package Repositories;
import Entities.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class BookingRepository {
    private Connection connection;

    public BookingRepository(Connection connection){this.connection = connection;}

    private int intTime(String time){
        int timeInSec = (time.charAt(0) - '0' + time.charAt(1) - '0')*3600;
        timeInSec += (time.charAt(3)-'0' + time.charAt(4)-'0')*60;
        return timeInSec;

    }
    public boolean timeCheker(String date, String time){
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookings");
            int cheker = 0;
            while (resultSet.next()){
                if(date.equals(resultSet.getString("date_")) && intTime(resultSet.getString("time_")) <= intTime(time) && intTime(resultSet.getString("time_"))+3600 >= intTime(time)){
                    cheker = 1;
                    break;
                }
            }
            if(cheker == 1){
                return false;
            }else{
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void addBooking(Booking booking, String username){
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bookings (username, name_of_procedure, date_, time_) VALUES(?,?,?,?)")){
            Statement statement_user = connection.createStatement();
            ResultSet resultSet_user = statement_user.executeQuery("SELECT * FROM users");
            int k = 0;
            while(resultSet_user.next()){
                if(resultSet_user.getString("name_").equals(username)) {
                    Statement statement_procedure = connection.createStatement();
                    ResultSet resultSet_procedure = statement_procedure.executeQuery("SELECT * FROM procedures");
                    int user_status = resultSet_user.getInt("status");
                    double user_balance = resultSet_user.getDouble("balance");
                    int k1 = 0;
                    while (resultSet_procedure.next()) {
                        if (resultSet_procedure.getString("title").equals(booking.getProcedure_name())) {
                            int price = resultSet_procedure.getInt("price");
                            if(user_status == 2){
                                if(user_balance >= price) {
                                    preparedStatement.setString(1, username);
                                    preparedStatement.setString(2, booking.getProcedure_name());
                                    preparedStatement.setString(3, booking.getDate());
                                    preparedStatement.setString(4, booking.getTime());
                                    PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE users SET balance = ? WHERE name_ = ?");
                                    preparedStatement1.setDouble(1,user_balance-price);
                                    preparedStatement1.setString(2, username);
                                    preparedStatement1.executeUpdate();

                                    preparedStatement.executeUpdate();
                                    System.out.println("Successfully booked!");
                                }else{
                                    System.out.println("Sorry, but you don't have enough money!");
                                }
                            }else{
                                preparedStatement.setString(1, username);
                                preparedStatement.setString(2, booking.getProcedure_name());
                                preparedStatement.setString(3, booking.getDate());
                                preparedStatement.setString(4, booking.getTime());

                                preparedStatement.executeUpdate();
                                System.out.println("Successfully booked!");
                            }
                            k1 = 0;
                            break;
                        } else {
                            k1 = 1;
                        }
                    }
                    if (k1 == 1) {
                        System.out.println("Sorry, such procedure doesn't exist ;(");
                    }
                    k=0;
                    break;
                }else{
                    k =1;
                }
            }
            if(k == 1){
                System.out.println("Sorry, such user doesn't exist ;(");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void deleteBooking(String username, String time, String date){
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM bookings WHERE username = ? AND time_ = ? AND date_ = ?")){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, date);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void getAllProcedures(){
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookings");

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String procedure_name = resultSet.getString("name_of_procedure");
                String date = resultSet.getString("date_");
                String time = resultSet.getString("time_");

                System.out.println("ID: " + id + " Username: " + username + " Procedure name: " + procedure_name + " Date: " + date + " Time: " + time);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
