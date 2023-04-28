<%-- 
    Document   : customers
    Created on : Mar 30, 2023, 2:51:52 PM
    Author     : 210016047
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="handleCustomer?action=search">
        <input type="hidden" name="action" value="search"/>
        Search customer: <br/>
        <input name="name"
        type="text" value=""/>
        <input type="submit" value="submit"/> </form>
    </body>
</html>
