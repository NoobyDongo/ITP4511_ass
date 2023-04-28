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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public MemberBean(String id, String fname, String lname, String email, String address, String phone, String pwd) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.pwd = pwd;
    }

    public String id, fname, lname, email, address, phone, pwd;
    
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
