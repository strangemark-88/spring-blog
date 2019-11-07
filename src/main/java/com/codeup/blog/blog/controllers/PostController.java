package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.Post;
import com.codeup.blog.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model vModel){
        vModel.addAttribute("posts", postDao.findAll());
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



    @GetMapping("/posts/edit")
    public String editForm(){
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String editPost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        return "redirect:/posts";
    }



//    @PostMapping("/posts/delete")
//    public String deletePost(@RequestParam(name = "post") long id){
//        postDao.deleteById(id);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    /* It updates model object. */
//    @RequestMapping(value="/editsave",method = RequestMethod.POST)
//    public String editsave(@ModelAttribute("emp") Emp emp){
//        dao.update(emp);
//        return "redirect:/viewemp";
//    }


}