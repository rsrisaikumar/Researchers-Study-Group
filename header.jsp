<%--
	Document: aboutl.jsp
	Created On: Feb 4, 2016
	Authors: Deepak Rohan, Abhishek

 --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <%-- title of the Page--%>
        <title>Researchers Exchange Participations</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="styles/main.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro|Open+Sans+Condensed:300|Raleway' rel='stylesheet' type='text/css'>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script><!-- jQuery Library-->

        
        <!-- BootStrap -->
        
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
        integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous"> -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        
        <script type="text/javascript">
        </script>
    </head>
    <body>
        
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <ul  class="left" >
                    <li><form action="UserController"><input type="hidden" name="action" value="join"><input type="submit" class="transbutton"  value='Researchers Exchange Participations'></form></li>
                </ul>
                <ul class="right">
                    <c:if test="${theUser.getName() == null && theAdmin.getName()==null}">
                        <li><a href="about.jsp">About Us</a></li>
                        <li><a href="how.jsp">How it Works</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        </c:if>
                        <c:if test="${theUser.getName() != null && theAdmin.getName()==null}">
                        <li><a href="aboutl.jsp?user=Hello,${theUser.getName()}">About Us</a></li>
                        <li><a href="main.jsp?user=Hello,${theUser.getName()}">How it Works</a></li>
                        <li>Hello, ${theUser.getName()}</li>
                        <li><form action="UserController" method="post">
                                <input type="hidden" name="action" value="logout">
                                    <input type="submit" class="transbutton1" value="Log out">
                        </form>
                        </li>
                        </c:if>
                        <c:if test="${theUser.getName() == null && theAdmin.getName()!=null}">
                        <li><a href="aboutl.jsp?user=Hello,${theAdmin.getName()}">About Us</a></li>
                        <li><a href="admin.jsp?user=Hello,${theAdmin.getName()}">How it Works</a></li>
                        <li>Hello, ${theAdmin.getName()}</li>
                         <li><form action="UserController" method="post">
                                <input type="hidden" name="action" value="logout">
                                    <input type="submit" class="transbutton2" value="Log out">
                        </form>
                        </c:if>
                </ul>

            </nav>



        </div>

