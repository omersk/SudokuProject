<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>

        h2{
            color: #00204c;
            text-align: center;
        }
        body {

            background-image: url("WallBackgroundSudoku.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;

        }
        div2{
            text-align: center;
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
    </style>
</head>
<body>
<div>
<h2><b>Unsuccessful Register attempt: <br> Password didn't match</b></h2>
    <div2>
<form action="/SudokuProject_war_exploded/" method="post">
    <label><b></b></label>
    <input class="button" type="submit" value="Main Page">
</form>
    </div2>
</div>
</body>
</html>