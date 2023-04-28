<%-- 
    Document   : editCustomer
    Created on : Mar 30, 2023, 1:52:45 PM
    Author     : 210016047
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            input[readonly]{
                background-color: yellow;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
                pointer-events: none;
            }
        </style>
    </head>
    <body>
        <jsp:useBean id="c" class="ict.bean.CustomerBean" scope="request"/>
        <%
            String custID = c==null? "" : c.getCustid();
            //String id, name, tel, age;
            
            boolean hasCust = custID != null;
            String type = hasCust? "edit" : "add", 
                    id = hasCust? custID : "",
                    name = hasCust? c.getName() : "",
                    tel = hasCust? c.getTel() : "",
                    age = hasCust? Integer.toString(c.getAge()) : "";
        %>
        <form  method=“get" action="handleEdit">
            <input type="hidden" name="action"  value="<%=type%>" />
            ID  <input name="id"  type="text" value="<%=id%>" <%=hasCust? "readonly" : ""%>/> <br>
            Name <input name="name"  type="text" value="<%=name%>"/> <br>
            Tel <input name="tel"  type="text" value="<%=tel%>"/> <br>
            Age <input name="age"  type="text" value="<%=age%>"/> <br>
            <td ><input type="submit" value="submit"/> <br>
    </form>

            <jsp:include page="footer.jsp"/>
    </body>
</html>
