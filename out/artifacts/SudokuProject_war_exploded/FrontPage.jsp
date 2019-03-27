<%--
  Created by IntelliJ IDEA.
  User: ofeko
  Date: 10/01/2019
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="Control.Controller" %>
<%  HttpSession session2 = request.getSession();%>
<%  String textsession = session2.toString();
    String myherf = "";
    Controller dbcon = new Controller();
    String returnedvalue = dbcon.UsernameBySession(textsession);
    if(!returnedvalue.equals("false") && !returnedvalue.equals(""))
    {
        myherf = "MainPage.jsp";
    }
    else
    {
        myherf = "LoginPage.jsp";
    }%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        body{

            background-image: url("WallBackgroundSudoku.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }
        div {
            border: #111111;
            border-radius: 20px;
            font-family: "Arial Black", Gadget, sans-serif;
            top: 45%;
            left: 50%;
            position: absolute;
            transform: translate(-50%, -50%);
        }
        #id, header {
            display: block;
            position: sticky;
            text-align: center;
        }
        .button {
            display: inline-block;
            border-radius: 4px;
            background: darkgrey;
            border: none;
            color: #FFFFFF;
            text-align: center;
            font-size: 28px;
            padding: 20px;
            width: 200px;
            transition: all 0.5s;
            cursor: pointer;
            margin: 5px;
        }

        .button span {
            cursor: pointer;
            display: inline-block;
            position: relative;
            transition: 0.5s;
        }

        .button span:after {
            content: '\00bb';
            position: absolute;
            opacity: 0;
            top: 0;
            right: -20px;
            transition: 0.5s;
        }

        .button:hover span {
            padding-right: 25px;
        }

        .button:hover span:after {
            opacity: 1;
            right: 0;
        }
        header {
            display: block;
            font-family: "Arial Black", Gadget, sans-serif;
            font-size: 70px;
            text-shadow: 2px 3px indigo;
            color: #00204c;
            position: sticky;
            text-align: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

    </style>
</head>
<body>
<div>
    <header>
        Ready To Play Sudoku?
    </header>
    <section id="id">
        <a href= <%= myherf %> class="button"><span>Login</span></a>
        <br>
        <br>
        <a href="RegisterPage.jsp" class="button"><span>Register</span></a>
    </section>
</div>
</body>
</html>