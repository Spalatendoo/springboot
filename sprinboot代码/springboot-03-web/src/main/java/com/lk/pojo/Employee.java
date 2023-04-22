package com.lk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor

//员工
public class Employee {
    private Integer id;
    private String name;
    private String email;
    private Integer gender;  // 0 :女 1： 男

    private Department department;
    private Date birth;

    public Employee(Integer id, String name, String email, Integer gender, Department department, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.department = department;
        // 默认的创建日期
        this.birth = new Date();
    }
}
