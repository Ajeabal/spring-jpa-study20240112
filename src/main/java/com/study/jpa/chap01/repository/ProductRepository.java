package com.study.jpa.chap01.repository;

import com.study.jpa.chap01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository상속 재너릭은 첫번째는 엔티티, 두번째는 PK
public interface ProductRepository extends JpaRepository<Product, Long> {

}
