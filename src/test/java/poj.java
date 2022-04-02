import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.Employee;
import com.gvssimux.pojo.TeaPack;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResult;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import org.bouncycastle.util.Pack;
import org.junit.Test;

import javax.json.Json;
import java.util.List;

public class poj {

    @Test
    public void test01() throws InstantiationException, IllegalAccessException {

        TeaAreaQueryResultList<TeaAreaQueryResult> resultList = JSON.toJavaObject(JSONObject.parseObject(
              "{\"teaAreas\":[{\"record\":{\"teaPackPer\":{\"eid\":\"cd09adee145f4ac995d349127e623efb\",\"ename\":\"杨过\",\"esex\":\"男\"},\"teaPackTime\":\"2022年3月23日20:48:46\",\"teaPackSmllBoxId\":\"2996d90caac749a3b61067b4e80b2e0a\",\"teaPackID\":\"389d\",\"teaPackBigBoxId\":\"24c0e81c573147589634ae12faa32236\"},\"key\":\"Pack1\"},{\"record\":{\"teaPackPer\":{\"eid\":\"cd09adee145f4ac995d349127e623efb\",\"ename\":\"杨过\",\"esex\":\"男\"},\"teaPackTime\":\"2022年3月23日20:48:46\",\"teaPackSmllBoxId\":\"2996d90caac749a3b61067b4e80b2e0a\",\"teaPackID\":\"389d\",\"teaPackBigBoxId\":\"24c0e81c573147589634ae12faa32236\"},\"key\":\"Pack2\"}]}"
        ),TeaAreaQueryResultList.class);


        TeaAreaQueryResult a = JSON.toJavaObject(
                JSONObject.parseObject(
                        String.valueOf(resultList.getTeaAreas().get(0))
                ),TeaAreaQueryResult.class
        );
        JSON json = JSON.parseObject(JSON.toJSONString(a.getRecord()));
        TeaPack pack = json.toJavaObject(TeaPack.class);

        System.out.println(pack);
    }
}
