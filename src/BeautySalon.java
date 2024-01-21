import java.util.ArrayList;

public class BeautySalon {
    static ArrayList<User> users;
    static ArrayList<BeautyProcedure> all_procedures;
    static ArrayList<Booking> booking_history;

    public BeautySalon() {
        this.users = new ArrayList<>();
        this.all_procedures = new ArrayList<>();
        this.booking_history = new ArrayList<>();
    }

    public void setUser(User user) {
        users.add(user);
    }

    public void setProcedure(BeautyProcedure procedure) {
        all_procedures.add(procedure);
    }
    int intTime(String s){
        int time = 0;
        time += ((s.charAt(0)- '0')*10 + s.charAt(1)-'0') * 3600;
        time += ((s.charAt(3)- '0')*10 + s.charAt(4)-'0') * 60;
        return time;
    }
    private boolean isTimeAvailable(String date, String time) {
        for (Booking booking : booking_history) {
            if (booking.getDate().equals(date) && (intTime(booking.getTime()) <= intTime(time)) && (intTime(booking.getTime())+3600 > intTime(time))) {
                return false;
            }
        }
        return true;
    }
    public void Booking_procedure(User user, BeautyProcedure beautyProcedure, String date, String time) {
        if (isTimeAvailable(date, time) && ((user.getBalance()>= beautyProcedure.getPrice() && user.getStatus() == 2) || user.getStatus() == 777)) {
            Booking booking = new Booking(beautyProcedure.getProcedureName(), date, time);
            booking_history.add(booking);
            user.setTaken_beauty_procedure(booking);
            if(user.getStatus() != 777){
                user.setBalance(user.getBalance() - beautyProcedure.getPrice());
            }
            System.out.println("Success");
        } else {
            System.out.println("Sorry, the time slot is already booked or you don't have the necessary balance");
        }
    }

    public void getProcedures(){
        System.out.println("Procedures that available now:");
        System.out.println("Name: \t" + "Price:\t" + "Description:");
        for(BeautyProcedure procedure: all_procedures){
            System.out.println(procedure.getProcedureName() + "\t $" + procedure.getPrice() + "\t" + procedure.getDescription());
        }
        System.out.println("\n");
    }
    public ArrayList<BeautyProcedure> getProcedures_f(){
        return all_procedures;
    }
    public void getBookingHistory(){
        System.out.println("Booking history:");
        System.out.println("ID: \t" +"Name of a procedure: \t" + "Date: \t" + "Time:\t" + "Status:\t");
        for(Booking booking : booking_history){
            if(booking.getStatus() == false){
                String s = "Canceled";
                System.out.println(booking.getID() + "\t" + booking.getName() + "\t" + booking.getDate() + "\t" + booking.getTime() + "\t" + s);
            }else{
                String s = "Available";
                System.out.println(booking.getID() + "\t" + booking.getName() + "\t" + booking.getDate() + "\t" + booking.getTime() + "\t" + s);
            }

        }
    }
    public ArrayList<Booking> getBookingHistory_f(){
        return booking_history;
    }
    public ArrayList<User> getUser(){
        return users;
    }
}
