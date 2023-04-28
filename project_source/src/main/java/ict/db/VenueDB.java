/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.AbstractBean;
import ict.bean.VenueBean;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ison Ho
 */
public class VenueDB extends AbstractDatabase<VenueBean> {

    public VenueDB(String url, String username, String password) {
        super(url, username, password, "venue");
    }

    protected boolean verify(Date date, int start, int time) {
        return false;
    }

    @Override
    protected VenueBean createBean() {
        return new VenueBean();
    }

    @Override
    public boolean update(VenueBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(VenueBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected SqlResultSet _queryByCol(String col, String value) {
        SqlResultSet rs = new SqlResultSet(tryHarder((SqlAction) () -> {
            st = new SqlStatement("select venue.*, staff.name as staffname from " + name + " inner join staff on " + name + ".staffid = staff.id where ? = ?", col, value);
            st.executeQuery();
        }, st), st.rs);
        return rs;
    }

    @Override
    protected SqlResultSet _queryByCol(String col, int value) {
        SqlResultSet rs = new SqlResultSet(tryHarder((SqlAction) () -> {
            st = new SqlStatement("select venue.*, staff.name as staffname from " + name + " inner join staff on " + name + ".staffid = staff.id where " + col + " = ?");
            st.pt.setInt(1, value);
            st.executeQuery();
        }, st), st.rs);
        return rs;
    }

    @Override
    public ArrayList<VenueBean> get(String col, String val) {
        ArrayList<VenueBean> list = new ArrayList();
        tryHarder((AbstractDatabase.SqlAction) () -> {
            AbstractDatabase.SqlResultSet rs = _queryByCol("hidden", 0);
            ResultSet r = rs.data;
            while (r.next()) {
                VenueBean b = createBean();
                b.update(r);
                SqlStatement o = getOpeningDays(b.id), c = getClosingDays(b.id);
                b.setTime(o.rs, c.rs);
                o.close();
                c.close();
                list.add(b);
            }
        }, st);
        return list;
    }

    @Override
    public VenueBean get(String id) {
        VenueBean b = new VenueBean();
        _queryByID(id, b);
        tryHarder((SqlAction) () -> {
            SqlStatement o = getOpeningDays(id), c = getClosingDays(id);
            b.setTime(o.rs, c.rs);
            o.close();
            c.close();
        }, null);
        return b;
    }

    public SqlStatement getOpeningDays(String id) throws SQLException, IOException {
        SqlStatement s = new SqlStatement("select weekdays, openinghour, closinghour from opening_days where venueid = ?", id);
        s.executeQuery();
        return s;
    }

    public SqlStatement getClosingDays(String id) throws SQLException, IOException {
        SqlStatement s = new SqlStatement("select start, end from closing_days where venueid = ?", id);
        s.executeQuery();
        return s;
    }

}
