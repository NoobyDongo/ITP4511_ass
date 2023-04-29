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
        super(url, username, password, "booking",
                "insert into member values(Default, ?, ?, ?, ?, ?, ?)",
                "UPDATE member set fname = ?, lname = ?, email = ?, address = ?, phone = ?, pwd = ? where id = ?");
    }

    @Override
    public BookingBean update(BookingBean bean) {
        return _update(bean, bean.toStringArray());
    }

    @Override
    public BookingBean create(BookingBean bean) {
        return _create(bean, bean.toStringArray(0, 6));
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
