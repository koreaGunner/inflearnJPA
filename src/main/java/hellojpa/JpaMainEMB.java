package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

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

//            Address addres = new Address("city", "street", "zipcode");
//
//            MemberEMB member = new MemberEMB();
//            member.setUsername("member1");
//            member.setHomeAddress(addres);
//            em.persist(member);
//
//
////            Address copyAddress = new Address(addres.getCity(), addres.getStreet(), addres.getZipcode());
////
////            MemberEMB member2 = new MemberEMB();
////            member2.setUsername("member2");
////            member2.setHomeAddress(copyAddress);
////            em.persist(member2);
//
//            //side effect발생 -> 객체의 같음은 주소값을 공유하기때문에 부분만 수정할 수 없다.
//            //그러므로 setter 자체를 없애서 side effect를 없애야한다(private으로 만들어도 됨)
////            member.getHomeAddress().setCity("newCity");
//
//            //setter를 없앴으므로 값을 바꾸려면 새로운 객체를 만들어서 통으로 갈아끼우는게 방법
//            Address newAddress = new Address("newAddress", addres.getStreet(), addres.getZipcode());
//            member.setHomeAddress(newAddress);






            MemberEMB member = new MemberEMB();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity1", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);
            
            em.flush();
            em.clear();

            System.out.println("======================================= START ============================================");
            MemberEMB findMember = em.find(MemberEMB.class, member.getId());

//            //값 타입 컬렉션은 default가 지연로딩
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }
//
//            //값 타입 컬렉션은 default가 지연로딩
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }
            
            //값 타입 수정 homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity"); // 이렇게 하면 절대안됨(값타입은 불변 -> setter 자체가 없어야함)
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode())); //아예 새로 설정해야한다
            
            //치킨 -> 한식(컬렉션 값만 변경해도 jpa가 알아서 바꿔줌)
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            //값 타입 컬렉션에 변경 사항이 발생하면, 주인 엔티티와 연관된 모든 데이터를 삭제하고, 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장한다.
            // -> 원하는 값만 지우고 insert하지 않는다.
            //실무에서는 값 타입 컬렉션을 쓰면 안되므로 대체자인 일대다 관계를 이용
//            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));





            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
