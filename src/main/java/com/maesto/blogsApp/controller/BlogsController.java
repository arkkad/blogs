package com.maesto.blogsApp.controller;


import com.maesto.blogsApp.model.Blog;
import com.maesto.blogsApp.service.impl.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class BlogsController {

    private final BlogService blogService;

    @Autowired
    public BlogsController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("getBlogs")
    public ResponseEntity<Page<Blog>> getPages(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "createdDate") String sortBy
    ) {
        return new ResponseEntity<Page<Blog>>(blogService.getPageOfBlogs(page, pageSize, sortBy), new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping("addBlog")
    public ResponseEntity<Object> addProduct(@ModelAttribute Blog blog) {
        Blog created = blogService.createBlog(blog);

        if (created != null) {
            return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.CREATED);
        } else {
            HashMap<String, String> message = new HashMap<>();
            message.put("mesage", "BAD REQUEST");
            return new ResponseEntity<>("Bad request", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }
}
