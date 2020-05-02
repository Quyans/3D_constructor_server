package com.example.springbootweb.dao.impl;

import com.example.springbootweb.dao.UserDao;
import com.example.springbootweb.mojo.Users;
import org.apache.catalina.User;
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
        System.out.println("插入成功");
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

    //占位符在最后加！
    @Override
    public Users login(Users users) {

        String sql = "select * from User where phone = ? and password = ?";
        List<Users> usersList  = this.jdbcTemplate.query(sql,new RowMapper<Users>() {

            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                //https://blog.csdn.net/qq_28080659/article/details/53363752
                //这篇文章讲了如何获取读到的数据数目
                rs.last();
                int rowCount = rs.getRow();
//
//                System.out.println("rs大小"+ rowCount);
                if (rowCount==1){
                    Users users1 = new Users();
                    users1.setPhone(rs.getString("phone"));
                    users.setNickname(rs.getString("nickname"));
                    return users;
                }
                return null;
            }
        },users.getPhone(),users.getPassword());

        if (usersList.size()==1){
            return usersList.get(0);
        }
        return null;
    }
}
