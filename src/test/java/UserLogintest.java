import com.gvssimux.pojo.UserBasic;
import com.gvssimux.service.UserLoginServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserLogintest {
    //单个查询
    @Test
    public void logintx(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserLoginServiceImpl mapper = context.getBean("UserLoginServiceImpl", UserLoginServiceImpl.class);
        UserBasic user = mapper.getUserIdByUserNamePwd("admin", "admin");
        System.out.println("执行成功=====》:"+user);
    }


}
