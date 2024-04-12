package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JpaMainJPQL {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //엔티티 매니저는 쓰레드간에 공유X(사용하고 버려야된다)
        EntityManager em = emf.createEntityManager();

        //!! JPA의 모든 데이터 변경은 트랜잭션 안에서 실행

        //jpa는 트랜잭션이 매우 중요함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            List<MemberEMB> result = em.createQuery(
//                    "select m from MemberEMB m where m.username like '%kim%'",
//                    MemberEMB.class
//            ).getResultList();
//
//            for (MemberEMB memberEMB : result) {
//                System.out.println("memberEMB = " + memberEMB);
//            }



            //Criteria 사용 준비 -> 실무에서 안씀!
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<MemberEMB> query = cb.createQuery(MemberEMB.class);
//
//            Root<MemberEMB> m = query.from(MemberEMB.class);
//
//            CriteriaQuery<MemberEMB> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
//
//            List<MemberEMB> resultList = em.createQuery(cq).getResultList();


            //flush -> commit, query
            //네이티브SQL
            em.createNativeQuery("select MEMBER_ID, city, street, zipcode, username from MEMBEREMB")
                    .getResultList();




        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}
