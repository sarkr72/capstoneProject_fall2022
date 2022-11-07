package models;

public class Criminal {

    private String address;
    private String data_of_crime;
    private String fName;
    private String id;
    private String lName;
    private String punishment_period;
    private String type_of_crime;
    

    public Criminal(String address, String data_of_crime, String fName, String id, String lName, String punishment_period, String type_of_crime) {
        this.address = address;
        this.data_of_crime = data_of_crime;
        this.fName = fName;
        this.id = id;
        this.lName = lName;
        this.punishment_period = punishment_period;
        this.type_of_crime = type_of_crime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getData_of_crime() {
        return data_of_crime;
    }

    public void setData_of_crime(String data_of_crime) {
        this.data_of_crime = data_of_crime;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPunishment_period() {
        return punishment_period;
    }

    public void setPunishment_period(String punishment_period) {
        this.punishment_period = punishment_period;
    }

    public String getType_of_crime() {
        return type_of_crime;
    }

    public void setType_of_crime(String type_of_crime) {
        this.type_of_crime = type_of_crime;
    }

}
