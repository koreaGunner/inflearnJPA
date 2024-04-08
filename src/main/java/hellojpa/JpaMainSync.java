package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
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

//            Movie movie = new Movie();
//            movie.setDirector("aaaa");
//            movie.setActor("bbbb");
//            movie.setName("바람과함께사라지다");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);

//            MemberSync member = new MemberSync();
//            member.setUsername("user1");
//            member.setCreatedBy("kim");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            MemberSync member = new MemberSync();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

//            MemberSync findMember = em.find(MemberSync.class, member.getId());
            MemberSync findMember = em.getReference(MemberSync.class, member.getId()); //프록시객체 -> 쿼리가 나가지않는다
            System.out.println("findMember = " + findMember.getClass()); //프록시클래스임을 확인(쿼리 나가지않는다.)
            System.out.println("findMember.getId() = " + findMember.getId()); //위의 코드에서 파라미터로 썼기때문에 쿼리 나가지않는다
            System.out.println("findMember.getUsername() = " + findMember.getUsername()); //실제 findMember를 쓸 때 쿼리가 나간다.


//            printMember(findMember);

//            printMemberAndTeam(member);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(MemberSync member) {
        System.out.println("member.getUsername() = " + member.getUsername());

    }

    private static void printMemberAndTeam(MemberSync member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        TeamSync team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());

    }
}
