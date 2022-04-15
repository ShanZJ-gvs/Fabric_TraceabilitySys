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

    // 员工类别 如：制茶师
    private String status;

    // 所属公司
    private String company;

    /**
     * 用于在富查询时标识查询类型的实体对象
     * */
    private final String type = "Employee";

}
