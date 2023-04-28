/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import ict.bean.BookingBean;
import ict.bean.VenueBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Ison Ho
 */
public class VenueListTag extends SimpleTagSupport {

    public String path;
    public ArrayList<VenueBean> venues = new ArrayList();

    public void setVenues(HttpServletRequest request) {
        path = request.getContextPath();
        Object o = request.getAttribute("venues");
        if (o != null) {
            venues = (ArrayList<VenueBean>) o;
        }
    }

    public VenueListTag() {
    }

    private String getIcon(String key) {
        return "<span class=\"material-symbols-rounded\">" + key +"</span>";
    }

    @Override
    public void doTag() {
        try {
            JspWriter out = getJspContext().getOut();

            out.print("<div class=\"venue\">");

            if (!venues.isEmpty()) {
                for (VenueBean venue : venues) {
                    StringBuilder sb = new StringBuilder("<div ishidden=" + venue.hidden + " class=\"block\">");
                    sb.append("<div class=\"img\"><img class=\"rounded\" src=\"").append(path).append("/img/venue/").append(venue.img).append(".jpg\"/></div>");
                   
                    sb.append("<div class=\"info\">");
                    
                    sb.append("<div class=\"name\">").append("").append("<p>").append(venue.name).append("</p></div>");
                    sb.append("<div class=\"address\">").append(getIcon("pin_drop")).append("<p>").append(venue.address).append("</p></div>");
                    sb.append("<div class=\"type\">").append("").append("<p>").append(venue.type).append("</p></div>");
                    sb.append("<div class=\"staffname\">").append(getIcon("support_agent")).append("<p>").append(venue.staffname).append("</p></div>");
                    sb.append("<div class=\"desc\">").append(getIcon("description")).append("<p>").append(venue.desc).append("</p></div>");
                    sb.append("<div class=\"capacity\">").append(getIcon("group")).append("<p>").append(venue.capacity).append("</p></div>");
                    sb.append("<div class=\"fee\">").append(getIcon("attach_money")).append("<p>").append(venue.fee).append("</p></div>");
                    
                    sb.append("<div class=\"book button\" t=\"main\" onclick=\"toBooking('").append(venue.id).append("');\"><span class=\"material-symbols-rounded\">in_home_mode</span>\n" +
"                            <p>Book</p>\n" +
"                        </div>");
                    
                    sb.append("<div class=\"button time\" t=\"main\" onclick=\"console.log('").append(venue.getOpenTime()).append("');\"><span class=\"material-symbols-rounded\">event_available</span>\n" +
"                            <p>View Opening Hours</p>\n" +
"                        </div>");
                    
                    sb.append("</div>");
                    
                    sb.append("</div>");
                    out.print(sb.toString());
                }
            } else {
                out.print("<div class=\"empty\">There are no avalible</div>");
            }
            out.print("</div>");
        } catch (IOException e) {
        }

    }
}
