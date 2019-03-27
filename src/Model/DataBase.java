/*
package Model;
import java.sql.*;


public class DataBase {
    private Connection conn;
    private Statement stt;
    private Connection conn1;
    private Statement stt1;
    private Connection con1;
    private Connection con2;
    private Connection con3;
    private Statement st1;
    private ResultSet rs1;

    public DataBase() {
        System.out.println("********************************************************************************");
        try {
            System.out.println("31");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("31");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
            if (con1==null)
            {
                System.out.println("31");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
                System.out.println("32");
                stt=conn.createStatement();
                System.out.println("33");
                String DBCreate = "CREATE DATABASE sudokudb";
                System.out.println("34");
                stt.executeQuery(DBCreate);
                System.out.println("35");
                conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
                System.out.println("36");
                stt1=conn1.createStatement();
                System.out.println("37");
                String UsersTableCreate = "CREATE TABLE users"
                                        + "(username varchar(50) not NULL UNIQUE," +
                                        "firstname varchar(50) not NULL," +
                                        "lastname varchar(50) not NULL," +
                                        "email varchar(90) not NULL," +
                                        "password varchar(50) not NULL," +
                                        "country varchar(50) not NULL)";
                System.out.println("38");
                String SessionTableCreate = "CREATE TABLE sessionid" +
                        "(session varchar(500)," +
                        "username varchar(50) not NULL UNIQUE)";
                System.out.println("39");
                stt.executeQuery(UsersTableCreate);
                System.out.println("40");
                stt.executeQuery(SessionTableCreate);


            }
            st1 = con1.createStatement();


        } catch (ClassNotFoundException e) {
            System.out.println("1");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("2");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("3");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("4");
            e.printStackTrace();
        }
    }

    public int RegisterUser(String username, String firstname, String lastname, String password, String email, String country) {
        try {
            PreparedStatement query = con1.prepareStatement("insert into users (username, firstname, lastname, email, password, country) VALUES (?, ?, ?, ?, ?, ?)");
            query.setString(1, username);
            query.setString(2, firstname);
            query.setString(3, lastname);
            query.setString(4, email);
            query.setString(5, password);
            query.setString(6, country);

            query.executeUpdate();
            return 0;

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1) {
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public boolean loginUser(String Username, String password) {


        try {
            PreparedStatement query = con1.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            query.setString(1, Username);
            query.setString(2, password);

            rs1 = query.executeQuery();

            return rs1.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void LoginSession(String session, String username) throws SQLException {
        try {
            PreparedStatement query = con1.prepareStatement("insert into sessionid (session, username) VALUES (?, ?)");
            query.setString(1, session);
            query.setString(2, username);

            query.executeUpdate();
        }
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            String b = "\'";
            username = b + username + b;
            session = b + session + b;
            String sql = "UPDATE sessionid SET username = " + username + " WHERE session = " + session;
            PreparedStatement query = con1.prepareStatement(sql);
            query.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    public String UsernameBySession(String session2) {

        try {
            //PreparedStatement query = con1.prepareStatement("SELECT username FROM sessionid WHERE session = ?");
            //query.setString(1,session);

            //rs2 = query.executeQuery(q);
            //String username = rs2.getString("username");
            //return username;
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
            Statement stmt = con2.createStatement();
            String b = "\'";
            session2 = b + session2 + b;
            String sql = "SELECT username FROM sessionid WHERE session = " + session2;
            ResultSet rs2 = stmt.executeQuery(sql);
            String username = "";
            while (rs2.next()) {
                username = rs2.getString("username");
                System.out.println(username);
            }
            return username;
        } catch (SQLException e) {
            e.printStackTrace();
            return "false";
        }
    }
    public String[] LoadUser (String username){

        try {
            con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sudokudb", "root", "");
            Statement stmt = con3.createStatement();
            String b = "\'";
            username = b + username + b;
            String sql = "SELECT * FROM users WHERE username = " + username;
            ResultSet rs3 = stmt.executeQuery(sql);
            String firstname = "";
            String lastname = "";
            String password = "";
            String email = "";
            String country = "";
            while (rs3.next()) {
                firstname = rs3.getString("firstname");
                lastname = rs3.getString("lastname");
                password = rs3.getString("password");
                email = rs3.getString("email");
                country = rs3.getString("country");
            }
            String[] returnedarr = new String[5];
            returnedarr[0] = firstname;
            returnedarr[1] = lastname;
            returnedarr[2] = password;
            returnedarr[3] = email;
            returnedarr[4] = country;
            return returnedarr;
        } catch (SQLException e) {
            e.printStackTrace();
            String[] faslearray = new String[1];
            faslearray[0] = "false";
            return faslearray;
        }
    }
}


*/