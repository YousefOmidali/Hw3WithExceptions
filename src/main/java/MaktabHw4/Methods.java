package MaktabHw4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Methods {

    private Connection connection;

    public void createCustomerTable(Connection connection) throws SQLException {
        this.connection = connection;
        String customersTable = "CREAT TABLE IF NOT EXISTS CUSTOMERS(" +
                "    firstName varchar(200)," +
                "    lastName varchar(200)," +
                "    username varchar(200) unique ," +
                "    password varchar(200)," +
                ");";
        connection.prepareStatement(customersTable).execute();
    }

    public void createCinemaTable(Connection connection) throws SQLException {
        this.connection = connection;
        String cinemaTable = "CREAT TABLE IF NOT EXISTS CINEMAS(" +
                "    cinemaName varchar(200)," +
                "    username varchar(200) unique ," +
                "    password varchar(200)," +
                "    status varchar (200)" +
                ");";
        connection.prepareStatement(cinemaTable).execute();
    }

    public void createMainAdminTable(Connection connection) throws SQLException {
        this.connection = connection;
        String customersTable = "CREAT TABLE IF NOT EXISTS ADMIN(" +
                "    firstName varchar(200)," +
                "    lastName varchar(200)," +
                "    username varchar(200) unique ," +
                "    password varchar(200)," +
                ");";
        connection.prepareStatement(customersTable).execute();
    }

    public void creatTicketTable(Connection connection) throws SQLException {
        this.connection = connection;
        String ticketTable = "CREAT TABLE IF NOT EXISTS TICKETS(" +
                "    ticketId INTEGER "+
                "    movieName varchar(200)," +
                "    cinemaName varchar(200)," +
                "    showTime varchar (200)" +
                "    reservedBy varchar (200)" +
                ");";
        connection.prepareStatement(ticketTable).execute();
    }

    // sabt naam customer
    public void customerRegister(String firstName, String lastName, String username, String password) throws SQLException {
        String saveCustomerTable = "INSERT INTO TABLE CUSTOMERS VALUE(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveCustomerTable);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    // sabt naam cinema
    public void cinemaRegister(String cinemaName, String username, String password) throws SQLException {
        String saveCinemaTable = "INSERT INTO TABLE CINEMAS VALUE(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(saveCinemaTable);
        preparedStatement.setString(1, cinemaName);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public Boolean adminLogin(String username, String password) throws SQLException {
        String adminLogin = "select * from ADMIN where username = ? And password = ?";
        boolean situation = login(username, password, adminLogin);
        return situation;
    }

    public boolean customerLogin(String username, String password) throws SQLException {
        String customerLogin = "select * from customers where username = ? And password = ?";
        boolean situation = login(username, password, customerLogin);
        return situation;

    }

    public boolean cinemaLogin(String username, String password) throws SQLException {
        String customerLogin = "select * from cinemas where username = ? And password = ?";
        boolean situation = login(username, password, customerLogin);
        return situation;
    }

    // baraye taghir status ya moshakhasat cinema
    public void updateCinema(String cinemaName, String username, String password, String cinemaStatus) throws SQLException {
        String changeCinemaStatus = "update cinemas " +
                "SET username = ?," +
                " password = ? " +
                "status = ? " +
                "where cinemaName = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(changeCinemaStatus);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, cinemaStatus);
        preparedStatement.setString(4, cinemaName);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void addTicket(Integer id,String movieName, String cinemaName,  String showTime) throws SQLException {
        String saveTicket = "INSERT INTO TABLE TICKETS VALUE(?,?,?,?,Null )";
        PreparedStatement preparedStatement = connection.prepareStatement(saveTicket);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, movieName);
        preparedStatement.setString(3, cinemaName);
        preparedStatement.setString(4, showTime);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public void viewTickets() throws SQLException {
        String viewTicket = "select * from TICKETS WHERE reservedBy = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(viewTicket);
        preparedStatement.setString(1, null);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Ticket ticket = new Ticket(
                    result.getString("movieName"),
                    result.getString("cinemaName"),
                    result.getInt("ticketId"),
                    result.getDate("showTime"),
                    result.getString("reservedBy")
            );
            System.out.println(ticket);
        }
    }

    public void ticketReserving(Integer numberOfTicketsYouWantOrder, Integer ticketId, String username) throws SQLException {
        viewTickets();
        while (numberOfTicketsYouWantOrder > 0) {
            String changeCinemaStatus = "update cinemas " +
                    "SET reservedBy = ?," +
                    "where ticketId= ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(changeCinemaStatus);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, ticketId);
            numberOfTicketsYouWantOrder--;
        }


    }
    public void deleteTicket(Integer ticketId) throws SQLException {

        String deleteTicketById = "delete from table tickets where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteTicketById);
        preparedStatement.setInt(1,ticketId);
        preparedStatement.executeUpdate(deleteTicketById);
        System.out.println("Done! ");
        preparedStatement.close();
    }

    public void movieSearchByNameAndDate(String movieName, String movieDate) throws SQLException {
        viewTickets();
        String movieSearchByNameAndDateString = "select * from TICKETS where movieName = ? AND showTime=?";
        PreparedStatement preparedStatement = connection.prepareStatement(movieSearchByNameAndDateString);
        preparedStatement.setString(1, movieName);
        preparedStatement.setString(2, movieDate);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            Ticket ticket = new Ticket(
                    result.getString("movieName"),
                    result.getString("cinemaName"),
                    result.getInt("ticketId"),
                    result.getDate("showTime"),
                    result.getString("reservedBy")
            );
            System.out.println(ticket);
        }
    }

    public boolean login(String username, String password, String query) throws SQLException {
        boolean situation = false;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet result = preparedStatement.executeQuery();
        preparedStatement.close();
        if (result.next())
            situation = true;
        return situation;
    }

}
