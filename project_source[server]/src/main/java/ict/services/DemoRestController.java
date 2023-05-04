package ict.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ict.bean.CustomerBean;
import ict.db.CustomerDB;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class DemoRestController {

    /*
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getCustomer(@PathVariable String id) {
        String url = "jdbc:mysql://localhost:3306/ITP4511_DB";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        CustomerBean cb = custDb.queryCustByID(id);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;
        try {
            json = ow.writeValueAsString(cb);
        } catch (JsonProcessingException e) {
            json = "{\"message\":\"failure\"}";
        }
        return json;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST, produces = "application/json")
    public String addCustomer(@RequestParam(value = "id", defaultValue = "0") String id,
            @RequestParam(value = "name", defaultValue = "Peter") String name,
            @RequestParam(value = "tel", defaultValue = "12345678") String tel,
            @RequestParam(value = "age", defaultValue = "18") String age) {

        String url = "jdbc:mysql://localhost:3306/ITP4511_DB";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        int ageNo = Integer.parseInt(age);
        custDb.addRecord(id, name, tel, ageNo);
        String json = "{\"message\":\"success\"}";
        return json;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT, produces = "application/json")
    public String editCustomer(@RequestParam(value = "id", defaultValue = "0") String id,
            @RequestParam(value = "name", defaultValue = "Peter") String name,
            @RequestParam(value = "tel", defaultValue = "12345678") String tel,
            @RequestParam(value = "age", defaultValue = "18") String age) {

        String url = "jdbc:mysql://localhost:3306/ITP4511_DB";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        int ageNo = Integer.parseInt(age);
        CustomerBean cb = new CustomerBean(id, name, tel, ageNo);
        custDb.editRecord(cb);
        String json = "{\"message\":\"success\"}";
        return json;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.DELETE, produces = "application/json")
    public String editCustomer(@RequestParam(value = "id", defaultValue = "0") String id) {

        String url = "jdbc:mysql://localhost:3306/ITP4511_DB";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        custDb.delRecord(id);
        String json = "{\"message\":\"success\"}";
        return json;
    }
*/
}
