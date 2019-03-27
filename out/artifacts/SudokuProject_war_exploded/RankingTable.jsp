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

<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>

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
        table{
            width: 400px;
            table-layout: fixed;
            display: block;
            text-align: center;
            margin: auto;
            border-collapse: collapse;
            border: #111111 3px;

        }
        thead{
            background: rgba(139,0,0,0.6);
            padding: 15px;
            font-weight: 500;
            font-size: 12px;
            color: rgba(0, 0, 0, 0.8);
            text-transform: uppercase;
        }
        td{
            padding: 15px;
            vertical-align:middle;
            font-weight: 300;
            font-size: 12px;
            color: rgba(0, 0, 0, 0.8);
            border-bottom: solid 3px rgba(255,255,255,0.1);
        }
        tr:nth-child(even){background: rgba(139, 0, 0, 0.3)}

        section{
            margin: 50px;
        }

        ::-webkit-scrollbar {
            width: 6px;
        }
        ::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        }
        ::-webkit-scrollbar-thumb {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        }
    </style>
</head>
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
<body>
<br>
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Score</th>
                <th>Country</th>
            </tr>
            </thead>
            <%
                for(int i = 0; i<10; i++){
            %>
            <tr>
                <td> <%=i+1%> </td>
                <td> <% int[] scores = new int[10];
                        String[] players = new String[10];
                        dbcon.getTopTen(scores,players);

                %>
                    <%if(players[i]!= null)
                    {
                     %>
                    <%=players[i]%>
                    <%
                    }%>

                        </td>
                <td>
                    <%if(players[i]!= null)
                    {
                    %>
                    <%=scores[i]%>
                    <%
                        }%>
                </td>
                <td>
                    <%if(players[i]!= null)
                    {
                    %>
                    <%=dbcon.LoadUser(players[i])[4]%>
                    <%
                        }%>


                </td>
                <% } %>
            </tr>
        </table>
</body>
</html>
