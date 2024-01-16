package com.study.jpa.chap03_pagination.repository;

import com.study.jpa.chap02_querymethod.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentPageRepository extends JpaRepository<Student, String> {

    Page<Student> findAll(Pageable pageable);

    // 검색 + 페이징
    // 학생 이름으로 검색기능 추가
    Page<Student> findByNameContaining(String name, Pageable pageable);
}
