package com.maesto.blogsApp.service;

import com.maesto.blogsApp.model.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBlogService {
    Blog createBlog(Blog blog);

    Page<Blog> getPageOfBlogs(Integer page, Integer pageSize, String sortBy);
}
