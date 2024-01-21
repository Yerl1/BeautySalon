package Entities;

import java.util.ArrayList;

public abstract class User{
    protected int id;
    protected String name;
    protected int status;
    protected int balance;
    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public int getBalance(){
        return balance;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
    public void setUsername(String username){this.name = username;}
    public String getUsername(){
        return name;
    }
    public int getStatus() {
        return status;
    }
    public void balanceCalc(int procedurePrice){};
}

