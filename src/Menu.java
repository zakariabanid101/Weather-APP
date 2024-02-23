import java.sql.Connection;
import java.sql.SQLException;
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
                int mChoice;
                System.out.println("*******************************************");
                System.out.println("********* WELCOME TO MANAGER MENU *********");
                System.out.println("**          -1- ADD CITY                 **");
                System.out.println("**          -2- UPDATE CITY INFO         **");
                System.out.println("**          -3- DELETE CITY              **");
                System.out.println("*******************************************");
                System.out.println("*******************************************");
                mChoice = new Scanner(System.in).nextInt();
                switch (mChoice) {
                    case 1:
                        try {
                            cityAdd();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:

                }


        }




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



