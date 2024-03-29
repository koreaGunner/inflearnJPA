package semina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaSemina {
    public static void main(String[] args) {

        //엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("semina");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            User user = new User();
            user.setId(1L);
            user.setName("A");

            em.persist(user);

            tx.commit();

        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
