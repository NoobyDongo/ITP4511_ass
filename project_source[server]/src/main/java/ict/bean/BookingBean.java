/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Ison Ho
 */
public class BookingBean extends AbstractBean<BookingBean> {

    public String memberid, status;
    public String createtime;
    public String image, guestlistid, remark;
    public String checkintime, checkouttime;
    public Date startdate;
    public int starthour, totalhour;
    public double fee;
    public String template;

    @Override
    public BookingBean update(ResultSet rs) throws SQLException, IOException {
        id = rs.getString(1);
        memberid = rs.getString("memberid");
        status = rs.getString("status");
        createtime = rs.getString("createtime");
        image = rs.getString("image");
        guestlistid = rs.getString("guestlistid");
        remark = rs.getString("remark");
        checkintime = rs.getString("checkintime");
        checkouttime = rs.getString("checkouttime");
        startdate = rs.getDate("startdate");
        starthour = rs.getInt("starthour");
        totalhour = rs.getInt("totalhour");
        fee = rs.getDouble("fee");
        template = rs.getString("template");
        return this;
    }

    @Override
    public String[] toStringArray() {
        return new String[]{memberid,
            guestlistid, remark,
            startdate.toString(),
            starthour + "", totalhour + "",
            fee + "", template};
    }
}
