package cn.tiiis.dynamicconf;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private User user;


    @org.junit.Test
    public void test (){
        System.out.println(user.getName());
        System.out.println(user.getPhone());
    }

}
