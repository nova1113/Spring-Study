package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //Spring이 Model을 넣어 준다.
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        return "hello";
    }

    //@ResponseBody가 없을 경우 Controller에서 ViewResolver로 리턴 값 전송
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //@ResponseBody가 있으면 http응답에 리턴값을 그냥 넣어서 HttpMessageConverter에 넘긴다.
    //HttpMessageConverter가 받았을 경우 String -> StringConverter | 객체 -> JsonConverter(MappingJackson2HttpMessageConverter)
    //Converter -> httpBody에 실어서 전송
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //api방식 -> 객체를 반환하는 방법
    static class Hello {
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }


}
