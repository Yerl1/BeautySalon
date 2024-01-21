import java.util.ArrayList;

public class User{
    protected String name;
    protected int status;
    protected int balance;
    protected ArrayList<Booking> taking_procedures;
    public int getBalance(){
        return balance;
    }
    public ArrayList<Booking> getTaking_procedures(){return taking_procedures;}

    public void setTaken_beauty_procedure(Booking procedure) {
        this.taking_procedures.add(procedure);
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
    public String getUsername(){
        return name;
    }
    public int getStatus(){
        return status;
    }
    public void Delete_booking(int id){
        taking_procedures.remove(id);
    }
}

