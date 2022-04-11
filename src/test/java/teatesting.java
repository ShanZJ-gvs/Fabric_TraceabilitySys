import com.gvssimux.service.*;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class teatesting {

    /**
     * 用来测试 关于茶叶10表是否可以 全部查询
     *
     * */


    // 全部查询teaArea
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,0,1));

    }

    // 全部查询teaGarden
    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,0,1));

    }


    // 全部查询teaTree
    @Test
    public void test03() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        System.out.println(mapper.selectOffsetLimit(FabricUtil.getContract(),0,1));
    }

    // 全部查询teaLeaf
    @Test
    public void test04(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaLeafServiceImpl mapper = context.getBean("TeaLeafServiceImpl", TeaLeafServiceImpl.class);
        System.out.println(mapper.selectAll());
    }

    // 全部查询teaPack
    @Test
    public void test05(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPackServiceImpl mapper = context.getBean("TeaPackServiceImpl", TeaPackServiceImpl.class);
        System.out.println(mapper.selectAll());
    }

    // 全部查询teaPick
    @Test
    public void test06(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);

    }

    // 全部查询teaRank
    @Test
    public void test07(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaRankServiceImpl mapper = context.getBean("TeaRankServiceImpl", TeaRankServiceImpl.class);

    }


    // 全部查询teaTesting
    @Test
    public void test08(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTestingServiceImpl mapper = context.getBean("TeaTestingServiceImpl", TeaTestingServiceImpl.class);

    }


    // 全部查询teaMake
    @Test
    public void test09(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);

    }


    // 全部查询teaWarehouse
    @Test
    public void test010(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);

    }


}
