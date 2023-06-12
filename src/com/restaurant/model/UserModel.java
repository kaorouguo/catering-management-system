package com.restaurant.model;

import com.restaurant.db.SqlHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户表数据模型，用它完成对用户的各种操作
 * 这里主要编写项目需要的业务逻辑操作
 */
public class UserModel {
    /**
     *
     * @param uid 用户编号
     * @param p 用户密码
     * @return 用户职位，不存在就返回空
     */
    public String checkUser(String uid,String p){
        String job="";
        SqlHelper sp=null;
        //组织SQL语句和参数列表
        try {
            String sql="select 人事资料.职位 from 登录,人事资料 where 登录.员工号=人事资料.员工号 and 登录.员工号=? and 登录.密码=?";
            String paras[] ={uid,p};
            sp = new SqlHelper();
            ResultSet rs = sp.query(sql,paras);
            if(rs.next()){
                //则取出职位
                job=rs.getString("职位");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sp.close();
        }
        return job;
    }

}
