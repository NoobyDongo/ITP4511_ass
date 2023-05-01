/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.MemberBean;
import ict.db.MemberDB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ison Ho
 */
@RestController
@RequestMapping("/member")
public class MemberCRUD extends AbstractCRUDservice<MemberDB, MemberBean> {

    @Override
    protected MemberDB createDB() {
        return new MemberDB(dbUrl, dbUser, dbPassword);
    }
    
}
