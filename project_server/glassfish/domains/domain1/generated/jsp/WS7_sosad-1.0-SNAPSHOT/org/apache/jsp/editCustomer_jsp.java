package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class editCustomer_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("            input[readonly]{\n");
      out.write("                background-color: yellow;\n");
      out.write("                -webkit-user-select: none;\n");
      out.write("                -moz-user-select: none;\n");
      out.write("                -ms-user-select: none;\n");
      out.write("                user-select: none;\n");
      out.write("                pointer-events: none;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      ict.bean.CustomerBean c = null;
      synchronized (request) {
        c = (ict.bean.CustomerBean) _jspx_page_context.getAttribute("c", PageContext.REQUEST_SCOPE);
        if (c == null){
          c = new ict.bean.CustomerBean();
          _jspx_page_context.setAttribute("c", c, PageContext.REQUEST_SCOPE);
        }
      }
      out.write("\n");
      out.write("        ");

            String custID = c==null? "" : c.getCustid();
            //String id, name, tel, age;
            
            boolean hasCust = custID != null;
            String type = hasCust? "edit" : "add", 
                    id = hasCust? custID : "",
                    name = hasCust? c.getName() : "",
                    tel = hasCust? c.getTel() : "",
                    age = hasCust? Integer.toString(c.getAge()) : "";
        
      out.write("\n");
      out.write("        <form  method=“get\" action=\"handleEdit\">\n");
      out.write("            <input type=\"hidden\" name=\"action\"  value=\"");
      out.print(type);
      out.write("\" />\n");
      out.write("            ID  <input name=\"id\"  type=\"text\" value=\"");
      out.print(id);
      out.write('"');
      out.write(' ');
      out.print(hasCust? "readonly" : "");
      out.write("/> <br>\n");
      out.write("            Name <input name=\"name\"  type=\"text\" value=\"");
      out.print(name);
      out.write("\"/> <br>\n");
      out.write("            Tel <input name=\"tel\"  type=\"text\" value=\"");
      out.print(tel);
      out.write("\"/> <br>\n");
      out.write("            Age <input name=\"age\"  type=\"text\" value=\"");
      out.print(age);
      out.write("\"/> <br>\n");
      out.write("            <td ><input type=\"submit\" value=\"submit\"/> <br>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
