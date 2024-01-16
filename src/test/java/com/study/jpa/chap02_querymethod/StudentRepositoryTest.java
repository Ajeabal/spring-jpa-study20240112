package com.study.jpa.chap02_querymethod;

import com.study.jpa.chap02_querymethod.entity.Student;

import com.study.jpa.chap02_querymethod.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void insertData() {
        Student s1 = Student.builder()
                .name("쿠로미")
                .city("청양군")
                .major("경제학")
                .build();

        Student s2 = Student.builder()
                .name("춘식이")
                .city("서울시")
                .major("컴퓨터공학")
                .build();

        Student s3 = Student.builder()
                .name("어피치")
                .city("제주도")
                .major("화학공학")
                .build();

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
    }


    @Test
    @DisplayName("테스트로 하나 삽입한다")
    void insertTest() {
        //given
        Student student = Student.builder()
                .name("말똥이")
                .city("춘천시")
                .major("한국미술")
                .build();
        //when
        studentRepository.save(student);
        //then
    }

    @Test
    @DisplayName("이름으로 조회")
    void findByName() {
        //given
        String name = "춘식이";
        //when
        List<Student> byName = studentRepository.findByName(name);
        //then
        System.out.println("byName = " + byName);
    }

    @Test
    @DisplayName("전공에 공학이 포함된 학생 전부 조회한다.")
    void findByContainingTest() {
        //given
        String majorKeyword = "공학";

        //when
        List<Student> byMajorContaining = studentRepository.findByMajorContaining(majorKeyword);
        System.out.println("byMajorContaining = " + byMajorContaining);

        //then
        assertEquals(2, byMajorContaining.size());

    }

    @Test
    @DisplayName("네이티브 SQL로 이름 조회")
    void nativeSQLTest() {
        //given
        String name = "쿠로미";
        //when
        Student byNameWithSQL = studentRepository.findByNameWithSQL(name);
        //then
        assertEquals("청양군", byNameWithSQL.getCity());
    }

    @Test
    @DisplayName("JPQL메서드 사용 도시 이름으로 학생 조회")
    void findByCityJPQL() {
        //given
        String city = "청양군";
        //when
        Student byCityWithJPQL = studentRepository.getByCityWithJPQL(city);
        //then
        assertEquals("쿠로미", byCityWithJPQL.getName());
    }
    
    @Test
    @DisplayName("JPQL을 이용한 이름으로 검색하기")
    void searchNameTest() {
        //given
        String name = "춘";
        //when
        List<Student> students = studentRepository.searchByNameWithJPQL(name);
        //then
        assertEquals(1, students.size());
        System.out.println("students.get(0) = " + students.get(0));
    }

    @Test
    @DisplayName("JPQL로 삭제하기")
    void deleteWithJPQLTest() {
        //given
        String name = "어피치";
        String city = "제주도";
        //when
        studentRepository.deleteByNameWithJPQL(name, city);
        //then
        List<Student> byName = studentRepository.findByName(name);
        assertEquals(0, byName.size());

    }

}