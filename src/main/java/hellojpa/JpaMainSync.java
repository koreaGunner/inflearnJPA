package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMainSync {
    public static void main(String[] args) {

        //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //엔티티 매니저는 쓰레드간에 공유X(사용하고 버려야된다)
        EntityManager em = emf.createEntityManager();

        //!! JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        //jpa는 트랜잭션이 매우 중요함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            TeamSync team = new TeamSync();
            team.setName("TeamA");
            em.persist(team);

            MemberSync member = new MemberSync();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);
            
            //영속성 컨텍스트를 날려서 실제 insert쿼리를 먼저 보고싶다면(영속성컨텍스트 클리어)
            em.flush();
            em.clear();

            MemberSync findMember = em.find(MemberSync.class, member.getId());
            List<MemberSync> members = findMember.getTeam().getMembers();

            for (MemberSync memberSync : members) {
                System.out.println("memberSync.getUsername() = " + memberSync.getUsername());
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
