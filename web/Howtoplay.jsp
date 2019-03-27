<%--
  Created by IntelliJ IDEA.
  User: ofeko
  Date: 26/12/2018
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" language="java" %>
<%@ page import="Model.DataBaseGood" language="java" %>
<%@ page import="Control.Controller" %>
<% HttpSession session2 = request.getSession();
%>
<%  String MySession = session2.toString();
    Controller dbcon = new Controller();
    String username = dbcon.UsernameBySession(MySession);
    if(username.equals(""))
        response.sendRedirect("/SudokuProject_war_exploded/");
    String[] details = dbcon.LoadUser(username);%>


<html>
<head>
    <title>MainPage</title>
    <style>
        header {


        }
        #intro{
            background: rgba(80, 80, 80, 0.4);
            display: block;
            text-align: center;
            line-height: 30px;
        }
        firststep{
            width: 600px;
            margin: auto;
            background: rgba(139, 0, 0, 0.3);
            display: block;
            line-height: 30px;
            text-align: left;
        }
        h2{
            text-align: center;
            margin: auto;
            text-shadow: 1px 2px darkred;

        }
        body{
            background-image: url("SudokuBG.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            font-family: "Arial Black", Gadget, sans-serif;
            background-attachment: fixed;

        }
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: darkred;
            position: sticky;
            top: 0;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }

        li a:active {
            background-color: #4CAF50;
        }
        li {
            text-align: center;
            border-bottom: 1px solid #555;
        }

        li:last-child {
            border-bottom: none;
        }

    </style>
</head>
<body>
<header>
    <br><br><br><br><br><br><br>

</header>
<nav>
    <ul>
        <li><a class="active" href="MainPage.jsp">Home</a></li>
        <li><a href="User.jsp">User</a></li>
        <li><a href="Contact.jsp">Contact</a></li>
        <li><a href="RankingTable.jsp">Top players</a></li>
        <% if (details[6].equals("Admin")) { %>
        <li><a href="Admin.jsp">Admin</a></li>
        <%}%>
        <li style="float:right"><a href="ExitServlet">Sign Out</a></li>
    </ul>
</nav>

<br>
<h2>Objective:</h2>
<firststep>
    The objective is to fill a 9x9 grid so that each column, each row, and each of the nine 3x3 boxes
    (also called blocks or regions) contains the digits from 1 to 9.
    <br>

    A cell is the smallest block in the game. A row , column and region consists of 9 cells and the whole game consists
    of 81 cells. A region has thicker lines surrounding it. This simply makes it easier to play the game.
</firststep>
<br>
<h2>How to:</h2>
<firststep>
    most basic strategy to find missing numbers is scanning and it consists of
    <br>1)Cross-hatching<br>
    2)Counting.<br>

    1) Crosshatching. You scan rows and columns to eliminate where a specific number can be in a given region.
    As you can see there is only one legal place left for the 1(marked green).
    <br>

    2)Counting. In counting you simply count all the different numbers that's in a row,column and region that connects to one cell.
    if there is just one number missing then that's what should be in the cell. Take a look at the second picture and see if you can figure out the missing number in the green cell.
</firststep>

</body>
</html>
