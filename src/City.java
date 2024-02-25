import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class City {
    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public int getCurrentHumidity() {
        return currentHumidity;
    }

    public int getCurrentWindSpeed() {
        return currentWindSpeed;
    }

    int cityId; //Identifiant unique pour la ville.
    String cityName; //Nom de la ville.
    int currentTemperature; //Température actuelle.
    int currentHumidity; //Taux d'humidité actuelle.
    int currentWindSpeed; //Vitesse du vent actuelle.

    public City(int cityId, String cityName, int currentTemperature, int currentHumidity, int currentWindSpeed) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentTemperature = currentTemperature;
        this.currentHumidity = currentHumidity;
        this.currentWindSpeed = currentWindSpeed;
    }
}
