import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.UserBasic;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.service.UserRegisterServiceImpl;
import com.gvssimux.util.JsonUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class mybatistest {

    //增加一条数据
    @Test
    public void test01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserRegisterServiceImpl mapper = context.getBean("UserRegisterServiceImpl", UserRegisterServiceImpl.class);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginid","shanzj");
        map.put("pwd","shanzj");
        System.out.println(mapper.addUserBasic(map));

    }



    //查询一条数据
    @Test
    public void ubtest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserRegisterServiceImpl mapper = context.getBean("UserRegisterServiceImpl", UserRegisterServiceImpl.class);

        UserBasic UserBasic = mapper.getUserBasicByUserUname( 1);
        System.out.println("执行成功=====》:"+UserBasic);

    }


}
