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

    public String id, memberid, status;
    public Timestamp createtime;
    public String image, guestlistid, notificationid;
    public Timestamp checkintime, checkouttime;
    public Date startdate;
    public int starthour, totalhour;
    public double fee;

    @Override
    public BookingBean update(ResultSet rs) throws SQLException, IOException {
        id = rs.getString(1);
        memberid = rs.getString("memberid");
        status = rs.getString("status");
        createtime = rs.getTimestamp("createtime");
        image = rs.getString("image");
        guestlistid = rs.getString("guestlistid");
        notificationid = rs.getString("notificationid");
        checkintime = rs.getTimestamp("checkintime");
        checkouttime = rs.getTimestamp("checkouttime");
        startdate = rs.getDate("startdate");
        starthour = rs.getInt("starthour");
        totalhour = rs.getInt("totalhour");
        fee = rs.getDouble("fee");
        return this;
    }

    @Override
    public String[] toStringArray() {
        return new String[]{id, memberid, status,
            createtime.toString(),
            image, guestlistid, notificationid,
            checkintime.toString(), checkouttime.toString(),
            startdate.toString(),
            starthour + "", totalhour + "",
            fee + ""};
    }

}
