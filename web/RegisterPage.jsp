<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        body{

            background-image: url("WallBackgroundSudoku.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;


        }
        div {
            width: 500px;
            padding: 25px 0;
            margin: auto;
            text-align: center;
            font-family: "Arial Black", Gadget, sans-serif;

        }
        h1{
            font-family: Impact, Charcoal, sans-serif;
            color: #00204c;
            text-shadow: 2px 3px indigo;

        }
        select {
            font-family: Impact, Charcoal, sans-serif;
            color: #00204c;

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
        .username, .firstname, .lastname {
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
        .email {
            background-image: url("https://img.icons8.com/windows/32/000000/email-sign.png");
            background-position: 0px 5px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }
        .country {
            background-image: url("https://img.icons8.com/windows/32/000000/globe.png");
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
<div>
    <h1><b> REGISTER </b></h1>
    <form action="RegisterServlet" method="post">
        <label><b><u>First Name</u></b></label>
        <br>
        <input class="firstname" type="text" placeholder="Enter First Name" name="Fname" autofocus required >
        <br>
        <label><b><u>Last Name</u></b></label>
        <br>
        <input class="lastname" type="text" placeholder="Enter Last Name" name="Lname" required>
        <br>
        <label><b><u>Username</u></b></label>
        <br>
        <input class="username" type="text" placeholder="Enter Username" name="username" required>
        <br>
        <label><b><u>Password</u></b></label>
        <br>
        <input class="psw" type="password" placeholder="Enter Password" name="Pw" required>
        <br>
        <label><b><u>Repeat Password</u></b></label>
        <br>
        <input class="psw" type="password" placeholder="Enter Password Again" name="Rpw" required>
        <br>
        <label><b><u>Email</u></b></label>
        <br>
        <input class="email" type="text" placeholder="Enter Email" name="email" required>
        <br>
        <label><b><u>Country</u></b></label>
        <br>
        <select class="country" name="Country">
            <option value="Israel">Israel</option>
            <option value="USA">USA</option>
            <option value="UK">UK</option>
            <option value="Germany">Germany</option>
            <option value="Hungary">Hungary</option>
            <option value="Canada">Canada</option>
            <option value="South Africa">South Africa</option>
            <option value="Other">Other</option>
        </select>
        <br>
        <br>
        <button type="submit" class="button">Register</button>
        <br>
        <p>Already have an account? <a href="LoginPage.jsp">Login</a>.</p>
    </form>
</div>

</body>
</html>
