package Entities;


import java.util.ArrayList;

public class Ordinary  extends User {

    public Ordinary(String name, int balance){
        this.name = name;
        this.balance = balance;
        status = 2;
    }

    public int getBalance(){
        return balance;
    }
    public String getUsername(){
        return name;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getStatus(){
        return status;
    }
    @Override
    public void balanceCalc(int procedurePrice){
        this.balance = this.balance - procedurePrice;
    }
}
