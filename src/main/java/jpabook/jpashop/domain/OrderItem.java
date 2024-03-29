package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="order_item_id") //내가 외래키 가지고 있으므로 mapping하겠다
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int OrderPrice; //주문 가격

    private int count; //주문 수량

    //==생성 메서드==
    public static OrderItem createOrderItem(Item item,int orderPrice,int count){
        OrderItem orderItem=new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;

    }


    //==비즈니스 로직==//
    //주문 취소
    public void cancle(){
        getItem().addStock(count);
    }

    //==조회 로직==//
    public int getTotalPrice(){
        return getOrderPrice()*getCount();
    }

}
