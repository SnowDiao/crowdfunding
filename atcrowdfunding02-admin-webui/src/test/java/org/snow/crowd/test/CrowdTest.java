package org.snow.crowd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snow.crowd.entity.Admin;
import org.snow.crowd.mapper.AdminMapper;
import org.snow.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 指定 Spring 给 Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)
// 加载 Spring 配置文件的注解
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;


    @Test
    public void testTx(){
        Admin admin = new Admin(null,"15151","123","杰伦","123@qq.com",null);
        adminService.saveAdmin(admin);
    }
    @Test
    public void testLog(){
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        logger.debug("测试debug级别的日志打印");
    }
    @Test
    public void testInsertAdmin(){
        for(int i = 1;i <= 226;i++){
            Admin admin = new Admin(null,"jack"+i,"123","杰克"+i,"123@qq.com",null);
            int count = adminMapper.insert(admin);
            System.out.println("受到影响的行数:" + count);
        }

    }

    @Test
    public void testDataSource() throws SQLException {
        // 1.通过数据源对象获取数据源连接
        Connection connection = dataSource.getConnection();
        // 2.打印数据库连接
        System.out.println(connection);
    }

}
