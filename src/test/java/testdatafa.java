import com.gvssimux.pojo.TeaMake;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testdatafa {
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
}
