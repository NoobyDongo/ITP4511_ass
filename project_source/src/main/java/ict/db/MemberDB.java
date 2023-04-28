package ict.db;

import ict.bean.MemberBean;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDB extends AbstractDatabase<MemberBean> {

    public MemberDB(String url, String username, String password) {
        super(url, username, password, "member");
    }
    
    public MemberBean verify(String email, String password){
        final MemberBean bean = new MemberBean();
        tryHarder((SqlAction) () -> {
            st = new SqlStatement("select * from member where email = ? and pwd = ?", email, password);
            if(_query(st).status){
            if(st.rs.next())
                bean.update(st.rs);
            else
                bean.id = null;
            }
        }, st);
        return bean;
    }

    @Override
    public boolean update(MemberBean bean) {
        return tryHarder((SqlAction) () -> {
            st = new SqlStatement("UPDATE member set fname = ?, lname = ?, email = ?, address = ?, phone = ?, pwd = ? where id = ?", bean.toStringArray());
            _updateDB();
        }, st) && st.rowCount > 0;
    }

    @Override
    public boolean add(MemberBean bean) {
        return tryHarder((SqlAction) () -> {
            _insertOrDeleteRecord("insert into member values(Default, ?, ?, ?, ?, ?, ?)", bean.toStringArray(0, 6));
        }, st);
    }

    @Override
    public MemberBean get(String id) {
        MemberBean b = new MemberBean();
        return _queryByID(id, b);
    }

    @Override
    protected MemberBean createBean() {
        return new MemberBean();
    }
}
