package com.gvssimux.pojo;

import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
public class Employee {

    // 员工唯一编号
    private String eid;

    // 员工姓名
    private String ename;

    // 员工性别
    private String esex;

}
