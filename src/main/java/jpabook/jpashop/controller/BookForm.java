package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class BookForm {

    //item 공통 속성 4가지
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //Bool 고유 속성 2가지
    private String author;
    private String isbn;
}
