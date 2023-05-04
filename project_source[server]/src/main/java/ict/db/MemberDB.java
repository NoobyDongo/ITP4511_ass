package ict.db;

import ict.bean.MemberBean;

public class MemberDB extends AbstractDatabase<MemberBean> {

    public MemberDB(String url, String username, String password) {
        super(url, username, password, "member",
                "insert into member values(Default, ?, ?, ?, ?, ?, 0)",
                "UPDATE member set fname = ?, lname = ?, email = ?, phone = ?, pwd = ? where id = ?");
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
    public MemberBean update(MemberBean bean) {
        return _update(bean, bean.toStringArray());
    }

    @Override
    public MemberBean create(MemberBean bean) {
        return _create(bean,bean.toStringArray(0, 5));
    }

    @Override
    protected MemberBean createBean() {
        return new MemberBean();
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
