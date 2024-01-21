package Entities;

public class VIP_kaz extends User{
    public VIP_kaz(String name, int balance){
        this.name = name;
        this.balance = balance;
        status = 777;
    }

    public int getBalance(){
        return balance;
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
    @Override
    public void balanceCalc(int procedurePrice){
        System.out.println("I'm a VIP Kazashka, sweety!");
    }
}
