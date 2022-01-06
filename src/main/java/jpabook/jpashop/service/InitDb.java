package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initDb1();
        initService.initDb2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void initDb1() {
            Member member = createMember("userA", "Seoul", "1", "111");
            em.persist(member);

            Book book1 = new Book();
            book1.setName("JPA1 Book");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("JPA2 Book");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem OrderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem OrderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, OrderItem1, OrderItem2);
            em.persist(order);
        }

        public void initDb2() {
            Member member = createMember("userB", "Busan", "2", "222");
            em.persist(member);

            Book book1 = new Book();
            book1.setName("Spring1 Book");
            book1.setPrice(20000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("Spring2 Book");
            book2.setPrice(40000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem OrderItem1 = OrderItem.createOrderItem(book1, 20000, 2);
            OrderItem OrderItem2 = OrderItem.createOrderItem(book2, 40000, 3);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, OrderItem1, OrderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }
}
