import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.service.TeaPickServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class Test07 {

    /*插入带公司标识的TeaMake*/
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        TeaMake pojo = new TeaMake();

        pojo.setMonth("三月");
        pojo.setCompany("阿里巴巴");
        pojo.setOutput(1000);

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
}
