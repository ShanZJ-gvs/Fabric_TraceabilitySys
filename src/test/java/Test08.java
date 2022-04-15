import com.gvssimux.pojo.Employee;
import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.EmployeeServiceImpl;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class Test08 {
    /*插入员工*/
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeServiceImpl mapper = context.getBean("EmployeeServiceImpl", EmployeeServiceImpl.class);
        Contract contract = FabricUtil.getContract();


        Employee pojo = new Employee();

        pojo.setEid("eid001");
        pojo.setEname("单子健001");
        pojo.setEsex("男");
        pojo.setCompany("阿里巴巴");

        String s = mapper.insertOne(contract, pojo);
        System.out.println(s);
    }


    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeServiceImpl mapper = context.getBean("EmployeeServiceImpl", EmployeeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        Employee pojo = new Employee();

        QueryResultList s1 = mapper.selectEmployeeById(contract, "阿里巴巴","eid001");
        QueryResultList s2 = mapper.selectAllEmployee(contract, "阿里巴巴");
        System.out.println("selectEmployeeById====》"+s1);
        System.out.println("selectAllEmployee====>"+s2);
    }


}
