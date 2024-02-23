import java.sql.*;

public class connect_Db {
    
        
    

    public static Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/weather?serverTimezone=UTC",
                "zakaria",
                "zakaria123"

        );
        System.out.println("done!");
          Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////
        return c;
    }
     public static void addCity(City city)throws SQLException{
         System.out.println(city.cityName);
        String sql="INSERT INTO city(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed) VALUES(?,?,?,?,?)";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,city.cityId);
        statement.setString(2,city.cityName);
        statement.setInt(3,city.currentTemperature);
        statement.setInt(4,city.currentHumidity);
        statement.setInt(5, city.currentWindSpeed);
        int rows = statement.executeUpdate();
        if (rows > 0){
            System.out.println("City added successfully!");
        }else
            System.out.println("Error!");
        statement.close();
        connection.close();
    }

}
