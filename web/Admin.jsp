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
<%@ page import="java.sql.SQLException" %>
<%
    /*
    The following command are in every (after login) page and came up in order to avoid entering to
    the pages without being connected
     */
%>
<% HttpSession session2 = request.getSession();
    %>
<%  String MySession = session2.toString();
    Controller dbcon = new Controller();
    String username = dbcon.UsernameBySession(MySession);
    if(username.equals("")) {
        response.sendRedirect("/SudokuProject_war_exploded/");
        return;
    }
    String[] details = dbcon.LoadUser(username);
    if(!details[6].equals("Admin")) {
        response.sendRedirect("FrontPage.jsp");
        return;
    }
    String[] users = dbcon.GetAllUsers();
%>


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
        #Menu{
            display: block;
            text-align: center;
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
        .button {
            display: inline-block;
            border-radius: 4px;
            background: darkred;
            border: none;
            color: #FFFFFF;
            text-align: center;
            font-size: 28px;
            padding: 20px;
            width: 350px;
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

        .username {
            background-image: url("https://img.icons8.com/windows/32/000000/iron-man.png");
            background-position: 0px 5px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }
        WarningBox{
            border: black;
            border-radius: 4px;
            background: rgba(200, 10, 10, 0.5);
            display: block;
            text-align: center;
            width: 500px;
            left: 50%;
            margin: auto;
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
<section id="intro">
    <h3><b>Welcome to the Admin page, here you can choose an account and do some actions on him
        <br>Here are all the accounts that registered to the website, choose one please</b></h3>
</section>
<section id="Menu">
    <br>
    <form action="AdminServlet">
    <select class="username" name="username">
        <% for (int i = 0; i<users.length; i++)
        {
        %>
        <option value=<%=users[i]%>> <%=dbcon.LoadUser(users[i])[6] + " : " + users[i]%></option>
        <%}%>
    </select>
        <br>
        <br>
        <input type="submit" name="button" value="Make Admin" class = button />
        <br>
        <br>
        <input type="submit" name="button" value="Remove" class = button />
        <br>
        <%
            System.out.println(request.getAttribute("msg"));
            if(request.getAttribute("msg") != null && !request.getAttribute("msg").equals("")) { %>
        <WarningBox>
            <h3>Warning!</h3>
            <p><%=request.getAttribute("msg")%></p>
        </WarningBox>
        <%} else
        {
            %> <br> <%
        }
        %>
        <input type="submit" name="button" value="Zero Score" class = button />

    </form>
</section>
</body>
</html>
