package com.yangweiye.springbootdemos;

import com.yangweiye.springbootdemos.pojo.*;
import com.yangweiye.springbootdemos.services.StoreService;
import com.yangweiye.springbootdemos.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("pro")
public class SpringBootDemosApplicationTests {
    private static Logger log = LoggerFactory.getLogger(SpringBootDemosApplication.class);

    @Autowired
    ApplicationContext app;

    //自动注入
    @Autowired
    private UserService userService;

    // 同时存在 两个 StoreService 实现类
    // 使用了 @Primary 标记了 AliStoreServiceImpl 就会首先使用 primary
    @Autowired
    private StoreService storeService;

    //这么写当然好使 但是太 low 了 应该基于接口 而非实现编程
    //@Autowired
    //private TencentStoreServiceImpl tencentStoreService;

    //使用 @Qualifier 制定一个Bean
    @Autowired
    @Qualifier("tencentStoreServiceImpl")
    private StoreService tencentStoreService;

    @Test
    public void testBean() {
        //通过类型获取Bean
        User user = app.getBean(User.class);
        log.info(user.toString());

        //通过名称获取Bean
        Role role = (Role) app.getBean("role");
        log.info(role.toString());

        //自动注入的Bean
        userService.printUser(user);

        //注入时冲突解决
        storeService.Save("haha");
        tencentStoreService.Save("tencent");

        //基于构造方法和set方法的注入
        Company company = app.getBean(Company.class);
        log.info(company.toString());

        //自己定制一个bean 讲述了Bean 的生命周期
        //这个bean 使用了 Conditional注解 决定是否加载
        //这个bean 制定了 scope 为 prototype
        /*
            ConfigurableBeanFactory.SCOPE_SINGLETON; 单例 多次获取返回同一个
            ConfigurableBeanFactory.SCOPE_PROTOTYPE; 原型 每次获取都 新生成一个返回
            WebApplicationContext.SCOPE_APPLICATION; web工程 基本等同单例
            WebApplicationContext.SCOPE_REQUEST; 一次请求 每次请求 新生成一个返回
            WebApplicationContext.SCOPE_SESSION; 一次会话 每次会话 新生成一个返回
         */
        if (app.containsBean("customized")) {
            Customized customized = app.getBean(Customized.class);
            customized.setName("aa");
            String name = customized.getName();
            log.info(name);
        }


        //person 读取了配置文件 application.yaml
        Person person = app.getBean(Person.class);
        log.info(person.toString());
        //app.close();
    }

}
