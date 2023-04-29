/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.MemberBean;
import ict.db.MemberDB;

/**
 *
 * @author Ison Ho
 */
public class MemberCRUD extends AbstractCRUDservices<MemberDB, MemberBean> {

    @Override
    protected MemberDB createDB() {
        return new MemberDB(dbUrl, dbPassword, dbUser);
    }
    
}
