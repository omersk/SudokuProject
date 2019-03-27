package Model;

import java.sql.*;

public class DataBaseGood {
    private Connection connect;
    private Statement stt;
    private Connection conn1;
    private Statement stt1;
    private Connection con1;
    private ResultSet rs1;

    private static DataBaseGood dbcon;
    private DataBaseGood() {
        /*
        Init the database by opening a database if not exists or connecting to an old one

        Args:
        None

        Yields:
        Contructor           : init the database

         */
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
            stt = con1.createStatement();
            stt.executeUpdate("CREATE DATABASE IF NOT EXISTS sudokudb");
            conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
            stt1 = conn1.createStatement();
            stt1.executeUpdate("CREATE TABLE IF NOT EXISTS users"
                    + "(username varchar(50) not NULL UNIQUE," +
                    "firstname varchar(50) not NULL," +
                    "lastname varchar(50) not NULL," +
                    "email varchar(90) not NULL," +
                    "password varchar(50) not NULL," +
                    "country varchar(50) not NULL," +
                    "score int," +
                    "admin varchar(50) not NULL)");
            stt1.executeUpdate("CREATE TABLE IF NOT EXISTS sessionid" +
                    "(session varchar(500)," +
                    "username varchar(50) not NULL UNIQUE)");
            stt1.executeUpdate("CREATE TABLE IF NOT EXISTS topten" +
                    "(username varchar(50) not NULL UNIQUE," +
                    "country varchar(50) not NULL," +
                    "score int)");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } finally {
            try {
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
                PreparedStatement query = connect.prepareStatement("insert into users (username, firstname, lastname, email, password, country, score, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                query.setString(1, "Admin");
                query.setString(2, "Admin");
                query.setString(3, "Admin");
                query.setString(4, "Admin");
                query.setString(5, "Admin");
                query.setString(6, "Israel");
                query.setString(7, "0");
                query.setString(8, "Admin");
                query.executeUpdate();
            } catch (SQLException exc) {

            }
        }
    }
    public static DataBaseGood getInstance()
    {
        if(dbcon==null)
        {
            dbcon = new DataBaseGood();
        }
        return dbcon;
    }
    public int registerUser(String username, String firstname, String lastname, String password, String email, String country) throws SQLException {
        PreparedStatement query = connect.prepareStatement("insert into users (username, firstname, lastname, email, password, country, score, admin) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        query.setString(1, username);
        query.setString(2, firstname);
        query.setString(3, lastname);
        query.setString(4, email);
        query.setString(5, password);
        query.setString(6, country);
        query.setString(7, "0");
        query.setString(8, "NormalUser");
        try {
            query.executeUpdate();
            return 0;
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            return 1;
        }
    }

    public boolean loginUser(String Username, String password) {


        try {
            PreparedStatement query = connect.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            query.setString(1, Username);
            query.setString(2, password);
            rs1 = query.executeQuery();
            if (rs1.next()) {
                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    public void loginSession(String session, String username) throws SQLException {
        PreparedStatement query = connect.prepareStatement("insert into sessionid (session, username) VALUES (?, ?)");
        query.setString(1, session);
        query.setString(2, username);

        query.executeUpdate();
        //catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
        //    String b = "\'";
        //    username = b + username + b;
        //    session = b + session + b;
        //    String sql = "UPDATE sessionid SET username = " + username + " WHERE session = " + session;
        //    PreparedStatement query = con1.prepareStatement(sql);
        //    query.executeUpdate();
        //}
    }

    public void updateLoginSession(String session, String username) {
        String b = "\'";
        username = b + username + b;
        session = b + session + b;
        String sql = "UPDATE sessionid SET session = " + session + " WHERE username = " + username;
        QueryExecute(sql);
    }
    public void makeAdmin(String username) {
        String b = "\'";
        username = b + username + b;
        String sql = "UPDATE users SET admin = 'Admin' WHERE username = " + username;
        QueryExecute(sql);
    }
    public void zeroScore(String username) {
        String b = "\'";
        username = b + username + b;
        String sql = "UPDATE users SET score = '0' WHERE username = " + username;
        QueryExecute(sql);
    }


    public void deleteSession(String session) {
        String b = "\'";
        session = b + session + b;
        String sql = "DELETE FROM sessionid WHERE session = " + session;
        QueryExecute(sql);
    }

    public String deleteUser(String username) {
        String b = "\'";
        String type = this.loadUser(username)[6];
        username = b + username + b;
        if (type.equals("NormalUser")) {
            String sql = "DELETE FROM users WHERE username = " + username;
            PreparedStatement query = null;
            try {
                query = connect.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                query.executeUpdate();
                return "succeed";
            } catch (SQLException e) {
                e.printStackTrace();
                return "failed";
            }
        }
        else
        {
            return "failed";
        }
    }

    public String usernameBySession(String session2) {

        try {
            //PreparedStatement query = con1.prepareStatement("SELECT username FROM sessionid WHERE session = ?");
            //query.setString(1,session);

            //rs2 = query.executeQuery(q);
            //String username = rs2.getString("username");
            //return username;
            //con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
            Statement stmt = connect.createStatement();
            String b = "\'";
            session2 = b + session2 + b;
            String sql = "SELECT username FROM sessionid WHERE session = " + session2;
            ResultSet rs2 = stmt.executeQuery(sql);
            String username = "";
            while (rs2.next()) {
                username = rs2.getString("username");
            }
            return username;
        } catch (SQLException e) {
            e.printStackTrace();
            return "false";
        }
    }

    public String[] loadUser(String username) {

        try {
            //con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
            Statement stmt = connect.createStatement();
            String b = "\'";
            username = b + username + b;
            String sql = "SELECT * FROM users WHERE username = " + username;
            ResultSet rs3 = stmt.executeQuery(sql);
            String firstname = "";
            String lastname = "";
            String password = "";
            String email = "";
            String country = "";
            String score = "";
            String admin = "";
            while (rs3.next()) {
                firstname = rs3.getString("firstname");
                lastname = rs3.getString("lastname");
                password = rs3.getString("password");
                email = rs3.getString("email");
                country = rs3.getString("country");
                score = rs3.getString("score");
                admin = rs3.getString("admin");
            }
            String[] returnedarr = new String[7];
            returnedarr[0] = firstname;
            returnedarr[1] = lastname;
            returnedarr[2] = password;
            returnedarr[3] = email;
            returnedarr[4] = country;
            returnedarr[5] = score;
            returnedarr[6] = admin;
            return returnedarr;
        } catch (SQLException e) {
            e.printStackTrace();
            String[] faslearray = new String[7];
            return faslearray;
        }
    }

    public void updateScore(String username, int K) {
        String b = "\'";
        int currentscore = Integer.parseInt(loadUser(username)[5]);
        username = b + username + b;
        currentscore += K;
        String str_score = Integer.toString(currentscore);
        str_score = b + str_score + b;
        String sql = "UPDATE users SET score = " + Integer.toString(currentscore) + " WHERE username = " + username;
        QueryExecute(sql);
    }

    private void QueryExecute(String sql) {
        PreparedStatement query = null;
        try {
            query = connect.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] getAllUsers() {
        try {
            Statement stmt = connect.createStatement();
            String b = "\'";
            String sql = "SELECT username FROM users";
            ResultSet rs3 = stmt.executeQuery(sql);
            int i = 0;
            while (rs3.next()) {
                i += 1;
            }
            String[] returnedarr = new String[i];
            int j = -1;
            ResultSet rs3copy = stmt.executeQuery(sql);
            while (rs3copy.next()) {
                j+=1;
                returnedarr[j] = rs3copy.getString("username");
            }
            return returnedarr;
        } catch (SQLException e) {
            e.printStackTrace();
            String[] faslearray = new String[6];
            return faslearray;
        }
    }
    public void getTopTen(int[] returnscores, String[] returnplayers)
    {
        String sql = "SELECT username,score FROM users order by score DESC";
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;
            while (rs.next() && i<returnplayers.length&& i<returnscores.length)
            {
                returnplayers[i]=rs.getString("username");
                returnscores[i]=rs.getInt("score");
                i+=1;
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}