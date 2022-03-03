import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.util.GetUUID;
import com.gvssimux.util.JsonUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class datatest {

    /**
     * 数据总览data
     * 测试 茶区TeaArea
     */
    @ResponseBody
    @GetMapping("/teagarden")
    @Test
    public void  teagarden()throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建json工具
        JsonUtil jsonUtil = new JsonUtil();

        // 创建接收对象
        TeaArea teaArea = new TeaArea();
        teaArea.setTeaAreaId("11435fbe37994d4e804937c6433e2ce2");

        System.out.println(jsonUtil.getJson(teaArea));
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
        TeaGarden teaGarden = new TeaGarden("3b9cbf4c3699462a9e8c9151b6fddf6d","安徽合肥","13000公顷","132-332");
        System.out.println(jsonUtil.getJson(teaGarden));


    }
}
