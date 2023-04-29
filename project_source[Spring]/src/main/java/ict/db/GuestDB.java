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
public class GuestDB extends AbstractDatabase<GuestBean> implements linkedDatabase<GuestBean> {

    public GuestDB(String url, String username, String password) {
        super(url, username, password, "guest",
                "insert into guest values(Default, ?, ?, ?)",
                "UPDATE guest set name = ?, email = ? where id = ? and memberid = ?");
    }

    @Override
    public GuestBean update(GuestBean bean) {
        return _update(bean, bean.toStringArray());
    }

    @Override
    public GuestBean create(GuestBean bean) {
        return _create(bean, bean.memberid, bean.name, bean.email);
    }

    @Override
    public boolean delete(String id) {
        return tryHarder((SqlAction) () -> {
            _updateDB("delete from guest where id = ?", id);
        }, st);
    }

    @Override
    public ArrayList<GuestBean> readBatch(String id) {
        ArrayList<GuestBean> list = new ArrayList<>();
        tryHarder((SqlAction) () -> {
            st = new SqlStatement("select id, name, email from guest where memberid = ?", id);
            SqlResultSet rs = _query(st);
            ResultSet r = rs.data;
            while (r.next()) {
                GuestBean b = new GuestBean(r.getString(1), id, r.getString(2), r.getString(3));
                list.add(b);
            }
        }, st);
        return list;
    }

    @Override
    protected GuestBean createBean() {
        return new GuestBean();
    }
}
