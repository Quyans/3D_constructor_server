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
        String sql = "insert into User(name,sex) values(?,?)";
        this.jdbcTemplate.update(sql,users.getUsername(),users.getUsersex());
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
                users.setUsername(rs.getString("name"));
                users.setUsersex(rs.getInt("sex"));


                return users;
            }
        });


    }
}
