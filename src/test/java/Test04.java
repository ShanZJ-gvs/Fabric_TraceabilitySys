import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.gvssimux.pojo.TeaArea;

import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaGardenServiceImpl;

import com.gvssimux.service.TeaTreeServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test04 {
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.getSum(contract));
    }


    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,0,10));
    }

    /*插入带公司标识的茶树*/
    @Test
    public void test04() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        TeaTree pojo = new TeaTree();
        pojo.setTeaTreeKind("瓜片");
        pojo.setCompany("阿里巴巴");
        pojo.setTeaTreeKind("瓜片1");
        pojo.setCompany("阿里巴巴");
        mapper.insertOne(contract,pojo);
        pojo.setTeaTreeKind("猴魁");
        pojo.setCompany("阿里巴巴");
        mapper.insertOne(contract,pojo);
        pojo.setTeaTreeKind("猴魁");
        pojo.setCompany("阿里巴巴");
        mapper.insertOne(contract,pojo);
        pojo.setTeaTreeKind("瓜片");
        pojo.setCompany("阿里巴巴");
        mapper.insertOne(contract,pojo);
    }

    /*查询某个公司下有多少个茶树*/
    @Test
    public void test05() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        System.out.println(mapper.getTreeSumByCompany(contract, "阿里巴巴"));
    }


    /* 查询一个公司下，茶种对应有多少棵茶树 */
    /* 茶树种类以及对应数量的Map映射集===》{"瓜片1":2,"猴魁":2,"瓜片":2} */
    @Test
    public void test06() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getTreeSumByCompanyToKind(contract, "阿里巴巴");
        System.out.println();
        System.out.println(JsonUtil.getJson(map));
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
