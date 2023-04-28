/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.BookingBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Ison Ho
 */
public class BookingListTag extends SimpleTagSupport {

    public ArrayList<BookingBean> bookings = new ArrayList();

    public void setBookings(HttpServletRequest request) {
        Object o = request.getAttribute("bookings");
        if (o != null) {
            bookings = (ArrayList<BookingBean>) o;
        }
    }


    @Override
    public void doTag() {

        try {
            JspWriter out = getJspContext().getOut();
            out.print("<div class=\"bookinglist\">");
            if (!bookings.isEmpty()) {
                    String html = "<table class=\"table\">\n"
                        + "  <thead>\n"
                        + "    <tr>\n"
                        + "      <th scope=\"col\"></th>\n"
                        + "      <th scope=\"col\">Name</th>\n"
                        + "      <th scope=\"col\">Email</th>\n"
                        + "      <th scope=\"col\"></th>\n"
                        + "    </tr>\n"
                        + "  </thead>\n"
                        + "  <tbody>\n";

                int count = 1;
                for (BookingBean b : bookings) {
                    html += "\n"
                            + "    <tr>\n"
                            + "      <th scope=\"row\">" + count++ + "</th>\n"
                            + "      <td>" + "a" + "</td>\n"
                            + "      <td>" + "<input class=\"button\" onclick=\"editGuest('');\"t=\"main\" value=\"Edit\" />" + "</td>\n"
                            + "    </tr>";
                }
                html += "</tbody>\n"
                        + "</table>";
                out.print(html);
                
            }else{
                
                out.print("There are no guest");
            }

            out.print("</div>");

        } catch (IOException e) {
        }

    }
}

