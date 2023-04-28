package ict.servlet;

import ict.bean.*;
import ict.db.*;
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

/**
 *
 * @author Ison Ho
 */
@WebServlet(name = "HandleAll", urlPatterns = {"/request"})
public class HandleAll extends HttpServlet {

    private String loginPage, indexPage;

    private GuestDB gdb;
    private MemberDB mdb;
    private VenueDB vdb;
    private BookingDB bdb;
    private ArrayList<VenueBean> venues = new ArrayList();

    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");

        loginPage = "/" + this.getServletContext().getInitParameter("login");
        indexPage = "/" + this.getServletContext().getInitParameter("index");

        gdb = new GuestDB(dbUrl, dbUser, dbPassword);
        mdb = new MemberDB(dbUrl, dbUser, dbPassword);
        vdb = new VenueDB(dbUrl, dbUser, dbPassword);
        bdb = new BookingDB(dbUrl, dbUser, dbPassword);

        venues = vdb.get("", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /*
    protected String fromUrl(HttpServletRequest request, HttpServletResponse response, String key) throws ServletException, IOException {

        String value = request.getParameter(key);

        if (value == null || value.trim().length() <= 0) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/error?error=Empty+Input+Value:+" + key);
            rd.forward(request, response);
            return null;
        }

        return value;
    }
     */
    protected static String fromUrl(HttpServletRequest request, HttpServletResponse response, String key) throws ServletException, IOException {

        String value = request.getParameter(key);

        if (value == null || value.trim().length() <= 0) {
            request.getSession().setAttribute("message", "Please fill in all the required information.");
            return null;
        }

        return value;
    }

    private static void setMessage(HttpSession session, String message) {
        session.setAttribute("message", message);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        boolean hasLogin = userid != null;
        Args args = new Args(request, response, action, userid);
        setMessage(session, "");

        //for testing
        session.setAttribute("userid", "1");
        session.setAttribute("username", "a" + " " + "a");

        if (args.equals("register")) {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String pwd = request.getParameter("pwd");

            MemberBean m = new MemberBean(null, fname, lname, email, address, phone, pwd);
            ArrayList<MemberBean> a = mdb.get("email", email);
            if (a.size() < 1) {
                mdb.add(m);
            }
            args.forward("register");
        }
        if (args.equals("auth")) {

        }
        if (args.equals("logout")) {
            if (hasLogin) {
                session.invalidate();
                args.session = request.getSession(true);
                //request.setAttribute("customers", customers);
            }
            args.forward("front");
        }
        if (args.equals("login")) {

            if (hasLogin) {
                //request.setAttribute("customers", customers);
                args.forward("front");
            } else {

                String email = request.getParameter("email");
                String pwd = request.getParameter("pwd");
                MemberBean member = mdb.verify(email, pwd);

                if (member.id != null) {
                    session.setAttribute("userid", member.id);
                    session.setAttribute("username", member.fname + " " + member.lname);
                }
                args.forward("front");
            }
        }
        if (args.equals("showvenue")) {
            request.setAttribute("venues", venues);
            args.forward("venue");
        }

        if (args.equals("showhome")) {
            args.forward("front");
        }

        if (args.tried) {
            return;
        }

        if (!hasLogin) {
            setMessage(session, "no login");
            request.setAttribute("redirect", indexPage);
            args.forward("login");
        } else {
            if (!manageGuest(args).msg.equals("")) {
                setMessage(session, args.msg);
            }
        }
    }

    class Args {

        String userid, msg = "", action = "";
        boolean status = false, tried = false;
        HttpServletRequest request;
        HttpServletResponse response;
        HttpSession session;

        public Args(HttpServletRequest request, HttpServletResponse response, String action, String userid) {
            this.userid = userid;
            this.request = request;
            this.response = response;
            this.action = action;
            session = request.getSession();
        }

        public boolean equals(String s) {
            if (tried) {
                return false;
            }
            tried = s.equalsIgnoreCase(action);
            return tried;
        }

        public Args setStatus(boolean b) {
            return setStatus(b, "", "");
        }

        public Args setStatus(boolean b, String t, String f) {
            status = b;
            msg = b ? t : f;
            return this;
        }

        public void setAttribute(String key, Object o) {
            request.setAttribute(key, o);
        }

        public String getParameter(String key) {
            return request.getParameter(key);
        }

        public void forward(String page) {
            forward(null, page);
        }

        public void forward(String prompt, String page) {

            session.setAttribute("page", page);
            session.setAttribute("prompt", prompt);

            if (!"".equals(msg)) {
                setMessage(session, msg);
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher(indexPage);
            try {
                rd.forward(request, response);
            } catch (Exception e) {

            }
        }
    }

    private Args manageGuest(Args args) throws IOException, ServletException {

        String gaction = "", baction = "";
        String item = "";
        if (args.equals("showguest")) {
            args.setStatus(true);
            gaction = "retrived";
            baction = "retrive";
            item = "guest list from database";
        } else {

            String id = args.getParameter("id");
            String name = args.getParameter("name");
            String email = args.getParameter("email");
            GuestBean bean = new GuestBean(id, args.userid, name, email);

            if (args.equals("editguest")) {
                args.setStatus(gdb.update(bean));
                gaction = "edited";
                baction = "edit";
                item = "a guest";
            }
            if (args.equals("createguest")) {
                args.setStatus(gdb.add(bean));
                gaction = "created";
                baction = "create";
                item = "a new guest";
            }
            if (args.equals("deleteguest")) {
                args.setStatus(gdb.delete(bean));
                gaction = "removed";
                baction = "remove";
                item = "a guest from the database";
            }
        }
        if (args.tried) {
            args.msg = args.status ? String.format("Successfully %s %s.", gaction, item) : String.format("Failed to %s %s.", baction, item);
            args.setAttribute("guests", gdb.getBatch(args.userid));
            args.forward("guest");
        }
        return args;
    }
}
