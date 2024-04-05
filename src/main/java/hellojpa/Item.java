package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //디폴트: SINGLE_TABLE -> 단일테이블 전략, JOINED ->  조인 전략,
                                                         //TABLE_PER_CLASS -> 구현 테이블마다 클래스 전략
@DiscriminatorColumn //디폴트로 엔티티명이 컬럼 데이터로 추가됨. 컬럼명은 DTYPE이 디폴트
                     //SINGLE_TABLE일때는 이 어노테이션이 없어도 DTYPE 알아서 추가
                     //TABLE_PER_CLASS일때는 이 어노테이션이 있어도 아무런 반응이 없음(구분할 필요가 없기때문)
public abstract class Item {
    //TABLE_PER_CLASS 일때는 abstract class로 만들어줘야한다 -> 그래야 Item 테이블이 안만들어짐
    
    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
