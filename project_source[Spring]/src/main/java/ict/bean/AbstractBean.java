/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author Ison Ho
 * @param <T>
 */
public abstract class AbstractBean<T extends AbstractBean> implements Serializable{
    public String id = null;
    public String getId() {
        return this.id;
    }
    public abstract T update(ResultSet rs) throws SQLException, IOException ;
    public abstract String[] toStringArray();
    public String[] toStringArray(int start, int end){
        return Arrays.copyOfRange(toStringArray(), start, end);
    }
    
    public AbstractBean(){
        
    }
}
