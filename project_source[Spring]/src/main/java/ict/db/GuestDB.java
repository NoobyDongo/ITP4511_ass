/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.GuestBean;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ison Ho
 */
public class GuestDB extends AbstractDatabase<GuestBean> implements linkedDatabase<GuestBean>{

    public GuestDB(String url, String username, String password) {
        super(url, username, password, "guest");
    }
    
    @Override
    public boolean update(GuestBean bean) {
        return tryHarder((SqlAction) () -> {
            st = new SqlStatement("UPDATE guest set name = ?, email = ? where id = ? and memberid = ?", bean.toStringArray());
            _updateDB();
        },st) && st.rowCount > 0;
    }

    @Override
    public boolean add(GuestBean bean) {
        return tryHarder((SqlAction) () -> {
            _insertOrDeleteRecord("insert into guest values(Default, ?, ?, ?)",bean.memberid, bean.name, bean.email);
        },st);
    }
    
    public boolean delete(GuestBean bean) {
        return tryHarder((SqlAction) () -> {
            _insertOrDeleteRecord("delete from guest where id = ?",bean.id);
        },st);
    }

    @Override
    public ArrayList<GuestBean> getBatch(String id) {
        ArrayList<GuestBean> list = new ArrayList();
        tryHarder((SqlAction) () -> {
            st = new SqlStatement("select id, name, email from guest where memberid = ?", id);
            SqlResultSet rs = _query(st);
            ResultSet r = rs.data;
            while(r.next()){
                GuestBean b = new GuestBean(r.getString(1), id, r.getString(2), r.getString(3));
                list.add(b);
            }
        },st);
        return list;
    }

    @Override
    public GuestBean get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected GuestBean createBean() {
        return new GuestBean();
    }
}
