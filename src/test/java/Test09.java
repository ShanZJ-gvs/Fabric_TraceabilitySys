import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.service.TeaRankServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class Test09 {


    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaRankServiceImpl mapper = context.getBean("TeaRankServiceImpl", TeaRankServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getRankPerSum(contract, "阿里巴巴");


        System.out.println("各级茶叶的量====》"+map);
        System.out.println("各级茶叶的量--前端接收的数据====》"+ JsonUtil.getJson(map));
    }
}
