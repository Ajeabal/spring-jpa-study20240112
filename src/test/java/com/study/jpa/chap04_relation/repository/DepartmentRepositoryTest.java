package com.study.jpa.chap04_relation.repository;

import com.study.jpa.chap04_relation.entity.Department;
import com.study.jpa.chap04_relation.entity.Employee;
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
@Rollback(false)
class DepartmentRepositoryTest {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @Test
    @DisplayName("특정 부서를 조회하면 해당 부서원들도 함께 조회된다.")
    void findDeptTest() {
        //given
        Long id = 1L;
        //when
        Department department = departmentRepository.findById(id).orElseThrow();

        //then
        System.out.println("\n\n\n");

        System.out.println("department = " + department);
        System.out.println("department.getEmployees() = " + department.getEmployees());

        System.out.println("\n\n\n");
    }

    //양방향 연관관계에서 연관 데이터 수정
    @Test
    @DisplayName("3번사원의 부서를 2번부서에서 1번으로 변경해야 한다")
    void changeDeptTest() {
        //given
        Employee employee = employeeRepository.findById(3L).orElseThrow();
        Department department = departmentRepository.findById(1L).orElseThrow();

        //when
        //tbl_emp테이블에서 dept_id를 변경하는 것이 논리적으로 옳다
        //양방향에서는 반대편도 같이 수정해줘야 한다.
        employee.setDepartment(department);
        department.getEmployees().add(employee);

        employeeRepository.save(employee);


        //then
        //부서를 변경한 상태에서 dept쪽에 1번 부서 사원을 조회하면
        List<Employee> employees = department.getEmployees();
        employees.forEach(System.out::println);
    }

}