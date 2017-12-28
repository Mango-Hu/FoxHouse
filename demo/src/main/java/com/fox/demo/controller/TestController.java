package com.fox.demo.controller;

import com.fox.demo.model.Girl;
import com.fox.demo.model.IccidList;
import com.fox.demo.repository.GirlRepository;
import com.fox.demo.service.CacheTest;
import com.fox.demo.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class TestController {

    @Autowired
    GirlRepository girlRepository;

    @Autowired
    GirlService girlService;

    @Autowired
    CacheTest cacheTest;

    @RequestMapping("hello")
    public ModelAndView Test(ModelAndView modelAndView){
        modelAndView.setViewName("hello");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sAppointedTime = "2017-11-22 15:00:00";
        LocalDateTime localDateTime =LocalDateTime.parse(sAppointedTime,timeFormatter);
        System.out.println(localDateTime);
        return modelAndView;
    }

    @RequestMapping("girl")
    public ModelAndView insertGirl(ModelAndView modelAndView){
        modelAndView.setViewName("hello");
        Iterable<Girl> girls = girlRepository.findAll();
        System.out.println(girls);
        return modelAndView;
    }

    @RequestMapping("name")
    public ModelAndView getGirlName(ModelAndView modelAndView){
        modelAndView.setViewName("hello");
        Girl girl = girlRepository.findGirlById(3);
        System.out.println(girl);
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("cacheGirl")
    public void testCacheGirl(){
        Girl girl  = cacheTest.testCache();
        System.out.println(girl);
    }

    @RequestMapping("cache3")
    public void TestCache3(){
        cacheTest.test3();
    }

    @RequestMapping("cache1")
    public void TestCache1(){
        cacheTest.test1();
    }

    @RequestMapping("cache2")
    public void TestCache2(){
        cacheTest.test2();
    }

    @RequestMapping("cache4")
    public void TestCache4(){
        cacheTest.test4();
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping("input")
    public ModelAndView input(ModelAndView modelAndView){
        modelAndView.setViewName("girl");
        return modelAndView;
    }

    @RequestMapping(value = "girls" )
    public @ResponseBody
    DataTablesOutput<Girl> testInput(@ModelAttribute DataTablesInput input){
        DataTablesOutput<Girl> girls = girlService.findAll(input);
        return girls;
    }
/*
    @RequestMapping("girls")
    public void  datatables(HttpServletResponse response){

        Iterable<Girl> girls = girlRepository.findAll();
        ObjectMapper mapper =new ObjectMapper();
        try {
            String girlsString = mapper.writeValueAsString(girls);
            System.out.println(girlsString);
            response.getWriter().print("{data:"+girlsString+"}");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    @PostMapping("save")
    public @ResponseBody
    List<String> save(IccidList iccidList){
        List list =new ArrayList();
        list.add("1");
        return list;
    }

    @RequestMapping("datatables")
    public ModelAndView datatables(ModelAndView modelAndView){
        modelAndView.setViewName("datatables");
        return  modelAndView;
    }

}
