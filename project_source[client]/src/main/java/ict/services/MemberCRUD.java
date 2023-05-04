/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.MemberBean;
import ict.db.MemberDB;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ison Ho
 */
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberCRUD extends AbstractCRUDservice<MemberDB, MemberBean> {

    @PostMapping("/verify")
    public ResponseEntity<MemberBean> verify(@RequestBody MemberBean bean) {
        MemberBean foundBean = db.verify(bean.email, bean.pwd);
        if (foundBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundBean);
        }
    }
    @Override
    protected MemberDB createDB() {
        return new MemberDB(dbUrl, dbUser, dbPassword);
    }
    
}
