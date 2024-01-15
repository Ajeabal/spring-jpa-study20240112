package com.study.jpa.chap01.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_product")
public class Product {

    // 앤티티 클래스는 DB테이블의 컬럼과 1:1로 매칭되는 필드르 선언
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private long id; // PK

    @Column(name = "prod_nm", length = 30, nullable = false)
    private String name; // 상품명

    @Builder.Default
    private int price = 1000; // 가격

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; // 상품 카테고리

    @CreationTimestamp // INSERT할 때 서버시간 자동 저장
    @Column(updatable = false)
    private LocalDateTime createdDate; // 상품등록 시간

    @UpdateTimestamp // UPDATE할 때 자동으로 수정시간 변경
    private LocalDateTime updatedDate; // 상품수정 시간

}
