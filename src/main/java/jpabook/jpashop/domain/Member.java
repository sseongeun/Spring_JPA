package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    private Address address;

    //@JsonIgnore // 주문 정보는 빼고 응답하겠다
    @OneToMany(mappedBy = "member")
    private List<Order> orders =new ArrayList<>();


}
