<%@page import="ict.bean.GuestBean"%>
<!--
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/showGuests.tld" prefix="guests"%>
<%@taglib uri="/WEB-INF/tlds/showBookings.tld" prefix="bookings"%>
<%@taglib uri="/WEB-INF/tlds/showVenues.tld" prefix="venues"%>
<%@taglib uri="/WEB-INF/tlds/showMessage.tld" prefix="msg"%>
-->
<!DOCTYPE html>
<html>
    <input type="hidden" value="<%=((String) request.getSession().getAttribute("page"))%>" id="cpage"/>
    <input type="hidden" value="<%=((String) request.getSession().getAttribute("prompt"))%>" id="cprompt"/>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Start Page</title>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link type="text/css" href="https://getbootstrap.com/1.0.0/assets/css/bootstrap-1.0.0.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/js/bootstrap-datetimepicker.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.7.14/css/bootstrap-datetimepicker.min.css">

        <script>
            var dates = ["20/05/2023", "21/05/2023", "22/05/2023", "23/05/2023"];
            function DisableDates(date) {
                var string = jQuery.datepicker.formatDate('dd/mm/yy', date);
                return [dates.indexOf(string) === -1];
            }

            $(function () {
                $('#time').datetimepicker({
                    format: 'hh:mm a'
                });
                $('#datetime').datetimepicker({
                    format: 'DD/MM/YYYY'
                })
                $('#datetime1').datetimepicker({
                    format: 'DD/MM/YYYY'
                })
            });
        </script>

        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link href="./css/main.css" rel="stylesheet" />
        <link th:href="@{/css/main.css}" rel="stylesheet">
        <script src="./js/main.js"></script>

    </head>

    <body hidden>

        <!--nav-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <a class="navbar-brand" href="#"><img src="./img/logo.PNG"/></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                    aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav w-100">
                    <form class="nav-item"method="POST" action="request?">
                        <input type="hidden" name="action" value="showhome" />
                        <input class="nav-link" type="submit" value="Home" />
                    </form>
                    <form class="nav-item"method="POST" action="request?">
                        <input type="hidden" name="action" value="showvenue" />
                        <input class="nav-link" type="submit" value="Venues" />
                    </form>
                    </li>
                    <li class="nav-item dropdown ml-auto">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <div>
                                <%=request.getSession().getAttribute("username") == null ? "Guest" : request.getSession().getAttribute("username")%>
                            </div>
                            <span class="fill material-symbols-rounded">
                                account_circle
                            </span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">

                            <form method="POST" action="request?">
                                <input type="hidden" name="action" value="register">
                                <input class="dropdown-item" type="submit" value="Register Account">
                            </form>
                            <form method="POST" action="request?">
                                <input type="hidden" name="action" value="showbooking">
                                <input class="dropdown-item" type="submit" value="Manage Booking">
                            </form>
                            <form method="POST" action="request?">
                                <input type="hidden" name="action" value="showguest">
                                <input class="dropdown-item" type="submit" value="Manage Guest">
                            </form>
                            <form method="POST" action="request?">
                                <input type="hidden" name="action" value="logout">
                                <input class="dropdown-item" type="submit" value="Logout">
                            </form>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <%System.out.println("dwadawdaw" + ((String) request.getSession().getAttribute("message")));%>    
        <msg:showMessage message="<%=(String) request.getSession().getAttribute("message")%>"/>

        <!--prompt-container-->
        <div class="prompt-container">
            <!--prompt--><!--createGuest-->
            <div id="createGuest">
                <div class="header">
                    <h3>Create guest</h3>
                    <span class="material-symbols-rounded" onclick="closePrompt('createGuest');">
                        close
                    </span>
                </div>


                <form method="POST" action="request?">
                    <input type="hidden" name="action" value="createguest" />
                    <div>
                        <p>Name</p><input name="name" />
                    </div>
                    <div>
                        <p>Email Address</p><input name="email" type="email" />
                    </div>
                    <div class="options">
                        <input class="button" t="main" type="submit" value="Create" />
                        <input class="button" t="sub" type="reset" value="Cancel" onclick="closePrompt('createGuest');"/>
                    </div>
                </form>
            </div>

            <!--prompt--><!--editGuest-->
            <div id="editguest">
                <div class="header">
                    <h3>Edit guest</h3>
                    <span class="material-symbols-rounded" onclick="closePrompt('editguest');">
                        close
                    </span>
                </div>


                <form method="POST" action="request?">
                    <input type="hidden" name="action" value="editguest" />
                    <input type="hidden" name="id" />
                    <div>
                        <p>Name</p><input name="name" />
                    </div>
                    <div>
                        <p>Email Address</p><input name="email" type="email" />
                    </div>
                    <div class="options">
                        <input class="button" t="main" type="submit" value="Save" />
                        <input class="button" t="no" onclick="submitForm('deleteguest', 'editguest')" type="button" value="Delete"/>
                    </div>
                </form>
            </div>
        </div>

        <!--main-container-->
        <div class="main-container">

            <!--test-->
            <div id="test">
            </div>

            <!--front-->
            <div id="front">
                <h3>Welcome</h3>
                <p>We do business here.</p>    

                <input class="form-control" type="text" id="time"/>
                <input class="form-control" type="text" id="datetime1"/>


                <div class="form-group">
                    <div class='input-group date' id='datetime'>
                        <input type='text' class="form-control" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>

            <!--venue-->
            <div id="venue" class="collapse">
                <h3>Our Venues</h3>
                <venues:showVenues venues="<%=request%>"/>
            </div>

            <!--guest-->
            <div id="guest">
                <h3>Guest List</h3>
                <guests:showGuests guests="<%=request%>" type="full"/>

                <div class="options">
                    <input class="button" t="main" type="submit" value="Create" onclick="openPrompt('createGuest');">
                </div>
            </div>

            <!--booking-->
            <div id="booking">
                <h3>booking List</h3>

                <div class="options">
                    <input class="button" t="main" type="submit" value="Create" onclick="openPrompt('createbooking');">
                </div>
            </div>
            <!--createbooking-->
            <div id="createbooking">
                <h3>Create Booking</h3>
                <form method="POST" action="request?">
                    <input type="hidden" name="action" value="createbooking">

                    <div>
                        Date: <input name="date" class="form-control" type="text" id="datepicker" class="hasDatepicker"/>
                    </div>
                    <div>
                        Email Address:<input class="form-control" name="email" type="email">
                    </div>
                    <input class="button" t="main" type="submit" value="Create Booking">
                </form>
            </div>

            <!--login-->
            <div id="login">
                <div>
                    <h3>Login</h3>
                </div>

                <form method="post" action="request?">
                    <input type="hidden" name="action" value="login">
                    <div>
                        Email Address:<input class="form-control" name="email" type="email">
                    </div>
                    <div>
                        Password:<input class="form-control" type="password" name="pwd">
                    </div>
                    <input type="submit" value="login">
                </form>
            </div>

            <!--register-->
            <div id="register">
                <div>
                    <h3>Register an account</h3>
                </div>

                <form method="post" action="request?">
                    <input type="hidden" name="action" value="register">


                    <div>
                        Name:
                        <div class="input-group">
                            <input name="fname" type="text" placeholder="First name" aria-label="First name"
                                   class="form-control">
                            <input name="lname" type="text" placeholder="Last name" aria-label="Last name"
                                   class="form-control">
                        </div>
                    </div>
                    <div>
                        Email Address:<input class="form-control" name="email" type="email">
                    </div>
                    <div>
                        Address:<textarea class="form-control" name="address"></textarea>
                    </div>
                    <div>
                        Phone:<input class="form-control" type="tel" name="phone" pattern="[0-9]{8}">
                    </div>
                    <div>
                        Password:<input class="form-control" type="password" name="pwd">
                    </div>
                    <div>
                        Confirm Password:<input class="form-control" type="password">
                    </div>


                    <input type="submit" value="create">
                </form>
            </div>
        </div>


    </body>

</html>