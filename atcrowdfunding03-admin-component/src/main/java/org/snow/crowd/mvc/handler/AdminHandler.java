package org.snow.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import org.snow.crowd.constant.CrowdConstant;
import org.snow.crowd.entity.Admin;
import org.snow.crowd.exception.LoginAcctAlreadyInUseException;
import org.snow.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;


    // 响应退出登录页面
    @RequestMapping(value = "/admin/do/logout.html")
    public String doLogout(HttpSession session){
        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";

    }
    //响应登录页面
    @RequestMapping(value = "/security/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("userPswd") String userPswd,
                          HttpSession session){
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/to/main/page.html";//进入视图控制器，然后跳转到main.jsp
    }
    //响应需要分页查询的链接(查询然后分页)
    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            ModelMap modelMap) {
        // 调用service方法获取PageInfo对象
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
        // 将PageInfo对象存入模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-page";
    }
    // 响应删除Admin页面
    @RequestMapping(value = "/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String remove(@PathVariable("adminId") Integer adminId,
                         @PathVariable("pageNum") Integer pageNum,
                         @PathVariable("keyword") Integer keyword){
        // 执行删除
        adminService.remove(adminId);
        // 页面跳转
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }
        // 响应增加Admin页面
    @RequestMapping("/admin/save.html")
    public String save(Admin admin) {
        try {
            adminService.saveAdmin(admin);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
            throw e;
        }

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
    }
    // 响应修改Admin信息的界面，送到填修改信息的表单界面
    @RequestMapping("admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId") Integer adminId,
                             ModelMap modelMap) {
        // 1.根据adminId查询Admin对象
        Admin admin = adminService.getAdminById(adminId);
        // 2.将Admin对象存入模型
        modelMap.addAttribute("admin", admin);
        return "admin-edit";
    }
    // 获取表单信息，更新到数据库，转发到分页查询页面
    @RequestMapping("admin/update.html")
    public String update(Admin admin,
                         @RequestParam("pageNum") Integer pageNum,
                         @RequestParam("keyword") String keyword){
        adminService.update(admin);
        return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }


}
