package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

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
            //code
            //insert
            //비영속 상태
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
            //영속 상태(이 때, db에 저장되는 것이 아님)
            //BEFORE AFTER 사이에 쿼리가 실행되지 않음을 확인할 수 있다
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");

            //delete
            //em.find 후에
//            em.remove();

            //Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            //update
            //em.find 후에
//            findMember.setName("HelloJPA");
            //em.persist필요없음(JPA에서 알아서 관리함)

            //JPQL
            //select(전체회원) -> 단일회원 조회같은건 em.find로 가능
            //JPA는 테이블을 대상으로 쿼리를 짜지않는다
            //객체를 대상으로 쿼리를 짠다(객체지향쿼리)
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    //페이지네이션
//                    .setFirstResult(1)
//                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
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
