import com.daily.test.Test2018.Zoo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by json on 2018/5/17.
 * Describe: 测试 注解 是否生效
 */
public class TestBean {


    @Test
    public void  testSpring(){
        //读取配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-demo.xml");
        Zoo zoo=(Zoo) ctx.getBean("zoo");
        System.out.println(zoo.toString());

    }

}
