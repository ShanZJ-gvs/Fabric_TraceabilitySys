import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.service.TeaKindServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test05 {
    /* 查询一个公司下有哪些种茶树*/
    @Test
    public void test04() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaKindServiceImpl mapper = context.getBean("TeaKindServiceImpl", TeaKindServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        List list1 = mapper.getKindByCompany(contract, "阿里巴巴");
        System.out.println();
        System.out.println("公司有哪些茶种 List====>"+list1);
        System.out.println();
        System.out.println("前端哪到的数据====>"+ JsonUtil.getJson(list1));

    }
}
