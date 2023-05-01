/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

import ict.bean.AbstractBean;
import ict.db.AbstractDatabase;
import jakarta.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Ison Ho
 */
class Result {

    final static String good = "Successfully %s %s.", bad = "Failed to %s %s.";
    String gaction = null, baction = null, item = null;

    public Result() {

    }

    public Result renew(Result r, String item) {
        this.gaction = r.gaction;
        this.baction = r.baction;
        this.item = item;
        return this;
    }

    public Result(String gaction, String baction, String item) {
        this.gaction = gaction;
        this.baction = baction;
        this.item = item;
    }

    public String get(boolean b, String item) {
        if (gaction != null) {
            return b ? String.format(good, gaction, item) : String.format(bad, baction, item);
        } else {
            return "";
        }
    }

    public String get(boolean b) {
        return get(b, item);
    }
}


@RestController
public abstract class AbstractCRUDservice<D extends AbstractDatabase<B>, B extends AbstractBean<B>> {

    protected final static String dbUser = "root", dbPassword = "", dbUrl = "jdbc:mysql://localhost:3306/ITP4511";
    private D db;
    private Result view, create, edit, delete;

    protected abstract D createDB();
    
    @PostConstruct
    void init() {

        db = createDB();

        edit = new Result("edited", "edit", "a record");
        create = new Result("created", "create", "a record");
        view = new Result("retrived", "retrive", "a list of records");
        delete = new Result("removed", "remove", "a record");
    }

    @GetMapping("/test/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<Object> sayHello() {
        //Get data from service layer into entityList.

        List<JSONObject> entities = new ArrayList<JSONObject>();
        for (int i = 0 ; i < 10; i++) {
            JSONObject entity = new JSONObject();
            entity.put("aa", "bb");
            entity.put("cc", "bb");
            entity.put("dd", "bb");
            entity.put("ee", "bb");
            entities.add(entity);
        }
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object>create(@RequestBody B bean)
            throws URISyntaxException {
        B createdBean = db.create(bean);
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

    @GetMapping("/{id}")
    public ResponseEntity<B> read(@PathVariable("id") Long id) {
        B foundBean = db.read(id.toString());
        if (foundBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundBean);
        }
    }

    @PutMapping("/")
    public ResponseEntity<B> update(@RequestBody B bean) {
        B updatedBean = db.update(bean);
        if (updatedBean == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedBean);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        if (db.delete(id.toString()) == false) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }
}
