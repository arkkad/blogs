package com.maesto.blogsApp.repo;

import com.maesto.blogsApp.model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogsRepository extends PagingAndSortingRepository<Blog, Long> {
}
