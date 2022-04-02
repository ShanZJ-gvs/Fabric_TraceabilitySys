import com.alibaba.fastjson.JSON;
import com.gvssimux.pojo.AllPojo;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.service.TeaAreaServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {


    // 全部查询teaArea
    @Test
    public void test01(){
        AllPojo allPojo = new AllPojo();
        TeaTree tree = new TeaTree();
        tree.setTeaGardenId2("gt23");
        tree.setTeaAreaId2("a676");
        tree.setTeaTreeCultivate("wadad");
        tree.setTeaTreeState("好！！");
        tree.setTeaTreeKind("毛峰");
        tree.setTeaTreeLongitude("wafwafa");
        tree.setTeaTreeGrowingEnv("潮湿");

        allPojo.setTeaTree(tree);

        System.out.println(JSON.toJSONString(allPojo));
    }
}
