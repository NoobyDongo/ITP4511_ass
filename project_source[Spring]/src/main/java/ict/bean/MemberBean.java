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

    public String fname, lname, email, address, phone, pwd;
    
    @Override
    public MemberBean update(ResultSet rs) throws SQLException, IOException {
        id = rs.getString(1);
        fname = rs.getString(2);
        lname = rs.getString(3);
        email = rs.getString(4);
        address = rs.getString(5);
        phone = rs.getString(6); 
        pwd = rs.getString(7);
        return this;
    }

    @Override
    public String[] toStringArray() {
        return new String[]{fname, lname, email, address, phone, pwd, id};
    }
}
