/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;


import ict.bean.BookingBean;
import ict.bean.GuestListBean;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Ison Ho
 */


public class BookingDB extends AbstractDatabase<BookingBean>{
    
    public BookingDB(String url, String username, String password) {
        super(url, username, password, "booking",
                "insert into booking values(Default, ?, Default, Default, Default, "
                        + "?, ?, "
                        + "Default, Default, "
                        + "?, ?, ?, ?, "
                        + "?)",
                "UPDATE booking set statis = ?, image = ?, fee = ? where id = ? and memberid = ?");
    }
    
    public BookingBean updateCol(String id, String status, String col, String data){
        final BookingBean bean = new BookingBean();
        boolean r = tryHarder((AbstractDatabase.SqlAction) () -> {
            st = new SqlStatement("UPDATE booking set " + col +" = ?, status = ? where id = ?", data,status, id);
            st.executeUpdate();
            st.close();
        }, st) && st.rowCount > 0;
        return r? bean : null;
    }
    
    @Override
    public BookingBean update(BookingBean bean) {
        return _update(bean, bean.toStringArray());
    }

    @Override
    public BookingBean create(BookingBean bean) {
        return _create(bean, bean.toStringArray());
    }

    public GuestListBean createGuestList(GuestListBean b) {
        boolean r = tryHarder((AbstractDatabase.SqlAction) () -> {
            st = new SqlStatement("insert into guestlist values(default)");
            st.executeUpdate();
            ResultSet generatedKeys = st.pt.getGeneratedKeys();
            if (generatedKeys.next()) {
                b.id = generatedKeys.getString(1);
            }
            st.close();
            for(String i : b.guests){
                st = new SqlStatement("insert into guestlist_guest values(?,?)", b.id, i);
                st.executeUpdate();
                st.close();
            }
        }, null);
        return r? b : null;
    }

    @Override
    protected BookingBean createBean() {
        return new BookingBean();
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
