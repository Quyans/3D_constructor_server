package com.example.springbootweb.dao.impl;

import com.example.springbootweb.dao.UserDao;
import com.example.springbootweb.mojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 持久层
 */

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;



    /**
     * 添加用户
     * @param users
     */
    @Override

    public void insertUsers(Users users) {
        String sql = "insert into User(phone,nickname,password) values(?,?,?)";
        this.jdbcTemplate.update(sql,users.getPhone(),users.getNickname(),users.getPassword());
    }

    @Override
    public List<Users> findAllUser() {
        String sql = "select * from User";
        return this.jdbcTemplate.query(sql, new RowMapper<Users>() {
            /**
             * 结果集的映射
             * @param rs
             * @param rowNum
             * @return
             * @throws SQLException
             */
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

                Users users = new Users();
                users.setPhone(rs.getString("phone"));
                users.setNickname(rs.getString("nickname"));


                return users;
            }
        });
    }

    @Override
    public Users getUserInfo(Users users) {
        String sql = "select * from User where phone = " + users.getPhone()  ;

        //queryForObject 返回单个对象
        //https://www.cnblogs.com/gongxr/p/8053010.html 这篇文章讲的很好
        return this.jdbcTemplate.queryForObject(sql, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setPhone(rs.getString("phone"));
                user.setNickname(rs.getString("nickname"));
                return user;
            }
        });
    }
}
