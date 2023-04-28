<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div><h2>Login Please</h2></div>

        <form method="post" action="request?">
            <input type="hidden" name="action" value="login"/>
            <div>
                Email Address:<input name="email" type="email"/>
            </div>
            <div>
                Password:<input type="password" name="pwd"/>
            </div>
            <input type="submit" value="login">
        </form>

        <div><h2>Register an account</h2></div>

        <form method="post" action="request?">
            <input type="hidden" name="action" value="register"/>

            <div>
                Name:<input name="fname"/>
            </div>
            <div>
                Surname:<input name="lname"/>
            </div>
            <div>
                Email Address:<input name="email" type="email"/>
            </div>
            <div>
                Address:<textarea name="address"></textarea>
            </div>
            <div>
                Phone:<input type="tel" name="phone" pattern="[0-9]{8}"/>
            </div>
            <div>
                Password:<input type="password" name="pwd"/>
            </div>
            <div>
                Confirm Password:<input type="password"/>
            </div>


            <input type="submit" value="create"/>
        </form>

    </body>
</html>
