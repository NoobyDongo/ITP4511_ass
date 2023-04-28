<%-- 
    Document   : listCustomer
    Created on : Mar 30, 2023, 1:49:10 PM
    Author     : 210016047
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.CustomerBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
                     ArrayList<CustomerBean> customers = (ArrayList<CustomerBean> )request.getAttribute("customers");
                    out.println("<h1>Customers</h1>");
                    out.println("<table border='1'>");
                    out.println("<tr>");
                    out.println("<th>CustId</th>  <th> name</th><th> tel</th><th> age</th >");
                    out.println("</tr>");
	  // loop through the customer array to display each customer record
                    for (int i = 0; i < customers.size(); i++) {
                        CustomerBean c = customers.get(i);
                        out.println("<tr>");
                        out.println("<td>" + c.getCustid()+ "</td>");
                        out.println("<td>" + c.getName() + "</td>");
                        out.println("<td>" + c.getTel() + "</td>");
                        out.println("<td>" + c.getAge() + "</td>");
                        out.println("<td><a href=\"handleCustomer?action=delete&id=" + c.getCustid()+ "\">delete</td>");
                        out.println("<td><a href=\"handleCustomer?action=getEditCustomer&id=" + c.getCustid()+ "\">edit</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
        %>
            <jsp:include page="footer.jsp"/>




    </body>
</html>
