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

    public String memberid,name, email;

    public GuestBean(String id, String memberid, String name, String email) {
        this.id = id;
        this.memberid = memberid;
        this.name = name;
        this.email = email;
    }
    public GuestBean() {
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
