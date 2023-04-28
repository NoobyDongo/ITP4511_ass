/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.GuestBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Ison Ho
 */
public class GuestListTag extends SimpleTagSupport {

    public ArrayList<GuestBean> guests = new ArrayList();
    public String type = "simple";

    public void setGuests(HttpServletRequest request) {
        Object o = request.getAttribute("guests");
        if (o != null) {
            guests = (ArrayList<GuestBean>) o;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public GuestListTag() {
    }

    @Override
    public void doTag() {

        try {
            JspWriter out = getJspContext().getOut();
            out.print("<div class=\"guestlist\">");
            if (!guests.isEmpty()) {
                if ("simple".equalsIgnoreCase(type)) {
                    StringBuilder sb = new StringBuilder();
                    for (GuestBean b : guests) {
                        sb.append("<div draggable=\"true\" class=\"block\">");
                        sb.append("<div class=\"name\">");
                        sb.append(b.name);
                        sb.append("</div>");
                        sb.append("<div class=\"email\">");
                        sb.append(b.email);
                        sb.append("</div></div>");
                    }
                    out.print(sb.toString());
                } else if ("full".equalsIgnoreCase(type)) {
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
                    for (GuestBean b : guests) {
                        html += "\n"
                                + "    <tr>\n"
                                + "      <th scope=\"row\">" + count++ + "</th>\n"
                                + "      <td>" + b.name + "</td>\n"
                                + "      <td>" + b.email + "</td>\n"
                                + "      <td>" + "<input class=\"button\" onclick=\"editGuest('" + b.id + "','" + b.name + "','" + b.email + "');\"t=\"main\" value=\"Edit\" />" + "</td>\n"
                                + "    </tr>";
                    }
                    html += "</tbody>\n"
                            + "</table>";
                    out.print(html);
                }
            }else{
                
                out.print("<div class=\"empty\">There are no guest</div>");
            }

            out.print("</div>");

        } catch (IOException e) {
        }

    }
}
