package MaktabHw4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ali yegane fard test
//        change
        int brch = 0;
        brch++;
        Class.forName("org.postgresql.Driver");
        Methods methods = new Methods();
        Connection connection =
                DriverManager.getConnection("jdbc:postgresql://localhost:5435/postgres", "postgres", "postgres");
        Scanner scanner = new Scanner(System.in);
        Boolean loginStatus = true;
        Integer loginOrSignUp;
        methods.createCustomerTable(connection);
        methods.createCinemaTable(connection);
        methods.createMainAdminTable(connection);
        //todo text write by mahdi
        String fName;
        String lName;
        String uName;
        String showTime;
        String movieName;
        String password;
        String cinemaName;
        Boolean adminLogin = false;
        Boolean customerLogin = false;
        Boolean cinemaLogin = false;
        Boolean situation = false;
        Integer order;


        while (loginStatus) {
            System.out.println("1.Login \n2.SignUp ");
            loginOrSignUp = scanner.nextInt();

            if (loginOrSignUp == 1) {
                System.out.println("What are you ? \n1.Customer \n2.Cinema \n3.Admin ");
                int loginCustomerOrCinemaOrAdmin = scanner.nextInt();
                if (loginCustomerOrCinemaOrAdmin == 1) {
                    System.out.println("Please enter your username: ");
                    uName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    situation = methods.customerLogin(uName, password);
                    if (situation) {
                        customerLogin = true;
                        situation = false;
                    } else System.out.println("wrong username or password! ");

                } else if (loginCustomerOrCinemaOrAdmin == 2) {
                    System.out.println("Please enter your username: ");
                    uName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    situation = methods.adminLogin(uName, password);
                    if (situation) {
                        adminLogin = true;
                        situation = false;
                    } else System.out.println("wrong username or password! ");

                } else if (loginCustomerOrCinemaOrAdmin == 3) {
                    System.out.println("Please enter your username: ");
                    uName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    situation = methods.cinemaLogin(uName, password);
                    if (situation) {
                        cinemaLogin = true;
                        situation = false;
                    } else System.out.println("wrong username or password! ");

                } else System.out.println("wrong number! ");
            } else if (loginOrSignUp == 2) {
                System.out.println("what are you? \n1.Customer \n2.Cinema");
                int whatAreYou = scanner.nextInt();
                if (whatAreYou == 1) {
                    System.out.println("Please enter your first name: ");
                    fName = scanner.nextLine();
                    System.out.println("Please enter your last name:  ");
                    lName = scanner.nextLine();
                    System.out.println("Please enter your username: ");
                    uName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    methods.customerRegister(fName, lName, uName, password);
                    System.out.println("Done! ");

                } else if (whatAreYou == 2) {
                    System.out.println("Please enter Cinema name:  ");
                    cinemaName = scanner.nextLine();
                    System.out.println("Please enter your username: ");
                    uName = scanner.nextLine();
                    System.out.println("Please enter your password: ");
                    password = scanner.nextLine();
                    methods.cinemaRegister(cinemaName, uName, password);
                    System.out.println("Done! ");

                } else System.out.println("wrong number! ");
            } else System.out.println("wrong number! ");

        }
        while (adminLogin) {
            System.out.println("1.change cinema's status \n2.Back");
            order = scanner.nextInt();
            if (order == 1) {
                System.out.println("please enter cinema name: ");
                cinemaName = scanner.nextLine();
                System.out.println("please enter cinema username:");
                uName = scanner.nextLine();
                System.out.println("please enter cinema password:");
                password = scanner.nextLine();
                System.out.println("please enter cinema's status (Allow) or (Deni)");
                String situationInMain = scanner.nextLine();
                methods.updateCinema(cinemaName, uName, password, situationInMain);
            } else System.out.println("wrong number! ");
        }
        while (customerLogin) {
            System.out.println("1.View tickets \n2.Reserve ticket \n3.Search for movie by name and date \n");
            order = scanner.nextInt();
            if (order == 1)
                methods.viewTickets();
            else if (order == 2) {
                System.out.println("enter number of ticket you want buy: ");
                int ticketNumber = scanner.nextInt();
                System.out.println("Enter ticket id:");
                int ticketId = scanner.nextInt();
                System.out.println("Enter your username: ");
                uName = scanner.nextLine();
                methods.ticketReserving(ticketNumber, ticketId, uName);
                System.out.println("Done! ");
            } else if (order == 3) {
                System.out.println("What is the movie name: ");
                movieName = scanner.nextLine();
                System.out.println("Enter the date you want: (yyyy/mm/dd) ");
                String movieDate = scanner.nextLine();
                methods.movieSearchByNameAndDate(movieName, movieDate);
                //String movieName, Date movieDate
            } else System.out.println("wrong number! ");
        }
        while (cinemaLogin) {
            System.out.println("1.Add ticket \n2.Delete ticket ");
            order = scanner.nextInt();
            if (order == 1) {
                System.out.println("enter ticket id: ");
                int id = scanner.nextInt();
                System.out.println("enter movie name: ");
                movieName = scanner.nextLine();
                System.out.println("enter cinema name: ");
                cinemaName = scanner.nextLine();
                System.out.println("enter movie show time: ");
                showTime = scanner.nextLine();
                methods.addTicket(id, movieName, cinemaName, showTime);
            }
            else if (order == 2){}
            System.out.println("please enter movie Id: ");
            int id=scanner.nextInt();
            methods.deleteTicket(id);
        }

        connection.close();
    }
}
