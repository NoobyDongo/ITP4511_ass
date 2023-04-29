/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.GuestBean;
import ict.db.GuestDB;

/**
 *
 * @author Ison Ho
 */
public class GuestCRUD extends AbstractCRUDservices<GuestDB, GuestBean> {

    @Override
    protected GuestDB createDB() {
        return new GuestDB(dbUrl, dbPassword, dbUser);
    }
    
}
