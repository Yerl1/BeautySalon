import java.util.ArrayList;

public class Ordinary  extends User implements User_info{


    public Ordinary(String name, int balance){
        this.name = name;
        this.balance = balance;
        status = 2;
        this.taking_procedures = new ArrayList<>();
    }

    public int getBalance(){
        return balance;
    }
    public String getUsername(){
        return name;
    }
    public ArrayList<Booking> getTaking_procedures(){return taking_procedures;}

    public void setTaken_beauty_procedure(Booking procedure) {
        this.taking_procedures.add(procedure);
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getStatus(){
        return status;
    }
}
