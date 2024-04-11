package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MemberEMB {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;


    //기간 Period
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    @Embedded
    private Period workPeriod;


    //주소
//    private String city;
//    private String street;
//    private String zipcode;
    @Embedded
    private Address homeAddress;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city",
//            column = @Column(name = "WORK_CITY")),
//            @AttributeOverride(name = "street",
//            column = @Column(name = "WORK_STREET")),
//            @AttributeOverride(name = "zipcode",
//            column = @Column(name = "WORK_ZIPCODE"))
//    })
//    private Address workAddress;

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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
