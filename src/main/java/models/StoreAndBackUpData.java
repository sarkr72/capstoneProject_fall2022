package models;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.mycompany.mvvmexample.App;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import static modelview.SignUpController.officer;

public class StoreAndBackUpData {

    private boolean key;
    private static TreeMap<String, Officer> officerAccounts;
    private static TreeMap<String, Criminal> criminals;
    private static TreeMap<String, Complaint> complaintsHistory;
    private static TreeMap<String, Crime> crimes;

    public StoreAndBackUpData() {
        
        officerAccounts = new TreeMap<>();
        criminals = new TreeMap<>();
        complaintsHistory = new TreeMap<>();
        crimes = new TreeMap<>();
        readFirebase();
        readFirebase2();
        readFirebase3();
    }

    public void addComplaint(String id, Complaint complaint) {
        complaintsHistory.put(id, complaint);
    }

    public static TreeMap<String, Complaint> getComplaints() {
        return complaintsHistory;
    }

    public void addOfficer(String badgeN, Officer user) {
        officerAccounts.put(badgeN, user);
    }

    public static TreeMap<String, Officer> getOfficerAccounts() {
        return officerAccounts;
    }

    public void addCriminal(String id, Criminal criminal) {
        criminals.put(id, criminal);
    }

    public static TreeMap<String, Criminal> getCriminals() {
        return criminals;
    }

    public void addCrime(String id, Crime crime) {
        crimes.put(id, crime);
    }

    public static TreeMap<String, Crime> getCrimes() {
        return crimes;
    }

    public boolean readFirebase() {
        key = false;

        ApiFuture<QuerySnapshot> future = App.fstore.collection("SignUp").get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                for (QueryDocumentSnapshot document : documents) {
                    officer = new Officer(String.valueOf(document.getData().get("firstname")),
                            String.valueOf(document.getData().get("lastName").toString()),
                            String.valueOf(document.getData().get("departmentCodd").toString()),
                            String.valueOf(document.getData().get("badgeNumber").toString()));
                    officerAccounts.put(document.getData().get("badgeNumber").toString(), officer);
//                    listOfOfficer.add(officer);
                }
            } else {
                System.out.println("No data");
            }
            key = true;

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return key;
    }
    
    public boolean readFirebase2() {
        key = false;
        ApiFuture<QuerySnapshot> future = App.fstore.collection("FileAComplain").get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents) {
                    
                    Complaint complaint = new Complaint(String.valueOf(document.getData().get("firstname")),
                            String.valueOf(document.getData().get("lastName").toString()),
                            String.valueOf(document.getData().get("address").toString()),
                            String.valueOf(document.getData().get("telephone").toString()),
                            String.valueOf(document.getData().get("complaintDate").toString()),
                            String.valueOf(document.getData().get("complaintTime").toString()),
                            String.valueOf(document.getData().get("locationOfComplaint").toString()),
                            String.valueOf(document.getData().get("complaintDescription").toString()),
                            String.valueOf(document.getData().get("witnessDetal").toString()),
                            String.valueOf(document.getData().get("complainantSignature").toString()),
                            String.valueOf(document.getData().get("complaintSignDate").toString()));
//                    System.out.println("compolaint:" + complaint);
                            complaintsHistory.put(complaint.getComplaint_id(), complaint);
                }
            } else {
                System.out.println("No data");
            }
            key = true;

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return key;
    }
    
    public boolean readFirebase3() {
        key = false;
        ApiFuture<QuerySnapshot> future = App.fstore.collection("AddCriminal").get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents) {
                   Criminal criminal = new Criminal(String.valueOf(document.getData().get("firstName")),
                            String.valueOf(document.getData().get("lastName").toString()),
                            String.valueOf(document.getData().get("month").toString()),
                            String.valueOf(document.getData().get("day").toString()),
                            String.valueOf(document.getData().get("year").toString()),
                            String.valueOf(document.getData().get("street").toString()),
                            String.valueOf(document.getData().get("city").toString()),
                            String.valueOf(document.getData().get("stateComboBox").toString()),
                            String.valueOf(document.getData().get("postalCode").toString()),
                            String.valueOf(document.getData().get("feet").toString()),
                            String.valueOf(document.getData().get("inches").toString()),
                            String.valueOf(document.getData().get("hairColorComboBox").toString()));

                    criminals.put(String.valueOf(document.getData().get("firstName")), criminal);
                  
                }
            } else {
                System.out.println("No data");
            }
            key = true;

        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return key;
    }
}
