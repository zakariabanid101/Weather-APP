import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connect_Db {


    public static Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/weather?serverTimezone=UTC",
                "zakaria",
                "zakaria.0123"

        );
        System.out.println("done!");
        Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
////
        return c;
    }

    public static void addCity(City city) throws SQLException {
        String sql = "INSERT INTO city(cityId, cityName, cityTemperature, cityHumidity, cityWindSpeed) VALUES(?,?,?,?,?)";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, city.cityId);
        statement.setString(2, city.cityName);
        statement.setInt(3, city.currentTemperature);
        statement.setInt(4, city.currentHumidity);
        statement.setInt(5, city.currentWindSpeed);
        int rows = statement.executeUpdate();
        if (rows > 0) {
            System.out.println("City added successfully!");
        } else
            System.out.println("Error!");
        statement.close();
        connection.close();
    }

    public static List<City> getAllCities() throws SQLException {

        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM city";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int cityId = resultSet.getInt("cityId");
            String cityName = resultSet.getString("cityName");
            int currentHumidity = resultSet.getInt("cityHumidity");
            int currentTemperature = resultSet.getInt("cityTemperature");
            int currentWindSpeed = resultSet.getInt("cityWindSpeed");

            cities.add(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return cities;
    }

    public static boolean cityExists(int cityIdToUpdate) {
        String sql = "SELECT cityId FROM city WHERE cityId=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cityIdToUpdate);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateCity(int cityIdToUpdate, int newTemperature, int newHumidity, int newWindSpeed) {
        String sql = "UPDATE city SET cityTemperature=?, cityHumidity=?, cityWindSpeed=? WHERE cityId=?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, newTemperature);
            statement.setInt(2, newHumidity);
            statement.setInt(3, newWindSpeed);
            statement.setInt(4, cityIdToUpdate);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("City information updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCity(int cityId) throws SQLException {
        String sql = "DELETE FROM City WHERE cityId = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cityId);
        statement.executeUpdate();
        System.out.println("City deleted successfully!");
        connection.close();
        statement.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void addCityHistory(CityHistory history) throws SQLException {
        String sql = "INSERT INTO cityHistory(`historicalDataId`, `cityId`, `eventDate`, `temperature`) VALUES(?,?,?,?)";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, history.historicalDataId);
        statement.setInt(2, history.cityId);
        statement.setString(3, history.eventDate);
        statement.setInt(4, history.temperature);
        int rows = statement.executeUpdate();
        if (rows > 0) {
            System.out.println("City added successfully!");
        } else
            System.out.println("Error!");
        statement.close();
        connection.close();
    }

    public static List<CityHistory> getAllCitiesHistory() throws SQLException {
        List<CityHistory> citiesHistory = new ArrayList<>();
        String sql = "SELECT * FROM cityhistory";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int historicalDataId = resultSet.getInt("historicalDataId");
            int cityId = resultSet.getInt("cityId");
            String eventDate = resultSet.getString("eventDate");
            int temperature = resultSet.getInt("temperature");
            citiesHistory.add(new CityHistory(historicalDataId, cityId, eventDate, temperature));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return citiesHistory;
    }

    public static void deleteCityHistory(int historyId) throws SQLException {
        String sql = "DELETE FROM cityhistory WHERE historicalDataId = ?";
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, historyId);
        statement.executeUpdate();
        System.out.println("City deleted successfully!");
        connection.close();
        statement.close();
    }
}

