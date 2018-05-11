package database;

import java.sql.*;

public class DB {
    private static Connection connection;

    public static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DB.connection = DriverManager.getConnection("jdbc:mysql://localhost/twogenerals?" + "user=root&password=");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        connection = null;
    }

    public static void insertUser(String login, String password, Boolean hasAdmin){
        try {
            String sqlInsert = "INSERT INTO `tuser`(`login`, `password`, `hasadmin`) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = DB.connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, login);
            // <------------------------------------------------------------------------>
            //password is visible, should be immediately transfered into hash after init
            // <------------------------------------------------------------------------>
            preparedStatement.setString(2, org.apache.commons.codec.digest.DigestUtils.sha256Hex(password));
            preparedStatement.setBoolean(3, hasAdmin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkPassword (String login, String password){
        try {
            String sqlSelect = "SELECT `password` FROM `tuser` WHERE `login` = '" + login +"';";
            Statement statement = DB.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            while (resultSet.next()) {
                if (org.apache.commons.codec.digest.DigestUtils.sha256Hex(password).equals(resultSet.getString("password"))){
                    return true;
                } else {
                    return false;
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
}
