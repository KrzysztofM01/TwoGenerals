import java.sql.*;

public class SqlManager {

    public static void insert(int id, String name, Double price, int amount) throws java.sql.SQLException{
        //Declaration of variables that will be later used to load files from SQL database
        Connection connect = SqlManager.sqlConnect();
        PreparedStatement preparedStatement = null;
        String sqlInsert = "INSERT INTO `tproduct`(`id`, `name`, `price`, `amount`) VALUES (?, ?, ?, ?);";
        preparedStatement = connect.prepareStatement(sqlInsert);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setDouble(3, price);
        preparedStatement.setInt(4, amount);
        preparedStatement.executeUpdate();
    }

    public static void update(int id, String name, Double price, int amount, String tablename, String condition) throws java.sql.SQLException{
        Connection connect = SqlManager.sqlConnect();
        PreparedStatement preparedStatement = null;
        String sqlUpdate = "UPDATE `tproduct` SET `id`=" + id + ",`name`='" + name +"',`price`="+ price + ",`amount`="+ amount +" WHERE " + tablename + "=" + condition + ";";
        preparedStatement = connect.prepareStatement(sqlUpdate);
        preparedStatement.executeUpdate();
    }

    public static void delete(String tableName, String condition) throws java.sql.SQLException{
        Connection connect = SqlManager.sqlConnect();
        PreparedStatement preparedStatement = null;
        String sqlDelete = "DELETE FROM `tproduct` WHERE " + tableName + "=" + condition + ";";
        preparedStatement = connect.prepareStatement(sqlDelete);
        preparedStatement.executeUpdate();
    }

    public static void select(String name) throws java.sql.SQLException{
        Connection connect = SqlManager.sqlConnect();
        Statement statement = null;
        ResultSet resultSet = null;
        String sqlSelect = "SELECT * FROM TPRODUCT WHERE name='" + name + "';";
        statement = connect.createStatement();
        resultSet = statement.executeQuery(sqlSelect);
        /*
        while (resultSet.next()) {
            Product p = new Product();
            p.id = resultSet.getInt("id");
            p.name = resultSet.getString("name");
            p.price = resultSet.getDouble("price");
            p.amount = resultSet.getInt("amount");
            return p;
        }
        */
    }

    private static Connection sqlConnect(){
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/sklep?" + "user=root&password=");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}