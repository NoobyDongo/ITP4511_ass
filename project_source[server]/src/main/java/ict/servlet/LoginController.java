package ict.servlet;

import ict.bean.*;
import ict.db.*;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ison Ho
 */
@Controller
public class LoginController{

    private MemberDB mdb;

    @PostConstruct
    public void init() {
        mdb = new MemberDB("jdbc:mysql://localhost:3306/ITP4511", "root", "");
    }

    @PostMapping("/verify")
    public String process(@RequestParam("user") MemberBean user, HttpSession session) {
        String id = (String)session.getAttribute("userid");

        if (id == null) {
            if(mdb.verify(user.email, user.pwd) != null)
                return "200";
            return "400";
        }
        return "200";
    }
}
