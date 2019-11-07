package com.codeup.blog.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number1}/and/{number2}")
    @ResponseBody
    public String add(@PathVariable int number1, @PathVariable int number2){
        int total = number1 + number2;
        return number1 + " + " + number2 + " = " + total;
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtract(@PathVariable int num1, @PathVariable int num2){
        int total = num2 - num1;
        return num1 + " - " + num2 + " = " + total;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiply(@PathVariable int num1, @PathVariable int num2){
        int total = num1 * num2;
        return num1 + " x " + num2 + " = " + total;
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divide(@PathVariable int num1, @PathVariable int num2){
        int total = num1 / num2;
        return num1 + " / " + num2 + " = " + total;
    }

    @GetMapping("/roll-dice")
    public String showGuessPage(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    public String showGuessedNum(@PathVariable int num, Model vModel){
        vModel.addAttribute("num", num);
        int randomNum = (int) (Math.random() * 6) + 1;
        vModel.addAttribute("randomNum", randomNum);
        return "roll-dice";
    }


}