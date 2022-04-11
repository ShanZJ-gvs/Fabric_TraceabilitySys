import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.service.TeaPickServiceImpl;
import com.gvssimux.service.TeaTreeServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

public class Test06 {


    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,"阿里巴巴","pick001",0,10));
    }

    /*插入带公司标识的TeaPick*/
    @Test
    public void test04() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        TeaPick pojo = new TeaPick();
        pojo.setTeaPickQuality("合格");
        pojo.setCompany("阿里巴巴");
        pojo.setTeaPickId("pick001");
        pojo.setTeaPickTime("二月");
        pojo.setOutput(1000);
        mapper.insertOne(contract,pojo);
    }

    /* 查询每月的采摘量*/
    @Test
    public void test05() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getPickPerSumByCompany(contract, "阿里巴巴");


        System.out.println("每月的采摘量====》"+map);
        System.out.println("前端接收的数据====》"+JsonUtil.getJson(map));
    }



    /* 测试前端的json格式*/
    @Test
    public void test07() throws JsonProcessingException {

        String json = "{\"瓜片1\":2,\"猴魁\":2,\"瓜片\":2}";
        HashMap<String,Integer> hashMap =  JSONObject.parseObject(json, HashMap.class);

        System.out.println();
        System.out.println("hashMap====>"+hashMap);

        hashMap.put("茶区数量",49);
        hashMap.put("茶园数量",50);
        hashMap.put("茶树数量",51);
        System.out.println();

        System.out.println("hashMap to jsonStr====》 "+JsonUtil.getJson(hashMap));

    }

}
