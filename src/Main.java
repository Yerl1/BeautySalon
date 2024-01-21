import java.net.IDN;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        BeautySalon beautySalon = new BeautySalon();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        int action;
        for(;;){
            System.out.println("Choose the action:");
            System.out.println("1) To show beauty procedures list;");
            System.out.println("2) To add a beauty procedure;");
            System.out.println("3) To add a new user;");
            System.out.println("4) To book for a beauty procedure;");
            System.out.println("5) To cancel booking for a beauty procedure;");
            System.out.println("6) To show booking history;");
            System.out.println("7) To get user's information");
            System.out.println("0) Exit.");

            System.out.println("Choose the option:");
            action = Integer.parseInt(scanner.next());

            switch (action) {
                case 1:
                    beautySalon.getProcedures();
                    break;
                case 2:
                    System.out.println("Print the name of the procedure:");
                    String name_of_procedure = scanner.next();

                    System.out.println("Print the price of the procedure:");
                    int procedure_price = Integer.parseInt(scanner.next());

                    System.out.println("Print the description of the procedure:");
                    String procedure_description = scanner.next().toString();

                    BeautyProcedure adding = new BeautyProcedure(name_of_procedure, procedure_price, procedure_description);
                    beautySalon.setProcedure(adding);
                    System.out.println("The procedure has been added successfully!");
                    break;
                case 3:
                    System.out.println("Print a new user's name:");
                    String user_name = scanner.next();

                    System.out.println("Print a new user's balance:");
                    int user_balance = Integer.parseInt(scanner.next());

                    System.out.println("New user has been added successfully!" + '\n');
                    System.out.println("-------------------------------------------------------------------------------");
                    System.out.println("|   We have a great suggestion for you, buy a subscription \"VIP KAZASHKA\"   |");
                    System.out.println("|                   and get any procedure for free!!!                         |");
                    System.out.println("|                                                                             |");
                    System.out.println("|              Do you want to get it for 2000$ ?   1)Yes 2)No                 |");
                    System.out.println("-------------------------------------------------------------------------------");
                    int choice  =  Integer.parseInt(scanner.next());
                    if(choice == 1 && user_balance >= 2000){
                        VIP_kaz new_user  = new VIP_kaz(user_name, user_balance);
                        System.out.println("Done! Thank you!");
                        new_user.setBalance(new_user.getBalance()-2000);
                        beautySalon.setUser(new_user);
                    }else if(choice == 1 && user_balance < 2000){
                        System.out.println("Sorry, but you don't have enough money...");
                    }else if(choice == 0){
                        System.out.println("Thank you for your feedback!");
                        Ordinary new_user = new Ordinary(user_name, user_balance);
                        beautySalon.setUser(new_user);
                    }
                    break;
                case 4:
                    System.out.println("Enter the name of the user you are going to book for");
                    String name = scanner.next();
                    int k = 0;
                    for(User user : beautySalon.getUser()){
                        if(name.equals(user.getUsername())){
                            k=1;
                            int n = 0;
                            System.out.println("The user has been found\n" + "Enter the name of procedure:");
                            String procedure_name = scanner.next();
                            for(BeautyProcedure procedure: beautySalon.getProcedures_f()){
                                if(procedure_name.equals(procedure.getProcedureName())){
                                    n=1;
                                    System.out.println("Write the date of booking:");
                                    String booking_date = scanner.next();
                                    System.out.println("Write the time of booking:");
                                    String booking_time = null;
                                    booking_time = scanner.next();
                                    beautySalon.Booking_procedure(user, procedure, booking_date, booking_time);
                                    break;
                                }
                            }
                            if(n==0) {
                                System.out.println("Procedure not found(");
                            }
                        }
                        System.out.println(user.getBalance());

                    }
                    if(k == 0){
                        System.out.println("User not found(");
                    }
                    break;
                case 5:
                    System.out.println("Enter the name of the user:");
                    String Uname = scanner.next();
                    int k2 = 0;
                    for(User user : beautySalon.getUser()){
                        if(Uname.equals(user.getUsername())){
                            k=1;
                            System.out.println("Bookings of the user:");
                            System.out.println("ID:\t" + "Procedure name:\t" + "Date:\t" + "Time:\t");
                            for(Booking booking : user.getTaking_procedures()){
                                System.out.println(booking.getID() +"\t" + booking.getName() + "\t" + booking.getDate() + "\t" + booking.getTime());
                            }
                            System.out.println("Choose the ID of canceling booking");
                            int Id = scanner.nextInt();
                            user.getTaking_procedures().get(Id-1).setStatus(false);
                            user.Delete_booking(Id-1);

                        }
                    }
                    if(k2 == 0){
                        System.out.println("User not found(");
                    }
                    break;
                case 6:
                    beautySalon.getBookingHistory();
                    break;
                case 7:
                    System.out.println("Print a new user's name:");
                    String user = scanner.next();
                    for(User u : beautySalon.getUser()){
                        if(user.equals(u.getUsername())){
                            System.out.println("Name: " + u.getUsername() + '\n' + "Balance: " + u.getBalance() + '\n');
                            if(u.getStatus() == 777) {
                                System.out.println("Status: VIP KAZASHKA");
                            }else{
                                System.out.println("Status: Prostoy smertniy");
                            }
                        }
                    }
                default:
                    System.out.println("Error!Choose the option again:");
            }
        }
    }
}
