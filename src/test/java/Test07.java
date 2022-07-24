import com.alibaba.fastjson.JSON;
import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.service.TeaPickServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Scanner;

public class Test07 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的key：");
        String key = sc.nextLine();
        System.out.println("请输入你的value：");
        String value = sc.nextLine();

        Contract contract = FabricUtil.getContract();

        contract.submitTransaction("createData", key,value);

    }

    /*插入带公司标识的TeaMake*/
    @Test
    public void test01() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的公司：");
        String name = sc.nextLine();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);

        Contract contract = FabricUtil.getContract();

        TeaMake pojo = new TeaMake();

        pojo.setMonth("三月");
        pojo.setOutput(1000);
        pojo.setCompany(name);

        mapper.insertOne(contract,pojo);
    }

    /* 查询每月的制茶量*/
    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        HashMap map = mapper.getMakePerSumByCompany(contract, "阿里巴巴");
        System.out.println("每月的制茶量====》"+map);
        System.out.println("前端接收的数据====》"+ JsonUtil.getJson(map));
    }

    @Test
    public void makeper() throws Exception {

        Contract contract = FabricUtil.getContract();

        String key = "Make3";
        byte[] deleteData = contract.submitTransaction("deleteData", key);
        String s = new String(deleteData);
        System.out.println(s);
    }
}
