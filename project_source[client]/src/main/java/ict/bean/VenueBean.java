/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Ison Ho
 */
public class VenueBean extends AbstractBean<VenueBean> {

    class TimeSpan {

        public int from = -1, to = -2;

        public TimeSpan() {
        }

        public TimeSpan(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    class DateSpan {

        public Date from, to;

        public DateSpan() {
        }

        public DateSpan(Date from, Date to) {
            this.from = from;
            this.to = to;
        }

        public ArrayList<Date> list() {
            ArrayList<Date> dates = new ArrayList<Date>();
            String str_date = "27/08/2018";
            String end_date = "22/09/2018";
            DateFormat formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = null;
            try {
                startDate = (Date) formatter.parse(str_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date endDate = null;
            try {
                endDate = (Date) formatter.parse(end_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long interval = 24 * 1000 * 60 * 60;
            long endTime = endDate.getTime();
            long curTime = startDate.getTime();
            while (curTime <= endTime) {
                dates.add(new Date(curTime));
                curTime += interval;
            }
            for (int i = 0; i < dates.size(); i++) {
                Date lDate = (Date) dates.get(i);
                String ds = formatter.format(lDate);
                System.out.println(" Date is ..." + ds);
            }
            return dates;
        }
    }

    public TimeSpan[] open = new TimeSpan[]{new TimeSpan(), new TimeSpan(), new TimeSpan(), new TimeSpan(), new TimeSpan(), new TimeSpan(), new TimeSpan()};
    public ArrayList<DateSpan> close = new ArrayList<>();

    public String staffname, email, name, address, desc, img, type;
    public int capacity;
    public double fee;
    public boolean hidden;

    public VenueBean setDateTime(ResultSet o, ResultSet c) throws SQLException, IOException {

        while (o.next()) {
            TimeSpan cur = open[o.getInt("weekdays")];
            cur.from = o.getInt("openinghour");
            cur.to = o.getInt("closinghour");
        }

        while (c.next()) {
            close.add(new DateSpan(c.getDate("start"), c.getDate("end")));
        }
        return this;
    }

    @Override
    public VenueBean update(ResultSet rs) throws SQLException, IOException {

        this.id = rs.getString("id");
        this.staffname = rs.getString("staffname");
        this.email = rs.getString("email");
        this.name = rs.getString("name");
        this.address = rs.getString("address");
        this.desc = rs.getString("desc");
        this.img = rs.getString("img");
        this.type = rs.getString("type");
        this.capacity = rs.getInt("capacity");
        this.fee = rs.getDouble("fee");
        this.hidden = "1".equals(rs.getString("hidden"));
        return this;
    }

    @Override
    public String[] toStringArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
