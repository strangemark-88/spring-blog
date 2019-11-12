package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.models.PostImage;
import com.codeup.blog.blog.models.Tag;
import com.codeup.blog.blog.repository.PostImageRepository;
import com.codeup.blog.blog.repository.PostRepository;
import com.codeup.blog.blog.repository.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final PostImageRepository postImageDao;

    private final TagRepository tagDao;

    public PostController(PostRepository postDao, PostImageRepository postImageDao, TagRepository tagDao) {
        this.postDao = postDao;
        this.postImageDao = postImageDao;
        this.tagDao = tagDao;
    }

    @GetMapping("/posts")
    public String index(Model model){

        model.addAttribute("posts", postDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model vModel){
        vModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam String title, @RequestParam String body){
        return "create a new post";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post oldPost = postDao.getOne(id);
        oldPost.setTitle(title);
        oldPost.setBody(body);
        postDao.save(oldPost);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/delete/posts")
    public String deletePostBy(@RequestParam("id")  String id){
        long deleteId = Long.parseLong(id);
        postDao.deleteById(deleteId);
        return "redirect:/posts";
    }

    @GetMapping("/posts/history/{id}")
    public String testView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/test";
    }

    @GetMapping("/posts/{id}/add-image")
    public String catView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getOne(id));
        return "posts/test";
    }

    @PostMapping("/posts/{id}/add-image")
    public String addImage(
            @PathVariable long id,
            @RequestParam String title,
            @RequestParam String url) {

        PostImage postImage = new PostImage(title, url);
        postImage.setPost(postDao.getOne(id));
        postImageDao.save(postImage);

        return "redirect:/posts/{id}/add-image";
    }

    @GetMapping("/post-tags")
    public String getPetVets(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/test";
    }

    @PostMapping("/tags/post/{id}")
    public String assignNewTagToPost(
            @PathVariable long id,
            @RequestParam String tag) {
        Post post = postDao.getOne(id);
        tagDao.save(new Tag(tag, Arrays.asList(post)));
        return "redirect:/post-tags";
    }

}