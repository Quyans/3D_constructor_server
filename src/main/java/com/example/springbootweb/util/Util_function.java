package com.example.springbootweb.util;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * 这里写了通用的工具方法
 */
public  class Util_function {


    /**
     * 统一请求头
     * @param status
     * @param message
     * @param jsonObject
     * @return
     */
    public  static JSONObject setHttpHeader( int status , String message,JSONObject jsonObject){
        JSONObject mJson = new JSONObject();
        mJson.appendField("status",status);
        mJson.appendField("message",message);
        mJson.appendField("data",jsonObject);
        return mJson;
    }
    public  static JSONObject setHttpHeader(int status , String message, List<JSONObject> jsonObject){
        JSONObject mJson = new JSONObject();
        mJson.appendField("status",status);
        mJson.appendField("message",message);
        mJson.appendField("data",jsonObject);
        return mJson;
    }

    public  static JSONObject setHttpHeader( int status , String message){
        JSONObject mJson = new JSONObject();
        mJson.appendField("status",status);
        mJson.appendField("message",message);

        return mJson;
    }

}
