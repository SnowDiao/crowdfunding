package org.snow.crowd.service.impl;

import org.snow.crowd.entity.Admin;
import org.snow.crowd.entity.AdminExample;
import org.snow.crowd.mapper.AdminMapper;
import org.snow.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;



    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
        throw new RuntimeException("抛出一个运行时异常");
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());

    }
}
