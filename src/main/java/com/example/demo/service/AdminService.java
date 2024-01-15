package com.example.demo.service;

import com.example.demo.repository.AdminMyBatisRepository;
import com.example.demo.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMyBatisRepository repository;

    public List<UsersVO> getTotalUserList(HashMap<String, Object> map) {
        return repository.getTotalUserList(map);
    }
    public int getTotalUser(){
        return repository.getTotalUser();
    }

    public int deleteUser(int userno) {
        return repository.deleteUser(userno);
    }
}