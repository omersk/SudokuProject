<%--
  Created by IntelliJ IDEA.
  User: ofeko
  Date: 26/12/2018
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" language="java" %>
<%@ page import="Model.SudokuGenerator" %>
<%@ page import="Control.Controller" %>
<%  HttpSession session2 = request.getSession();
    String MySession = session2.toString();
    Controller dbcon = new Controller();
    String username = dbcon.UsernameBySession(MySession);
    if(username.equals(""))
        response.sendRedirect("/SudokuProject_war_exploded/");
    String[] details = dbcon.LoadUser(username);
%>


<html>
<head>
    <title>User</title>
    <style>

        header {


        }

        h2{
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

        table{
            text-align: center;
            position: sticky;
            border: 2px solid black;
            background: rgba(139, 139,139, 0.5);
            border-collapse: collapse;
            margin: auto;
            color: #111111;
            font-family: Impact, Charcoal, sans-serif;
            transform: rotate(-4deg);
            -ms-transform: rotate(-4deg);

        }

        td {
            height: 60px;
            width: 60px;
            border:2px solid black;
            text-align:center;
        }
        td:first-child {
            border-left:solid #8b0000 6px;
        }
        td:nth-child(3n) {
            border-right:solid darkred 6px ;
        }
        tr:first-child {
            border-top:solid darkred 6px;
        }
        tr:nth-child(3n) td {
            border-bottom:solid darkred 6px;

        }
        Sudoku{
            color: rgba(51,51,51,0.4);
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
<!-- User's information page -->
<br>
<%
    Controller con = new Controller();
    int[][] SF = con.CreateSudoku(0).getSoduko();
%>
<table>
    <%int n =9;
        for(int s = 0; s<n; s++){
    %>
    <tr>
        <% for(int f=0; f<n; f++)
        {
        %>
        <td>
            <% if(s==1 && f ==1) {%>
            <h2>Hi <%=username %></h2>
            <% } else {if(s==3 && f==3) { %>
            <h2>First Name:</h2> <%%>
            <% } else { if(s==3 && f==5) { %>
            <h2>Last Name:</h2> <%%>
            <% } else {if(s==4 && f==3) { %>
            <b><%=details[0]%></b>
            <% } else { if(s==4 && f==5) { %>
            <b><%=details[1]%></b>
            <% } else {if(s==4 && f==7) { %>
            <h2>Score:</h2>
            <% } else { if(s==4 && f==8) { %>
            <b><%=details[5]%></b>
            <% } else { if(s==5 && f==3) { %>
            <h2>Email:</h2>
            <% } else { if(s==5 && f==5) { %>
            <b><%=details[3]%></b>
            <% } else { if(s==6 && f==3) { %>
            <h2>Password:</h2>
            <% } else { if(s==6 && f==5) { %>
            <b><%=details[2]%></b>
            <% } else { if(s==7 && f==3) { %>
            <h2>Country:</h2>
            <% } else { if(s==7 && f==5) { %>
            <b><%=details[4]%></b>
            <% } else { %>
            <Sudoku><%=SF[s][f]%></Sudoku>
            <%}}}}}}}}}}}}}%>
        </td>
        <% } %>
    </tr>
    <% } %>
</table>
</body>
</html>
