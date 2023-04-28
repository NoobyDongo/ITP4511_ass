/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.BookingBean;

/**
 *
 * @author Ison Ho
 */
public class BookingDB extends AbstractDatabase<BookingBean>{
    public BookingDB(String url, String username, String password) {
        super(url, username, password, "booking");
    }

    @Override
    public boolean update(BookingBean bean) {
        return tryHarder((AbstractDatabase.SqlAction) () -> {
            st = new AbstractDatabase.SqlStatement("UPDATE member set fname = ?, lname = ?, email = ?, address = ?, phone = ?, pwd = ? where id = ?", bean.toStringArray());
            _updateDB();
        }, st) && st.rowCount > 0;
    }

    @Override
    public boolean add(BookingBean bean) {
        return tryHarder((AbstractDatabase.SqlAction) () -> {
            _insertOrDeleteRecord("insert into member values(Default, ?, ?, ?, ?, ?, ?)", bean.toStringArray(0, 6));
        }, st);
    }

    @Override
    public BookingBean get(String id) {
        BookingBean b = new BookingBean();
        return _queryByID(id, b);
    }

    @Override
    protected BookingBean createBean() {
        return new BookingBean();
    }
}
