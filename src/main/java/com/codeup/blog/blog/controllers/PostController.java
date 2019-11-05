package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

//  GET	    /posts	posts index page

    @GetMapping("/posts")
    @ResponseBody
    public String index(){
        return "posts index page\n";
    }

//  GET	    /posts/{id}	view an individual post

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable int id){
        return "view an individual post";
    }

//  GET	    /posts/create	view the form for creating a post

    @GetMapping("/posts/create")
    @ResponseBody
    public String postCreateGet(){
        return "view the form for creating a post\n";
    }

//  POST	/posts/create	create a new post

    @PostMapping("posts/create")
    @ResponseBody
    public String postCreatePost(){
        return "create a new post\n";
    }


}
