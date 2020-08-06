package com.maesto.blogsApp.service.impl;

import com.maesto.blogsApp.model.Blog;
import com.maesto.blogsApp.repo.BlogsRepository;
import com.maesto.blogsApp.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class BlogService implements IBlogService {
    private final BlogsRepository blogsRepository;

    @Autowired
    public BlogService(BlogsRepository blogsRepository) {
        this.blogsRepository = blogsRepository;
    }

    @Override
//    @Transactional
    public Blog createBlog(Blog blog) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM.dd.yyy HH:mm");
            String formattedDateRime = now.format(formatter);
            blog.setCreatedDate(formattedDateRime);
            blogsRepository.save(blog);
            return blog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<Blog> getPageOfBlogs(Integer page, Integer pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        return blogsRepository.findAll(paging);
    }
}
