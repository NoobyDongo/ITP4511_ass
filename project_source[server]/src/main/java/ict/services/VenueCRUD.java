/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.VenueBean;
import ict.db.VenueDB;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ison Ho
 */
@RestController
@CrossOrigin
@RequestMapping("/venue")
public class VenueCRUD extends AbstractCRUDservice<VenueDB, VenueBean> {
    
    @Override
    protected VenueDB createDB() {
        return new VenueDB(dbUrl, dbUser, dbPassword);
    }
}
