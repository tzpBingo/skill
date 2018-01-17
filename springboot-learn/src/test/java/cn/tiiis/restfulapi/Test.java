package cn.tiiis.restfulapi;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class Test {

    @Autowired
    private MockMvc mvc;

    @org.junit.Test
    public void test() throws Exception {

        RequestBuilder requset = null;

        //get查询所有
        requset = get("/user/");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

        //post一个user
        requset = post("/user/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("id","1")
                .param("name","tzp")
                .param("age","25");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("ok")));


        //get查询所有
        requset = get("/user/");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"tzp\",\"age\":25}]")));


        //put修改user
        requset = put("/user/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("id","1")
                .param("name","ttt")
                .param("age","18");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("ok")));

        //get查询所有
        requset = get("/user/");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"ttt\",\"age\":18}]")));


        //delete删除user
        requset = delete("/user/1");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("ok")));

        //get查询所有
        requset = get("/user/");
        mvc.perform(requset)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }


}
