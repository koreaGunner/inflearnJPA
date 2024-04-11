package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMainEMB {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //엔티티 매니저는 쓰레드간에 공유X(사용하고 버려야된다)
        EntityManager em = emf.createEntityManager();

        //!! JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        //jpa는 트랜잭션이 매우 중요함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address addres = new Address("city", "street", "zipcode");

            MemberEMB member = new MemberEMB();
            member.setUsername("member1");
            member.setHomeAddress(addres);
            em.persist(member);


//            Address copyAddress = new Address(addres.getCity(), addres.getStreet(), addres.getZipcode());
//
//            MemberEMB member2 = new MemberEMB();
//            member2.setUsername("member2");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);

            //side effect발생 -> 객체의 같음은 주소값을 공유하기때문에 부분만 수정할 수 없다.
            //그러므로 setter 자체를 없애서 side effect를 없애야한다(private으로 만들어도 됨)
//            member.getHomeAddress().setCity("newCity");

            //setter를 없앴으므로 값을 바꾸려면 새로운 객체를 만들어서 통으로 갈아끼우는게 방법
            Address newAddress = new Address("newAddress", addres.getStreet(), addres.getZipcode());
            member.setHomeAddress(newAddress);

            

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
