
package models;


public class Officer {
    
    private String fName;
    private String lName;
    private String badgeN;
    private String departmentCodd;

    public String getDepartmentCodd() {
        return departmentCodd;
    }

    public void setDepartmentCodd(String departmentCodd) {
        this.departmentCodd = departmentCodd;
    }

    public Officer(String fName, String lName, String departmentCodd, String badgeN) {
        this.fName = fName;
        this.lName = lName;
        this.badgeN = badgeN;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getBadgeN() {
        return badgeN;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    
    

    
    
    @Override
    public String toString() {
        return "Employee{" + "fName=" + fName + ", lName=" + lName + ", badgeN=" + badgeN + '}';
    }
}
