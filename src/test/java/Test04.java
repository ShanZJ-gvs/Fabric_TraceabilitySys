import com.gvssimux.pojo.TeaArea;

import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.service.TeaGardenServiceImpl;

import com.gvssimux.service.TeaTreeServiceImpl;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    /*插入带公司标识的茶区*/
    @Test
    public void test04() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        TeaTree pojo = new TeaTree();
        pojo.setTeaTreeKind("瓜片");
        pojo.setCompany("百度");
        System.out.println(mapper.insertOne(contract,pojo));

    }

    /*查询某个公司下有多少个茶区*/
    @Test
    public void test05() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        System.out.println(mapper.getTreeSumByCompany(contract, "百度"));

    }





}
