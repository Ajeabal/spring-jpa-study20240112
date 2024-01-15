package com.study.jpa.chap01.repository;

import com.study.jpa.chap01.entity.Category;
import com.study.jpa.chap01.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.study.jpa.chap01.entity.Category.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void insertBeforeTest() {
        Product p1 = Product.builder()
                .name("아이폰")
                .category(ELECTRONIC)
                .price(2000000)
                .build();
        Product p2 = Product.builder()
                .name("탕수육")
                .category(FOOD)
                .price(20000)
                .build();
        Product p3 = Product.builder()
                .name("구두")
                .category(FASHION)
                .price(30000)
                .build();
        Product p4 = Product.builder()
                .name("주먹밥")
                .category(FOOD)
                .price(1500)
                .build();
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);

    }

    @Test
    @DisplayName("상품을 DB에 저장한다.")
    void saveTest() {
        //given
        Product buildProduct = Product.builder()
                .name("정장")
                .price(120000)
                .category(FASHION)
                .build();
        //when
        Product save = productRepository.save(buildProduct);
        //then
        assertNotNull(save);
    }

    @Test
    @DisplayName("1번 상품을 삭제한다")
    void delTest() {
        //given
        Long id = 1L;
        //when
        productRepository.deleteById(id);
        //then
        Optional<Product> byId = productRepository.findById(id);
        assertNull(byId);
    }

    @Test
    @DisplayName("3번 상품 단일조회")
    void findOneTest() {
        //given
        long id = 3L;
        //when
        //Optional null 체크 간소화 하기 위한 타입
        Optional<Product> product = productRepository.findById(id);
        //then
        System.out.println("product = " + product);
        //ifPresent는 null이 아니면 람다의 코드 진행, null이면 무시
        product.ifPresent(p -> assertEquals("구두", p.getName()));

        // product가 null이면 새로운 new Product를 반환하고
        // null이 아니면 Optional 안에서 꺼내서 반환
        Product ppp = product.orElse(new Product());
        Product pppp = product.orElseThrow();
    }

    @Test
    @DisplayName("2번 상품의 이름과 카테고리를 수정한다.")
    void modTest() {
        //given
        long id = 2L;
        String newName = "짜장면";
        //when
        /*
            jpa에서는 수정 메서드를 따로 제공하지 ㅇ낳고
            단일 조회를 수행 후 setter를 호출해 값을 변경
            다시 save를 하면 UPDATE가 나간다.
         */
        Product product = productRepository.findById(id).get();
        product.setName(newName);
        product.setCategory(FOOD);
        productRepository.save(product);

        //then
    }

    @Test
    @DisplayName("상품을 전체조회하면 상품의 총 개수가 4개다.")
    void findALlTest() {
        //given

        //when
        List<Product> all = productRepository.findAll();
        //then
        System.out.println("\n\n");
        all.forEach(System.out::println);
        System.out.println("\n\n");
        assertEquals(4, all.size());
    }
}