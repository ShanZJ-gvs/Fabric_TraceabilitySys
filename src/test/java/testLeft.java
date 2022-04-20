import com.gvssimux.service.TeaLeafServiceImpl;
import com.gvssimux.service.TeaTreeServiceImpl;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testLeft {
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaLeafServiceImpl mapper = context.getBean("TeaLeafServiceImpl", TeaLeafServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.selectOffsetLimit(contract,"阿里巴巴",0,2));
    }

    @Test
    public void test02() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaLeafServiceImpl mapper = context.getBean("TeaLeafServiceImpl", TeaLeafServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println();
        System.out.println(mapper.getLeft(contract,"阿里巴巴",0,100));
    }
}
