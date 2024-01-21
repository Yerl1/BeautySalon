package Controllers;
import Entities.*;
import Repositories.BookingRepository;
import java.util.Scanner;
public class BookingController {
    private BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository){this.bookingRepository = bookingRepository;}

    public void addBooking(Scanner scanner){
        System.out.println("Enter username:");
        String username = scanner.next();


        System.out.println("Enter the name of procedure:");
        String procedure_name = scanner.next();

        System.out.println("Enter the date:");
        String date = scanner.next();

        System.out.println("Enter the time");
        String time = scanner.next();

        boolean chacker = bookingRepository.timeCheker(date, time);
        if(!chacker){
            System.out.println("Sorry, this time is already booked ;(");
            return;
        }

        Booking newbooking = new Booking(procedure_name, date, time);
        bookingRepository.addBooking(newbooking, username);
    }

    public void deleteBooking(Scanner scanner){
        System.out.println("Enter username:");
        String username = scanner.next();

        System.out.println("Enter the date of the booking:");
        String date = scanner.next();

        System.out.println("Enter the time of the booking:");
        String time = scanner.next();

        bookingRepository.deleteBooking(username, time, date);
    }

    public void getAllBookings(){
        bookingRepository.getAllProcedures();
    }
}
