package hellojpa;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name = "member_seq_generator",
        sequenceName = "member_seq", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50 //시퀀스값을 미리 올려놓는 방식(다 채워지지 않는 경우가 존재)
)
public class MemberPK {
    public MemberPK() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

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
}
