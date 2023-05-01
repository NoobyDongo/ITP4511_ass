/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.GuestBean;
import ict.db.GuestDB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ison Ho
 */
@RestController
@RequestMapping("/guest")
public class GuestCRUD extends AbstractCRUDservice<GuestDB, GuestBean> {

    @Override
    protected GuestDB createDB() {
        return new GuestDB(dbUrl, dbUser, dbPassword);
    }
    
}
