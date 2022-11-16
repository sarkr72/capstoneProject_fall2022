package models;

public class Complaint {

    private String fullName;
    private String address;
    private String telephone;
    private String complaintDate;
    private String complaintTime;
    private String locationOfComplaint;
    private String complaintDescription;
    private String witnessDetal;
    private String complainantSignature;
    private String complaintSignDate;

    public Complaint(String fullName, String address, String telephone, String complaintDate, String complaintTime, String locationOfComplaint, String complaintDescription, String witnessDetal, String complainantSignature, String complaintSignDate) {
        this.fullName = fullName;
        this.address = address;
        this.telephone = telephone;
        this.complaintDate = complaintDate;
        this.complaintTime = complaintTime;
        this.locationOfComplaint = locationOfComplaint;
        this.complaintDescription = complaintDescription;
        this.witnessDetal = witnessDetal;
        this.complainantSignature = complainantSignature;
        this.complaintSignDate = complaintSignDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(String complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getLocationOfComplaint() {
        return locationOfComplaint;
    }

    public void setLocationOfComplaint(String locationOfComplaint) {
        this.locationOfComplaint = locationOfComplaint;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    public String getWitnessDetal() {
        return witnessDetal;
    }

    public void setWitnessDetal(String witnessDetal) {
        this.witnessDetal = witnessDetal;
    }

    public String getComplainantSignature() {
        return complainantSignature;
    }

    public void setComplainantSignature(String complainantSignature) {
        this.complainantSignature = complainantSignature;
    }

    public String getComplaintSignDate() {
        return complaintSignDate;
    }

    public void setComplaintSignDate(String complaintSignDate) {
        this.complaintSignDate = complaintSignDate;
    }

}
