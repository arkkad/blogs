package com.maesto.blogsApp.controller;

import com.maesto.blogsApp.model.Blog;
import com.maesto.blogsApp.service.impl.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(BlogsController.class)
public class BlogsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlogService blogService;

    @Test
    public void findAllBlogs() throws Exception {
        List<Blog> blogsList = new ArrayList<>();
        Blog blog = new Blog((long) 1, "Test text for blog #1", "08.05.2020 17:04");
        Blog blog2 = new Blog((long) 1, "Test text for blog #2", "08.05.2020 17:05");

        blogsList.add(blog);
        blogsList.add(blog2);

        Page<Blog> pageBlogs = new PageImpl<Blog>(blogsList);

        Mockito.when(blogService.getPageOfBlogs(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).thenReturn(pageBlogs);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/getBlogs")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();

        String expected = "{\"content\":[{\"id\":1,\"text\":\"Test text for blog #1\",\"createdDate\":\"08.05.2020 17:04\"},{\"id\":1,\"text\":\"Test text for blog #2\",\"createdDate\":\"08.05.2020 17:05\"}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":2,\"totalPages\":1,\"number\":0,\"size\":2,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"numberOfElements\":2,\"first\":true,\"empty\":false}\n";


        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void addBlog() throws Exception {

        Blog mockBlog = new Blog((long) 1, "Super interesting text to blog!", "08.05.2020 20:47");
        Mockito.when(blogService.createBlog(Mockito.any(Blog.class))).thenReturn(mockBlog);

        String exampleCourseJson = "{\"text\":\"Super interesting text to blog!\"]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/addBlog")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());


    }
}
