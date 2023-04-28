/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ison Ho
 */
public class GuestBean extends AbstractBean<GuestBean>{

    public GuestBean() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String id, memberid,name, email;

    public GuestBean(String id, String memberid, String name, String email) {
        this.id = id;
        this.memberid = memberid;
        this.name = name;
        this.email = email;
    }
    
    @Override
    public GuestBean update(ResultSet rs) throws SQLException, IOException {
        id = rs.getString(1);
        memberid = rs.getString(2);
        name = rs.getString(3);
        email = rs.getString(4);
        return this;
    }
    
    @Override
    public String[] toStringArray(){
        return new String[]{name, email, id, memberid};
    }
}
