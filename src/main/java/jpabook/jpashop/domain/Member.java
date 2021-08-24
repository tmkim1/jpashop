package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String userName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //Order 테이블에 있는 member field에 의해서 매핑된 것을 의미
    private List<Order> orders = new ArrayList<>();

}
