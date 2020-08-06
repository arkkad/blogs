package com.maesto.blogsApp.smoke;

import com.maesto.blogsApp.controller.BlogsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private BlogsController blogsController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(blogsController).isNotNull();
    }
}
