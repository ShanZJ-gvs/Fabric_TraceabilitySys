import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.util.FabricGatewayUtil;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test10 {
    /*清空数据*/
    @Test
    public void delete() throws Exception {
        Contract contract = FabricGatewayUtil.getContract();
        byte[] bytes = new byte[0];
        for (int i = 0; i < 10; i++) {
            bytes = contract.submitTransaction("deleteData","Area"+i);
            bytes = contract.submitTransaction("deleteData","Garden"+i);
        }
    }
}
