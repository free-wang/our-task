package com.water76016.ourtask.controller;

import com.alibaba.fastjson.JSONObject;
import com.water76016.ourtask.common.RestResult;
import com.water76016.ourtask.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class TouristControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void register() {
    }

    @Test
    void login() throws Exception {
        User user = new User("user", "123456");
        String content = JSONObject.toJSONString(user);
        String result = mockMvc.perform(post("/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        RestResult restResult = JSONObject.parseObject(result, RestResult.class);
        assert restResult.get("code").equals(200);
        System.out.println();
    }
}