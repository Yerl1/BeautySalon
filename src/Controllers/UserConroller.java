package Controllers;
import Entities.*;
import Repositories.UserRepository;

import java.util.Scanner;


public class UserConroller {
    private UserRepository userRepository;

    public UserConroller(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(Scanner scanner){

        System.out.println("Enter name:");
        String username = scanner.next();
        boolean checker = userRepository.addUserChecker(username);
        if(!checker){
            System.out.println("Sorry, this username already exist ;(");
            return;
        }
        System.out.println("Enter the balance: ");
        int balance = scanner.nextInt();



        if(balance >= 2000){
            System.out.println("-------------------------------------------------------------------------------");
            System.out.println("|   We have a great suggestion for you, buy a subscription \"VIP KAZASHKA\"   |");
            System.out.println("|                   and get any procedure for free!!!                         |");
            System.out.println("|                                                                             |");
            System.out.println("|              Do you want to get it for 2000$ ?   1)Yes 2)No                 |");
            System.out.println("-------------------------------------------------------------------------------");
            int choice = scanner.nextInt();
            if(choice == 1){
                VIP_kaz user = new VIP_kaz(username, balance-2000);
                userRepository.addUser(user);
            }else{
                Ordinary user = new Ordinary(username, balance);
                userRepository.addUser(user);
            }
        }else{
            Ordinary user = new Ordinary(username, balance);
            userRepository.addUser(user);
        }
    }

    public void deleteUser(Scanner scanner){
        System.out.println("Enter username:");
        String username = scanner.next();

        userRepository.deleteUser(username);
    }

    public void getALLUsers(){
        userRepository.getAllUsers();
    }


}
