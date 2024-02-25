import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    int choice;

    void menu() throws SQLException {
        do {
            System.out.println("******************************************");
            System.out.println("********* WELCOME TO WEATHER APP *********");
            System.out.println("**                                      **");
            System.out.println("**            -1- Manager.              **");
            System.out.println("**            -2- User.                 **");
            System.out.println("******************************************");
            System.out.println("******************************************");
            choice = new Scanner(System.in).nextInt();
            if (choice < 1 && choice > 2) {
                System.out.println("Error!");
                System.out.println("choose 1 for Manager and 2 for User");
                choice = new Scanner(System.in).nextInt();
            }
        } while (choice < 1 && choice > 2);
        switch (choice) {
            case 1:
                Manager();
                break;
            case 2:
                User();
                break;
        }
        menu();
    }


    public void cityAdd() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter City Id : ");
        int cityId = scanner.nextInt();
        System.out.println("Enter City Name");
        String cityName = scanner.next();
        System.out.println("Enter city Temperature");
        int currentTemperature = scanner.nextInt();
        System.out.println("Enter city Humidity");
        int currentHumidity = scanner.nextInt();
        System.out.println("Enter city Wend Speed");
        int currentWindSpeed = scanner.nextInt();
        connect_Db.addCity(new City(cityId, cityName, currentTemperature, currentHumidity, currentWindSpeed));
    }

    public void  displayAllCities() throws SQLException {
        List<City> cities = connect_Db.getAllCities();

        System.out.println("*******************************************");
        System.out.println("********* LIST OF ALL ADDED CITIES ********");

        for (City city : cities) {
            System.out.println("City ID: " + city.cityId);
            System.out.println("City Name: " + city.cityName);
            System.out.println("Temperature: " + city.currentTemperature);
            System.out.println("Humidity: " + city.currentHumidity);
            System.out.println("Wind Speed: " + city.currentWindSpeed);
            System.out.println("-------------------------------------------");
        }
        System.out.println("*******************************************");
    }


    public void updateCityInfo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the City ID to update: ");
        int cityIdToUpdate = scanner.nextInt();
        if (connect_Db.cityExists(cityIdToUpdate)) {
            System.out.println("Enter new Temperature: ");
            int newTemperature = scanner.nextInt();
            System.out.println("Enter new Humidity: ");
            int newHumidity = scanner.nextInt();
            System.out.println("Enter new Wind Speed: ");
            int newWindSpeed = scanner.nextInt();

            connect_Db.updateCity(cityIdToUpdate, newTemperature, newHumidity, newWindSpeed);

        } else {
            System.out.println("City with ID " + cityIdToUpdate + " does not exist.");
        }
    }
    void deleteCity() throws SQLException {
        System.out.print("Enter ID of the city to delete: ");
        int cityId = new Scanner(System.in).nextInt();
        connect_Db.deleteCity(cityId);

    }


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void cityAddHistory() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter City History Id : ");
        int historicalDataId = scanner.nextInt();
        System.out.println("Enter City Id");
        int cityId = scanner.nextInt();
        System.out.println("Enter city Event Data");
        String eventDate = scanner.next();
        System.out.println("Enter city Temperature");
        int temperature = scanner.nextInt();
        connect_Db.addCityHistory(new CityHistory(historicalDataId, cityId, eventDate, temperature));
    }


    public void  displayAllCitiesHistory() throws SQLException {
        List<CityHistory> citiesHistory = connect_Db.getAllCitiesHistory();

        System.out.println("*******************************************");
        System.out.println("********* LIST OF ALL ADDED CITIES ********");
        for (CityHistory cityHistory : citiesHistory) {
            
            System.out.println("Data ID" + cityHistory.historicalDataId);
            System.out.println("City ID: " + cityHistory.cityId);
            System.out.println("Event Data" + cityHistory.eventDate);
            System.out.println("Temperature: " + cityHistory.temperature);
            System.out.println("-------------------------------------------");
        }
        System.out.println("*******************************************");
    }

    void deleteCityHistory() throws SQLException {
        System.out.print("Enter ID of the city to delete: ");
        int historyId = new Scanner(System.in).nextInt();
        connect_Db.deleteCityHistory(historyId);

    }

//***********************************************************************************************************************
//MANAGER USER DISPLAY**********************************************************************************************
    void Manager() throws SQLException {
        int mChoice;
        System.out.println("*******************************************");
        System.out.println("********* WELCOME TO MANAGER MENU *********");
        System.out.println("**          -1- ADD CITY                 **");
        System.out.println("**          -2- DISPLAY CITIES           **");
        System.out.println("**          -3- UPDATE CITY INFO         **");
        System.out.println("**          -3- DELETE CITY              **");
        System.out.println("**          -4- MANAGE CITIES HISTORY    **");
        System.out.println("**          -5- BACK TO GENERAL MENU     **");
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        mChoice = new Scanner(System.in).nextInt();
        switch (mChoice) {
            case 1:
                cityAdd();
                break;
            case 2:
                displayAllCities();
                break;
            case 3:
                deleteCity();
                break;
            case 4:
                updateCityInfo();
                break;
            case 5:
                System.out.println("*******************************************");
                System.out.println("********* WELCOME TO MANAGER MENU *********");
                System.out.println("**          -1- ADD CITY HISTORY         **");
                System.out.println("**          -2- DISPLAY CITIES HISTORIES **");
                System.out.println("**          -3- UPDATE CITY HISTORY      **");
                System.out.println("**          -4- DELETE CITY HISTORY      **");
                System.out.println("*******************************************");
                System.out.println("*******************************************");
                int choice = new Scanner(System.in).nextInt();
                switch (choice){
                    case 1:
                        cityAddHistory();
                        break;
                    case 2:
                        displayAllCitiesHistory();
                        break;
                    case 4:
                        deleteCityHistory();
                        break;
                }
                break;
            case 6:
                menu();
                break;

        }
        Manager();
    }
    //USER MENU DISPLAY ******************************************************************************
    void User() throws SQLException {
        System.out.println("*******************************************");
        System.out.println("********* WELCOME TO USER MENU ************");
        System.out.println("**          -1- DISPLAY CITIES           **");
        System.out.println("*******************************************");
        System.out.println("*******************************************");
        System.out.println("Enter Your Choice");
        int userChoice = new Scanner(System.in).nextInt();
        switch (userChoice){
            case 1:
                displayAllCities();
                break;
            case 2:
                menu();
                break;
        }
        User();
    }
}




//    void Manager() throws SQLException {
//        int mChoice;
//        System.out.println("*******************************************");
//        System.out.println("********* WELCOME TO MANAGER MENU *********");
//        System.out.println("**          -1- ADD CITY                 **");
//        System.out.println("**          -2- UPDATE CITY INFO         **");
//        System.out.println("**          -3- DELETE CITY              **");
//        System.out.println("*******************************************");
//        System.out.println("*******************************************");
//        mChoice = new Scanner(System.in).nextInt();
//        switch (mChoice){
//            case 1:
//                City();
//                break;
//        }


//    }



