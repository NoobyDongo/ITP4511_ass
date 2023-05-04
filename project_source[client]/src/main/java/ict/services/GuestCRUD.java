/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.GuestBean;
import ict.db.GuestDB;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ison Ho
 */
@RestController
@CrossOrigin
@RequestMapping("/guest")
public class GuestCRUD extends AbstractCRUDservice<GuestDB, GuestBean> {

    @Override
    protected GuestDB createDB() {
        return new GuestDB(dbUrl, dbUser, dbPassword);
    }
    
    @GetMapping("/ALL/{id}")
    public ResponseEntity<ArrayList<GuestBean>> readBatch(@PathVariable("id") Long id) {
        ArrayList<GuestBean> foundBean = db.readBatch(id.toString());
        if (foundBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundBean);
        }
    }
    
}
