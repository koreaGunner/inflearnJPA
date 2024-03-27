package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
//@Table(name = "MBR") : table 이름 설정 가능 -> runtime과는 무관하다
public class Member2 {

    @Id
    private Long id;
    // @Column(unique = true, length = 10) : 제약조건 -> DDL 생성기능
    private String name;
    private int age;

    //JPA는 기본 생성자 필요
    public Member2() {
    }

    public Member2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
