<%@ page import="Control.Controller" %><%--
  Created by IntelliJ IDEA.
  User: ofeko
  Date: 10/01/2019
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  HttpSession session2 = request.getSession();
%>
<%  String textsession = session2.toString();
    Controller dbcon = new Controller();
    String returnedvalue = dbcon.UsernameBySession(textsession);
    if(!returnedvalue.equals("false") && !returnedvalue.equals(""))
    {
        response.sendRedirect("MainPage.jsp");
    }
    %>
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
            width: 500px;
            border: 15px #221112;
            padding: 25px 0;
            margin: auto;
            text-align: center;
            font-family: "Arial Black", Gadget, sans-serif;



        }
        h1{
            text-shadow: 2px 3px indigo;
            color: #00204c;
            font-family: Impact, Charcoal, sans-serif;
        }
        label{
            color: #00204c;

        }

        input {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 2px solid black;
            border-radius: 4px;
            font-family: Impact, Charcoal, sans-serif;


        }
        .username {
            background-image: url("https://img.icons8.com/windows/32/000000/gender-neutral-user.png");
            background-position: 0px 5px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }
        .psw {
            background-image: url("https://img.icons8.com/windows/32/000000/password.png");
            background-position: 0px 5px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }
        .button {
            display: inline-block;
            border-radius: 4px;
            background: rgba(168, 255, 215, 0.85);
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

    </style>
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>

<div>
    <h1><b> Login </b></h1>
    <br>

    <form action="LoginServlet" method="post">
        <label><b><u>Username</u></b></label>
        <br>
        <input class="username" type="text" placeholder="Username" name="Username" required>
        <br>
        <label><b><u>Password</u></b></label>
        <br>
        <input class="psw" type="password" placeholder="Password" name="Password" required>
        <br>
        <button class="button" type="submit">Login</button>
        <br>
        <p>Don't have an account? <a href="RegisterPage.jsp">Register</a>.</p>
    </form>
</div>

</body>
</html>
