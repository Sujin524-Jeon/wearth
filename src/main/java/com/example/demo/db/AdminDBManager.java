package com.example.demo.db;

import com.example.demo.vo.OrdersDetailGoodsVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.UsersVO;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class AdminDBManager extends DBManager {
    public static List<UsersVO> getTotalUserList(HashMap<String, Object>map) {

        System.out.println("getTotalUserList의 AdminDBManager작동");

        SqlSession session = sqlSessionFactory.openSession();
        List<UsersVO> userList = session.selectList("admin.getTotalUserList", map);
        System.out.println("AdminDBManger의 List확인" + userList);
        session.close();
        return userList;
    }

    public static int getTotalUser(){
        int n = 0;
        SqlSession session = sqlSessionFactory.openSession();
        n = session.selectOne("admin.getTotalUser");
        System.out.println("AdminDBMANGER 활성화 : getTotalUser : " + n);
        return n;
    }

    public static int deleteUser(int userno){

        int re = -1;
        System.out.println();
        SqlSession session =sqlSessionFactory.openSession();
        re = session.delete("admin.deleteUser", userno);
        session.commit();
        session.close();
        return re;
    }
}