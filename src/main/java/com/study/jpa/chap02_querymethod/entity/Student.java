package com.study.jpa.chap02_querymethod.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Setter // 실무에서는 정말 필요한지 확인하고 만듬
@Getter
@Builder
@ToString
@EqualsAndHashCode(of = {"id", "name"}) // 두 객체럴 비교할 때 어떤 속성이 일치하면 같은 것일지를 설정
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @Column(name = "stu_id")
    @GeneratedValue(generator = "uid")
    @GenericGenerator(strategy = "uuid", name = "uid")
    private String id;

    @Column(name = "stu_name", nullable = false)
    private String name;

    private String city;

    private String major;
}
