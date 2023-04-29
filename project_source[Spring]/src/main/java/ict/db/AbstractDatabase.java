/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.db;

import ict.bean.AbstractBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ison Ho
 * @param <T>
 */
interface linkedDatabase<T>{
    
    public ArrayList<T> readBatch(String id);
}

public abstract class AbstractDatabase<T extends AbstractBean<T>> {

    interface SqlAction {

        void run() throws SQLException, IOException;
    }
    
    protected class SqlData<K>{
        
        public K data;
    }
    
    protected class SqlStatement {

        public Connection cnnct = null;
        public Statement st = null;
        public PreparedStatement pt = null;
        public int rowCount = -1;
        public ResultSet rs = null;

        public SqlStatement(String placeholder, String... s) throws SQLException, IOException {
            this(placeholder);
            int count = 1;
            for(int i = 0 ; i < pt.getParameterMetaData().getParameterCount(); i++){
                pt.setString(count++, s[i]);
            }
        }
        
        public SqlStatement(String placeholder) throws SQLException, IOException {
            cnnct = getConnection();  // the connection 
            pt = cnnct.prepareStatement(placeholder);  // create statement
        }

        public SqlStatement() throws SQLException, IOException {
            cnnct = getConnection();  // the connection 
            st = cnnct.createStatement();  // create statement
        }

        public void close() {
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException e) {
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                }
            }
            if (pt != null) {
                try {
                    pt.close();
                } catch (SQLException e) {
                }
            }
        }

        public void execute(String sql) throws SQLException {
            System.out.println(st);
            st.execute(sql);
        }

        public void executeUpdate() throws SQLException {
            System.out.println(pt);
            rowCount = pt.executeUpdate();
        }

        public void executeQuery() throws SQLException {
            System.out.println(pt);
            rs = pt.executeQuery();
        }
    }

    protected class SqlResultSet {

        public ResultSet data;
        public boolean status;

        public SqlResultSet(boolean status, ResultSet data) {
            this.data = data;
            this.status = status;
        }

        public void set(ResultSet rs) {
            data = rs;
        }

        public void set(boolean s) {
            status = s;
        }
    }

    protected boolean tryHarder(SqlAction action, SqlStatement sql) {
        boolean status = false;
        try {
            action.run();
            status = true;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(sql != null)
                sql.close();
        }
        return status;
    }

    private String url;
    private String username;
    private String password;
    protected String name;
    protected SqlStatement st = null;
    protected String updateSql, createSql;

    protected AbstractDatabase(String url, String username, String password, String name, String createSql, String updateSql) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.name = name;
        this.createSql = createSql;
        this.updateSql = updateSql;
    }

    private Connection getConnection() throws SQLException, IOException {
        try {
            //  System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, username, password);
    }

    public boolean createTable(String... cols) {
        return tryHarder((SqlAction) () -> {
            st = new SqlStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + name + "(";
            for (String s : cols) {
                sql += s + ",";
            }
            sql = sql.substring(0, sql.length() - 1) + ")";
            System.out.println(sql);
            st.execute(sql);
        }, st);
    }

    protected boolean _updateDB() throws SQLException{
        st.executeUpdate();
        return st.rowCount > 0;
    }
    
    protected boolean _updateDB(String sql, String... args){
        return tryHarder((SqlAction) () -> {
            st = new SqlStatement(sql, args);
            st.executeUpdate();
        }, st) && st.rowCount > 0;
    }

    protected SqlResultSet _queryByCol(String col, String value) {
        SqlResultSet rs = new SqlResultSet(tryHarder((SqlAction) () -> {
            st = new SqlStatement("select * from " + name + " where ? = ?", col, value);
            st.executeQuery();
        },st), st.rs);
        return rs;
    }
    
    protected SqlResultSet _queryByCol(String col, int value) {
        SqlResultSet rs = new SqlResultSet(tryHarder((SqlAction) () -> {
            st = new SqlStatement("select * from " + name + " where " + col +" = ?");
            st.pt.setInt(1, value);
            st.executeQuery();
        },st), st.rs);
        return rs;
    }
    
    protected T _queryByID(String id) {
        T bean = createBean();
        tryHarder((SqlAction) () -> {
            SqlResultSet rs = _queryByCol("id", id);
            ResultSet r = rs.data;
            if(r.next()){
                bean.update(r);
            }
        },st);
        return bean;
    }
    
    protected SqlResultSet _query(SqlStatement sql) {
        return new SqlResultSet(tryHarder((SqlAction) () -> {
            sql.executeQuery();
        }, null), sql.rs);
    }
    
    protected abstract T createBean();
    
    public abstract boolean delete(String id);
    public abstract T update(T bean);
    public abstract T create(T bean);
    
    
    protected T _update(T bean, String... args) {
        boolean r = tryHarder((AbstractDatabase.SqlAction) () -> {
            st = new SqlStatement(updateSql, args);
            _updateDB();
        }, st) && st.rowCount > 0;
        return r? bean : null;
    }

    protected T _create(T bean, String... args) {
        boolean r = tryHarder((AbstractDatabase.SqlAction) () -> {
            _updateDB(createSql, args);
        }, st);
        return r? bean : null;
    }
    public T read(String id){
        T bean = _queryByID(id);
        return bean.getId() == null? null:bean;
    }
    public ArrayList<T> read(String col, String val){
        ArrayList<T> list = new ArrayList<>();
        tryHarder((AbstractDatabase.SqlAction) () -> {
            SqlResultSet rs = _queryByCol(col, val);
            ResultSet r = rs.data;
            while (r.next()) {
                T b = createBean();
                b.update(r);
                list.add(b);
            }
        }, st);
        return list;
    }
}
