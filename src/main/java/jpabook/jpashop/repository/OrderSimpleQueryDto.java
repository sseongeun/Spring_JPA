package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;

import java.time.LocalDateTime;

public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId,String name,LocalDateTime orderDate,OrderStatus orderStatus,Address address) {
        orderId = orderId;
        name = name;
        orderDate = orderDate;
        orderStatus =orderStatus;
        address = address;
    }
}
