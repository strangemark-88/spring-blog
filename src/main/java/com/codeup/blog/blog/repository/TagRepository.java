package com.codeup.blog.blog.repository;

import com.codeup.blog.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}