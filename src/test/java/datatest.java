import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.fabric.ClientApp;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.GetUUID;
import com.gvssimux.util.JsonUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public class datatest {

    /**
     * abric
     * 数据总览data
     * 测试 茶区TeaArea
     */
    @Test
    public void  teagarden()throws JsonProcessingException {

        // 创建json工具
        JsonUtil jsonUtil = new JsonUtil();

        // 创建接收对象
        TeaArea teaArea = new TeaArea();
        teaArea.setTeaAreaId1("11435fbe37994d4e804937c6433e2ce2");

        System.out.println(jsonUtil.getJson(teaArea));
    }


    @Test
    public void  teagarden02() throws Exception {
        log.info("controller开始执行");
        String s = new ClientApp().get("getTeaArea", "茶区");
        log.info("controller执行完毕");
        System.out.println(s);
    }

    @Test
    public void  teagarden03() throws Exception {
        Path walletPath = Paths.get("D:\\JavaProject\\Fabric_TraceabilitySys\\src\\main\\java\\com\\gvssimux\\wallet");
        String CCP = "D:/JavaProject/Fabric_TraceabilitySys/src/main/resources/com/shanzj/fabric/app/java/" +
                "peerOrganizations/org1.example.com/connection-org1.yaml";
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result;
        log.info("执行到了查询区块了----");


        //System.out.println(new String(result = contract.evaluateTransaction("createTeaArea2", "a4","安徽1")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a1")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a2")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a3")));
        //System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a4")));
    }
    @Test
    public void  teagarden04() throws Exception {
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result;
        log.info("执行到了查询区块了----");


        //System.out.println(new String(result = contract.evaluateTransaction("createTeaArea2", "a4","安徽1")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a1")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a2")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a3")));
        System.out.println(new String(contract.evaluateTransaction("getTeaArea", "a4")));
    }






    /**
     * 数据总览data
     * 测试
     * @return
     */
    @Test
    public void test01 () throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建json工具
        JsonUtil jsonUtil = new JsonUtil();

        // 创建 返回对象
        //TeaGarden teaGarden = new TeaGarden("3b9cbf4c3699462a9e8c9151b6fddf6d","安徽合肥","13000公顷","132-332");
        TeaGarden teaGarden = new TeaGarden();
        System.out.println(jsonUtil.getJson(teaGarden));


    }
}
