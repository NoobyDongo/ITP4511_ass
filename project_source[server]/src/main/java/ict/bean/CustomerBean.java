package ict.bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author a1
 */
public class CustomerBean implements Serializable {

    
    private String custid;
    private String name;
    private String tel;
    private int age;

    public CustomerBean(String custid, String name, String tel, int age) {
        this.custid = custid;
        this.name = name;
        this.tel = tel;
        this.age = age;
    }

    public CustomerBean(){
    }
    
    public String getCustid(){
        return custid;
    }

    public String getName(){
        return name;
    }

    public String getTel(){
        return tel;
    }

    public int getAge(){
        return age;
    }

    public void setCustid(String id){
        custid=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setTel(String tel){
        this.tel=tel;
    }

    public void setAge(int age){
        this.age=age;
    }


}
