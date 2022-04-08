import com.gvssimux.pojo.TeaArea;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.TeaKindServiceImpl;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.getSum(contract));
    }

    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        TeaArea pojo = new TeaArea();
        pojo.setTeaAreaName("006");

        System.out.println(mapper.insertOne(contract,pojo));
    }

    @Test
    public void test03() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,1,7));
    }

    /*插入带公司标识的茶区*/
    @Test
    public void test04() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        TeaArea pojo = new TeaArea();
        pojo.setTeaAreaName("007");
        pojo.setCompany("腾讯");
        System.out.println(mapper.insertOne(contract,pojo));

    }

    /*查询某个公司下有多少个茶区*/
    @Test
    public void test05() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        System.out.println(mapper.getAreaSumByCompany(contract, "腾讯"));

    }





}
