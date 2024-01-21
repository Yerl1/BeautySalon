package Controllers;
import Entities.*;
import Repositories.BeautyRepository;
import java.util.Scanner;

public class BeautyController {
    BeautyRepository beautyRepository;

    public BeautyController(BeautyRepository beautyRepository){this.beautyRepository = beautyRepository;}

    public void addProcedure(Scanner scanner){
        System.out.println("Enter new procedure name:");
        String procedureName = scanner.next();

        System.out.println("Enter new procedure price:");
        int price = scanner.nextInt();

        System.out.println("Enter new procedure's description:");
        String description = scanner.next();

        BeautyProcedure newProcedure = new BeautyProcedure(procedureName, price, description);
        beautyRepository.addProcedure(newProcedure);

    }

    public void deleteProcedure(Scanner scanner){
        System.out.println("Enter procedure name:");
        String title = scanner.next();

        beautyRepository.deleteProcedure(title);
    }

    public void getProcedures(){
        beautyRepository.getAllProcedures();
    }


}
