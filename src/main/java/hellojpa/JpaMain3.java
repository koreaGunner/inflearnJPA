package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain3 {
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
            Member member = new Member(200L, "member200");
            em.persist(member);

            //직접 호출(이 때 쿼리가 실행됨)
            em.flush();

            System.out.println("=============================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
