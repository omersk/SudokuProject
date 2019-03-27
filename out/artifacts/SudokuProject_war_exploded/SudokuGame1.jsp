<%--
  Created by IntelliJ IDEA.
  User: ofeko
  Date: 2/15/2019
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" language="java" %>
<%@ page import="Model.DataBaseGood" language="java" %>
<%@ page import="Control.Controller" %>
<% HttpSession session2 = request.getSession(); %>
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
    <title>SudokuGame1</title>
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
            width: 42px;
            height: 42px;
            background: rgba(139, 139,139, 0.5);
            border: black 0px;
        }
        body{
            background-color: gray;
            background-repeat: no-repeat;
            background-size: cover;
            font-family: "Arial Black", Gadget, sans-serif;
            text-align: center;
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

<section id="Menu">
    <form action="SelectDifficultyServlet" method="post">
        <select class="button" name="Difficulty">
            <option value="Easy">EASY</option>
            <option value="Medium">MEDIUM</option>
            <option value="Hard">HARD</option>
        </select>
        <button type="submit" class="button">SELECT</button>
    </form>
</section>
    <% String K = (String) request.getAttribute("K");
    int val = 20;
    try {
        val = Integer.parseInt(K);
    }
    catch (Exception e)
    {
        val = 20;
    }
    Controller con = new Controller();
    int[][] SF = con.CreateSudoku(val).getSoduko();
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
        <button type="submit" class="button">Check Sudoku</button>
    </form>
</section>

</body>
</html>

