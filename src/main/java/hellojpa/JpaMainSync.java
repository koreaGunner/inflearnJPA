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
//            TeamSync team = new TeamSync();
//            team.setName("TeamA");
//            em.persist(team);
//
//            MemberSync member = new MemberSync();
//            member.setUsername("member1");
//            member.changeTeam(team);
//            em.persist(member);
            
            //양방향일 때, 양쪽에 다 값을 넣어주는게 제일 좋다
            //그런데 사람은 실수로 안넣을 수 있기때문에.. setTeam함수(chageTeam으로 변경)에 이 로직을 추가해주는게 제일 좋다
            //team.getMembers().add(member); --> setTeam(chageTeam으로 변경)함수에 추가

            //영속성 컨텍스트를 날려서 실제 insert쿼리를 먼저 보고싶다면(영속성컨텍스트 클리어)
//            em.flush();
//            em.clear();
//
//            TeamSync findTeam = em.find(TeamSync.class, team.getId());
//            List<MemberSync> members = findTeam.getMembers();
//
//            for (MemberSync memberSync : members) {
//                System.out.println("memberSync.getUsername() = " + memberSync.getUsername());
//            }

//            MemberSync member = new MemberSync();
//            member.setUsername("member1");
//
//            em.persist(member);
//
//            TeamSync team = new TeamSync();
//            team.setName("teamA");
//            team.getMembers().add(member);
//
//            em.persist(team);

            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
