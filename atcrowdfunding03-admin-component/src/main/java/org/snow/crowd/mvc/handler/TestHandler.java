package org.snow.crowd.mvc.handler;

import com.sun.net.httpserver.Authenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snow.crowd.entity.Admin;
import org.snow.crowd.service.api.AdminService;
import org.snow.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;


    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap){
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute(adminList);
       /* String a = null;
        System.out.println(a.toString());*/
        System.out.println(10/0);

        return "target";
    }


    @RequestMapping("/send/array/three.json")
    @ResponseBody
    public ResultEntity<List<Integer>> testReceiveArray(@RequestBody List<Integer> arrayList){
        Logger logger = LoggerFactory.getLogger(this.getClass());
       for (Integer number : arrayList){
           logger.info("number=" + number);
       }
       ResultEntity<List<Integer>> resultEntity = ResultEntity.successWithData(arrayList);

        /*String a = null;
        System.out.println(a.toString());*/
        return resultEntity;
    }
}
