package Repositories;
import java.sql.*;
import Entities.User;

public class UserRepository {
    private Connection connection;
    public UserRepository(Connection connection){this.connection = connection;}

    public boolean addUserChecker(String username) {
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            int checker = 0;
            while(resultSet.next()){
                if(resultSet.getString("name_").equals(username)){
                    checker = 1;
                    break;
                }
            }
            if(checker == 1){
                return false;
            }else{
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(User user){
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name_, status, balance) VALUES (?, ?, ?)")){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getStatus());
            preparedStatement.setInt(3, user.getBalance());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
                System.out.println("User added successfully!");
            } else {
                System.out.println("Failed to add user. Please try again.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteUser(String username){
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE name_ = ?")){
            preparedStatement.setString(1, username);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void getAllUsers(){
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("name_");
                int status = resultSet.getInt("status");
                int balance = resultSet.getInt("balance");
                if(status == 777){
                    System.out.println("|ID: " + id + "| Name: " + username + "| Status: VIP KAZASHKA | " + "Balance: " + balance + " |");
                }else{
                    System.out.println("|ID: " + id + "| Name: " + username + "| Status: Ordinary " + "| Balance: " + balance + " |");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
