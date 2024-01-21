package Repositories;
import Controllers.BeautyController;
import Entities.*;

import java.sql.*;

public class BeautyRepository {
    Connection connection;

    public BeautyRepository(Connection connection){this.connection = connection;}

    public void addProcedure(BeautyProcedure newProcedure){
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO procedures (title, price, description) VALUES(?,?,?)")){
            preparedStatement.setString(1,newProcedure.getProcedureName());
            preparedStatement.setInt(2, newProcedure.getPrice());
            preparedStatement.setString(3, newProcedure.getDescription());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteProcedure(String title){
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM procedures WHERE title = ?")){
            preparedStatement.setString(1, title);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getAllProcedures(){
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM procedures");

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int price = resultSet.getInt("price");
                String description  = resultSet.getString("description");

                System.out.println("ID: " + id + " Name of procedure: " + title + " Price: " +price + " Description: " + description);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
