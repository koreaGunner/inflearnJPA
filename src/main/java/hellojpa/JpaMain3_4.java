package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain3_4 {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //엔티티 매니저는 쓰레드간에 공유X(사용하고 버려야된다)
        EntityManager em = emf.createEntityManager();

        //!! JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        //jpa는 트랜잭션이 매우 중요함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //영속
            Member2 member = em.find(Member2.class, 150L);
            member.setName("AAAA"); //em.persist(member) 를 쓰면 안됨

            //data 변경 쿼리가 실행되지않음
            //detach를 함으로써 영속성컨텍스트를 관리하지 않음 -> 준영속 상태
//            em.detach(member);

            //영속성컨텍스트를 다 날려버림(초기화)
            em.clear();

            //영속성컨텍스트가 관리됐다면 쿼리를 실행하지 않으나, em.clear로 다 날려버렸기 때문에 쿼리를 다시 날림
            Member2 member2 = em.find(Member2.class, 150L);


            System.out.println("====================================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
