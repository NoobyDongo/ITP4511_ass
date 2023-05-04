/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.BookingBean;
import ict.bean.GuestBean;
import ict.bean.GuestListBean;
import ict.db.BookingDB;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Ison Ho
 */
@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingCRUD extends AbstractCRUDservice<BookingDB, BookingBean> {

    @GetMapping("/ALL/{id}")
    public ResponseEntity<ArrayList<BookingBean>> readBatch(@PathVariable("id") Long id) {
        ArrayList<BookingBean> foundBean = db.read("memberid", id.toString());
        if (foundBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundBean);
        }
    }
    
    @PostMapping("/guestlist")
    public ResponseEntity<Object> createGuestList(@RequestBody GuestListBean bean) throws URISyntaxException {
        GuestListBean createdBean = db.createGuestList(bean);
        if (createdBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdBean.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(createdBean);
        }
    }
    
    @PostMapping("/receipt")
    public ResponseEntity<Object> receipt(@RequestBody BookingBean bean) throws URISyntaxException {
        BookingBean createdBean = db.updateCol(bean.id, bean.status, "image", bean.image);
        if (createdBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdBean.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(createdBean);
        }
    }
    
    @PostMapping("/remark")
    public ResponseEntity<Object> remark(@RequestBody BookingBean bean) throws URISyntaxException {
        BookingBean createdBean = db.updateCol(bean.id, bean.status, "remark", bean.remark);
        if (createdBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdBean.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(createdBean);
        }
    }
    
    @Override
    protected BookingDB createDB() {
        return new BookingDB(dbUrl, dbUser, dbPassword);
    }
    
}
