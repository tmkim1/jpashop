package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member") //Order 테이블에 있는 member field에 의해서 매핑된 것을 의미
    private List<Order> orders = new ArrayList<>();

}
