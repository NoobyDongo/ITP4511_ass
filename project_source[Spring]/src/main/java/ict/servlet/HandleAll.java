package ict.servlet;

import ict.bean.*;
import ict.db.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    private Result view, create, edit, delete;

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

        venues = vdb.read("", "");
        
        edit = new Result("edited", "edit", "a record");
        create = new Result("created", "create", "a record");
        view = new Result("retrived", "retrive", "a list of records");
        delete = new Result("removed", "remove", "a record");
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

            MemberBean m = new MemberBean();
            ArrayList<MemberBean> a = mdb.read("email", email);
            if (a.size() < 1) {
                mdb.create(m);
            }
            args.forward("register");
        }
        if (args.equals("auth")) {

        }
        if (args.equals("logout")) {
            if (hasLogin) {
                session.invalidate();
                args.session = request.getSession(true);
            }
            args.forward("front");
        }
        if (args.equals("login")) {

            if (hasLogin) {
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
            setMessage(session, "Please login.");
            request.setAttribute("redirect", indexPage);
            args.forward("login");
        } else {
            if (!manageGuest(args).msg.equals("")) {
                setMessage(session, args.msg);
            }
        }
    }

    class Result{
        final static String good = "Successfully %s %s.", bad = "Failed to %s %s.";
        String gaction = null, baction = null, item = null;

        public Result(){
            
        }
        public Result renew(Result r, String item){
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
        
        public String get (boolean b, String item){
            if(gaction != null)
                return b? String.format(good, gaction, item) : String.format(bad, baction, item);
            else return "";
        }
        
        public String get(boolean b){
            return get (b, item);
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

        Result r = new Result();
        if (args.equals("showguest")) {
            args.setStatus(true);
            r = r.renew(view, "guest list from database");
        } else {

            String id = args.getParameter("id");
            String name = args.getParameter("name");
            String email = args.getParameter("email");
            GuestBean bean = new GuestBean(id, args.userid, name, email);

            if (args.equals("editguest")) {
                //args.setStatus(gdb.update(bean));
            r = r.renew(edit, "a guest");
            }
            if (args.equals("createguest")) {
                //args.setStatus(gdb.create(bean));
            r = r.renew(create, "a new guest");
            }
            if (args.equals("deleteguest")) {
                //args.setStatus(gdb.delete(bean));
            r = r.renew(create, "a guest from the database");
            }
        }
        if (args.tried) {
            args.msg = r.get(args.status);
            args.setAttribute("guests", gdb.readBatch(args.userid));
            args.forward("guest");
        }
        return args;
    }
    
}
