package com.study.jpa.chap04_relation.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
// 양방향에서 ToString을 사용하면 StackOverFlow에러가 발생함
// 한쪽 엔터티에서는 ToString을 만들 때 제외 시켜야 한다.
@ToString(exclude = {"employees"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name", nullable = false)
    private String name;

    /*
        양방향 맾핑은 데이터베이스와 달리 객체지향 시스템에서 가능한 방법으로
        1대N관계에서 1쪽에 N데이터를 포함 시킬 수 있는 방법

        - 양방향 맾핑에서 1쪽은 상대방 엔터티 개신에 관여X
            (리스트에서 사원을 지운다고 실제 디비에서 사원이 삭제되지는 않는다는 말)
            단순히 읽기전용으로만 사용하는 것
        - mappedBy에는 상대방 엔터티에 @ManyToOne에 대응되는 필드명을 꼭 적어야 한다.
    */
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();


}
