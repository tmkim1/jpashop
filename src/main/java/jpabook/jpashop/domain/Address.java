package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

// 값 타입은 변경이 불가능하도록 설정 해야 함.
// @Setter를 제거하고, 생성자에서 값을 모두 초기화 하도록 설정.
@Embeddable //JPA 내장 타입임을 명시
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // JPA특성 상 처리, JPA -> 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 설정해야 함
    // - reflection 같은 기능을 사용하기 위해
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
