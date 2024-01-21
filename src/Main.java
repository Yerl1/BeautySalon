import Controllers.BeautyController;
import Controllers.BookingController;
import Controllers.UserConroller;
import Repositories.BeautyRepository;
import Repositories.BookingRepository;
import Repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main{
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "16022006";
    public static void main(String[] args) {
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            createBookingTable(connection);
            createProcedureTable(connection);
            createUserTable(connection);

            UserRepository userRepository = new UserRepository(connection);
            UserConroller userConroller = new UserConroller(userRepository);

            BookingRepository bookingRepository = new BookingRepository(connection);
            BookingController bookingController = new BookingController(bookingRepository);

            BeautyRepository beautyRepository = new BeautyRepository(connection);
            BeautyController beautyController = new BeautyController(beautyRepository);

            runBeautySalonApp(userConroller,beautyController,bookingController);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createUserTable(Connection connection) throws SQLException{
        try(Statement statement = connection.createStatement()){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name_ VARCHAR(50) NOT NULL,"
                    + "status INT NOT NULL,"
                    + "balance DOUBLE PRECISION NOT NULL)";
            statement.executeUpdate(createTableQuery);
        }
    }

    public static void createBookingTable(Connection connection) throws SQLException{
        try(Statement statement = connection.createStatement()){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS bookings("
                    + "id SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) NOT NULL,"
                    + "name_of_procedure VARCHAR(150) NOT NULL,"
                    + "date_ VARCHAR(12) NOT NULL,"
                    + "time_ VARCHAR(6) NOT NULL)";
            statement.executeUpdate(createTableQuery);
        }
    }

    public static void createProcedureTable(Connection connection) throws SQLException{
        try(Statement statement = connection.createStatement()){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS procedures("
                    + "id SERIAL PRIMARY KEY,"
                    + "title VARCHAR(50) NOT NULL,"
                    + "price INT NOT NULL,"
                    + "description VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createTableQuery);
        }
    }
    private static void runBeautySalonApp(UserConroller userConroller, BeautyController beautyController, BookingController bookingController){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1) Add new user");
            System.out.println("2) Delete user");
            System.out.println("3) Get all users");
            System.out.println("4) Add new beauty procedure");
            System.out.println("5) Delete procedure");
            System.out.println("6) Get all procedures");
            System.out.println("7) Book for a procedure");
            System.out.println("8) Cancel booking");
            System.out.println("9) Get all bookings");
            System.out.println("10) Quit");

            System.out.println("Make your choice: ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    userConroller.addUser(scanner);
                    break;
                case 2:
                    userConroller.deleteUser(scanner);
                    break;
                case 3:
                    userConroller.getALLUsers();
                    break;
                case 4:
                    beautyController.addProcedure(scanner);
                    break;
                case 5:
                    beautyController.deleteProcedure(scanner);
                    break;
                case 6:
                    beautyController.getProcedures();
                    break;
                case 7:
                    bookingController.addBooking(scanner);
                    break;
                case 8:
                    bookingController.deleteBooking(scanner);
                    break;
                case 9:
                    bookingController.getAllBookings();
                    break;
                case 10:
                    System.out.println("Thank you for visiting us! Goodbye!");
                    return;

            }
        }

    }
}