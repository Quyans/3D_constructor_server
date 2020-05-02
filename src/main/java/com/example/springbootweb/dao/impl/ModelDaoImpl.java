package com.example.springbootweb.dao.impl;

import com.example.springbootweb.dao.ModelDao;
import com.example.springbootweb.mojo.Models;
import com.example.springbootweb.mojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 持久层
 */

@Repository
public class ModelDaoImpl implements ModelDao {

//    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
//
//
//    /**
//     *
//     * @param models
//     */
//    @Override
//    public void newModel(Models models) {
//        System.out.println("Dao!!  ------------这里是Dao层");
//        String sql = "insert into Model(phone,model_name,id_model,create_time) values(?,?,?,?)";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
////        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//        String time = df.format(new Date());
//        this.jdbcTemplate.update(sql,models.getPhone(),models.getName(),models.getID(),time);
//    }


    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 新建模型
     * @param models
     */
    @Override
    public void newModel(Models models) {
        System.out.println("Dao!!  ------------这里是Dao层");
        String sql = "insert into Model(id_model,phone,model_name,create_time,ownerName,logoUrl) values(?,?,?,?,?,?)";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String time = df.format(new Date()).toString();
        this.jdbcTemplate.update(sql,models.getID(),models.getPhone(),models.getName(),time,models.getOwnerName(),models.getLogo());
//        this.jdbcTemplate.update(sql,models.getID(),models.getPhone(),"曲岩松",time);
    }

    @Override
    public List<Models> getAllModel(String phone) {
        String sql = "select * from Model where phone = ?";
        return this.jdbcTemplate.query(sql, new RowMapper<Models>() {
            @Override
            public Models mapRow(ResultSet rs, int rowNum) throws SQLException {
                Models models = new Models();
                models.setName(rs.getString("model_name"));
                models.setID(rs.getString("id_model"));
                models.setLogo(rs.getString("logoUrl"));
                models.setPhone(rs.getString("phone"));
                models.setOwnerName(rs.getString("ownerName"));
                models.setTime(rs.getString("create_time"));
                return models;
            }
        },phone);
    }

}
