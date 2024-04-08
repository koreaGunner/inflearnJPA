package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

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

//            MemberSync member = new MemberSync();
//            member.setUsername("hello");
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
////            MemberSync findMember = em.find(MemberSync.class, member.getId());
//            MemberSync findMember = em.getReference(MemberSync.class, member.getId()); //프록시객체 -> 쿼리가 나가지않는다
//            System.out.println("before findMember = " + findMember.getClass()); //프록시클래스임을 확인(쿼리 나가지않는다.)
//            System.out.println("findMember.getId() = " + findMember.getId()); //위의 코드에서 파라미터로 썼기때문에 쿼리 나가지않는다
//            System.out.println("findMember.getUsername() = " + findMember.getUsername()); //실제 findMember를 쓸 때 쿼리가 나간다.
//            System.out.println("after findMember = " + findMember.getClass()); //프록시객체는 실제 엔티티로 바뀌는것이 아님을 확인할 수 있다.


//            printMember(findMember);

//            printMemberAndTeam(member);




//            MemberSync member1 = new MemberSync();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            MemberSync member2 = new MemberSync();
//            member2.setUsername("member2");
//            em.persist(member2);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("1");
//            MemberSync m1 = em.find(MemberSync.class, member1.getId());
//            System.out.println("2");
//            MemberSync m2 = em.getReference(MemberSync.class, member2.getId());
//
//            System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass())); //같은 class의 선언이더라도 프록시 객체는 원본 클래스와 같지않다
//                                                                                  //JPA에서 class 비교는 == 으로 하지 않는게 좋다
//            System.out.println("m1 instance m2 : " + (m1 instanceof MemberSync));
//            System.out.println("m1 instance m2 : " + (m2 instanceof MemberSync));



//            MemberSync member1 = new MemberSync();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            MemberSync m1 = em.find(MemberSync.class, member1.getId());
//            System.out.println("m1 = " + m1.getClass());
//
//            MemberSync reference = em.getReference(MemberSync.class, member1.getId());
//            System.out.println("reference = " + reference.getClass());
//
//            System.out.println("a == a : " + (m1 == reference));




//            MemberSync member1 = new MemberSync();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            MemberSync refMember = em.getReference(MemberSync.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); //proxy
//
//            MemberSync findMember = em.find(MemberSync.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass()); //MemberSync
//
//            System.out.println("refMember == findMember : " + (refMember == findMember)); //true가 나와야하므로 em.find에서도 프록시객체를 반환함




//            MemberSync member1 = new MemberSync();
//            member1.setUsername("member1");
//            em.persist(member1);
//
//            em.flush();
//            em.clear();
//
//            MemberSync refMember = em.getReference(MemberSync.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); //proxy
//
//            em.detach(refMember);
//
//            refMember.getUsername(); //영속성 컨텍스트에 해당 객체가 없으므로 프록시객체 초기화가 불가능




            MemberSync member1 = new MemberSync();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            MemberSync refMember = em.getReference(MemberSync.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); //proxy
            Hibernate.initialize(refMember);// 강제 초기화 : refMember.getUsername(); 같은 경우도 강제초기화(강제호출)이다.

            System.out.println("isLoaded : " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //프록시 인스턴스의 초기와 여부 확인




            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
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
