package ict.bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author a1
 */
public class MemberBean extends AbstractBean<MemberBean> {

    public MemberBean() {
    }

    public String fname, lname, email, address, phone, pwd, role;
    
    @Override
    public MemberBean update(ResultSet rs) throws SQLException, IOException {
        id = rs.getString("id");
        fname = rs.getString("fname");
        lname = rs.getString("lname");
        email = rs.getString("email");
        phone = rs.getString("phone"); 
        pwd = rs.getString("pwd");
        role = rs.getString("role");
        return this;
    }

    @Override
    public String[] toStringArray() {
        return new String[]{fname, lname, email, address, phone, pwd, id};
    }
}
