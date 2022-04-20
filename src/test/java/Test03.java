import com.gvssimux.pojo.TeaArea;

import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.service.TeaGardenServiceImpl;

import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03 {
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.getSum(contract));
    }


    @Test
    public void test03() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,"阿里巴巴",1,7));
    }

    /*插入带公司标识的茶园*/
    @Test
    public void test04() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        TeaGarden pojo = new TeaGarden();
        pojo.setCompany("阿里巴巴");
        pojo.setTeaGardenId1("d2wagwa1241ot41142ytytj14wag125afa");
        pojo.setTeaGardenArea("88885");
        pojo.setTeaGardenName("天下会茶园");
        pojo.setTeaGardenAddress("安徽黄山");
        System.out.println(mapper.insertOne(contract,pojo));

    }

    /*查询某个公司下有多少个茶园*/
    @Test
    public void test05() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        System.out.println(mapper.getGardenSumByCompany(contract, "阿里巴巴"));

    }





}
