public class Booking {
    private static int local_id = 1;
    private int id;
    private String procedure_name;
    private String date;
    private String time;
    private Boolean status;
    public Booking(String procedure_name, String date, String time) {
        this.id = local_id++;
        this.procedure_name = procedure_name;
        this.date = date;
        this.time = time;
        this.status = true;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }

    //Getters
    public int getID() {return id;}

    public String getName() {
        return procedure_name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
    public Boolean getStatus(){return status;}
}
