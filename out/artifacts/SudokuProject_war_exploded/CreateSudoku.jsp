<%--
  Created by IntelliJ IDEA.
  User: ofeko
  Date: 2/15/2019
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.*" %>
<%@ page import="Control.Controller" %>
<% HttpSession session2 = request.getSession();%>
<%  String MySession = session2.toString();
    Controller dbcon = new Controller();

    String username = dbcon.UsernameBySession(MySession);
    if(username.equals(""))
        response.sendRedirect("/SudokuProject_war_exploded/");
    String[] details = dbcon.LoadUser(username);
    String[] users = dbcon.GetAllUsers();
%>

<html>
<head>
    <title>CreateSudoku</title>
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

        #Sudoku{
            font-family: "Arial Black", Gadget, sans-serif;
            display: block;
            width: 450px;
            margin-left:auto;
            margin-right:auto;
        }
        table{
            text-align: center;
            position: sticky;
            border: 2px solid black;
            background: rgba(139, 139,139, 0.5);
            border-collapse: collapse;
        }

        td {
            height:42px;
            width:42px;
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
        input{
            height:42px;
            width:42px;
            text-align:center;
            background-color: rgba(139, 139,139, 0.5);
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
<section id="intro">
    <h3><b>Here you can create your own SUDOKU! Enter your SUDOKU, Select Difficulty and just let us do the magic
        <br>Don't know how to play SUDOKU? Don't know what is Sudoku? Press <a href="Howtoplay.jsp">HERE</a></b></h3>
</section>
<%
    Controller con = new Controller();
    int[][] SF = con.CreateSudoku(0).getSoduko();
%>

<section id="Sudoku">
    <form action="CheckSudokuServlet" method="post">
        <table>
            <%int n =9;
                for(int s = 0; s<n; s++){
            %>
            <tr>
                <% for(int f=0; f<n; f++)
                {
                %>
                <td>
                    <%  int z = SF[s][f];
                        if(z==0) {
                            // meaning SF[s][f] was deleted
                    %>
                    <%String str = "Z[" + String.valueOf(s) + "][" + String.valueOf(f) + "]";%>
                    <input type="number" name = "<%=str%>" required="required" min="1" max="9"> <!-- than we give the user input -->
                    <%
                    }
                    else {
                        // there is a value there
                    %>
                    <%=SF[s][f]%>
                    <%String str = "z[" + String.valueOf(s) + "][" + String.valueOf(f) + "]";
                    %>
                    <input type="hidden" name = "<%=str%>" value="<%=SF[s][f]%>">
                    <%}%>
                </td>
                <% } %>
            </tr>
            <% } %>
        </table>
</section>

</body>
</html>

