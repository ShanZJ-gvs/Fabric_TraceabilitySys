import com.gvssimux.service.TeaTestingServiceImpl;
import com.gvssimux.service.TeaTreeServiceImpl;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test11 {
    @Test
    public void test01() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTestingServiceImpl mapper = context.getBean("TeaTestingServiceImpl", TeaTestingServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        System.out.println(mapper.querySum(contract,"阿里巴巴"));
    }
}
