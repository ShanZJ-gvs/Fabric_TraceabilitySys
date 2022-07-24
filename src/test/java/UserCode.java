import com.gvssimux.controller.DataController;
import com.gvssimux.service.CreateUserCode;
import com.gvssimux.util.FabricUtil;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCode {
    public static void main(String[] args) throws Exception {
        DataController dataController = new DataController();
       /* String s = dataController.userCode("24c0e81c573147589634ae12faa32236");
        System.out.println(s);*/
    }

    @Test
    public void t2() throws Exception {
        CreateUserCode mapper = new CreateUserCode();
        String s = mapper.createUserCodeByKey("24c0e81c573147589634ae12faa32236");
        System.out.println(s);
    }

    @Test
    public void t1() throws Exception {
        Contract contract = FabricUtil.getContract();
        byte[] bytes = contract.evaluateTransaction("queryData", "24c0e81c573147589634ae12faa32236");
        System.out.println("初识数据=======>"+new String(bytes));
        System.out.println();

    }
}
