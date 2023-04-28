/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Ison Ho
 */
public class MessageTag extends SimpleTagSupport {

    public String message = "";

    public void setMessage(String message) {
        if(message != null)
            this.message = message;
    }

    public MessageTag() {
    }

    @Override
    public void doTag() {
        if(!"".equals(message))
        try {
            JspWriter out = getJspContext().getOut();
            StringBuilder sb = new StringBuilder("<div class=\"collapse\" id=\"message\">");
            sb.append(message);
            sb.append("</div>");
            out.print(sb.toString());
        } catch (IOException e) {
        }

    }
}
