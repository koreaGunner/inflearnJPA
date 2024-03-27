package hellojpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    public Member() {
    }

    @Id
    private Long id;
    @Column(name = "name", nullable = false) // nullable = false : not null
    //columnDefinition = "varchar(100) default ‘EMPTY'" : 컬럼 정의를 직접 하는경우
    private String username;
    @Column
    private int age; //BigDecimal : 엄청 큰 값이나 소수점을 쓰고 싶을 때 사용 가능
    @Enumerated(value = EnumType.STRING)
    //defalt : EnumType.ORDINAL -> 순서 데이터를 insert -> 사용 금지(값을 추가하면 설정값이 다 바뀌는데 반영이 안됨)
    // -> 필수로 EnumType.STRING 사용해야한다
    private RoleType roleType;
    
    //과거의 버전
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    //과거의 버전
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //현재버전
    private LocalDate testLocalDate; //연도, 월
    //현재버전
    private LocalDateTime testLocalDateTime; //연도, 월, 일

    @Lob
    private String description;

    //db에 적용시키고 싶지 않을때
    @Transient
    private int temp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
