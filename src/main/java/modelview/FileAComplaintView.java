package modelview;

import com.mycompany.mvvmexample.App;
import viewmodel.FileAComplaintViewModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.mvvmexample.FirestoreContext;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Criminal;
import models.Complaint;
import models.StoreAndBackUpData;

public class FileAComplaintView {

    private String name;
    private String additionalEvidence;
    private String address;
    private String complaintDescription;
    private String date;
    private String location;
    private String signature;
    private String signatureDate;
    private String time;
    private String telephone;
    private String fName;
    private String lName;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField locationField;
    @FXML
    private TextArea complaintDescriptionLabel;

    @FXML
    private TextField additionalEvidenceLabel;

    @FXML
    private TextField signatureField;

    @FXML
    private TextField signatureDateField;

    @FXML
    private Button submitBrn;

    @FXML
    private Button readButton;

    @FXML
    private TextField outputField;
    private boolean key;
    private ObservableList<Complaint> listOfComplaint = FXCollections.observableArrayList();
    private Complaint complaint;
    private TreeMap<String, Complaint> complaints = StoreAndBackUpData.getComplaints();

    public ObservableList<Complaint> getListOfComplaint() {
        return listOfComplaint;
    }

    @FXML
    private void goBackToLoggedIN(ActionEvent event) throws IOException {
        if (LoggedInPageController.loggedIn.compareTo("true") == 0) {
            App.setRoot("LoggedInView.fxml");
        } else {
            App.setRoot("HomePageView.fxml");
        }
    }

    void initialize() {

        FileAComplaintViewModel FileAComplaintViewModel = new FileAComplaintViewModel();
        firstNameField.textProperty().bindBidirectional(FileAComplaintViewModel.fullNameProperty());
        lastNameField.textProperty().bindBidirectional(FileAComplaintViewModel.fullNameProperty());
        addressField.textProperty().bindBidirectional(FileAComplaintViewModel.addressProperty());
        telephoneField.textProperty().bindBidirectional(FileAComplaintViewModel.telephoneProperty());
        dateField.textProperty().bindBidirectional(FileAComplaintViewModel.complaintDateProperty());
        timeField.textProperty().bindBidirectional(FileAComplaintViewModel.complaintTimeProperty());
        locationField.textProperty().bindBidirectional(FileAComplaintViewModel.locationOfComplaintProperty());
        complaintDescriptionLabel.textProperty().bindBidirectional(FileAComplaintViewModel.complaintDescriptionProperty());
        additionalEvidenceLabel.textProperty().bindBidirectional(FileAComplaintViewModel.complaintTimeProperty());
        signatureField.textProperty().bindBidirectional(FileAComplaintViewModel.witnessDetalProperty());
        signatureDateField.textProperty().bindBidirectional(FileAComplaintViewModel.complainantSignatureProperty());

        submitBrn.disableProperty().bind(FileAComplaintViewModel.isWritePossibleProperty().not());
    }

    @FXML
    private void addRecord(ActionEvent event) {
        addData();
    }

    @FXML
    private void readRecord(ActionEvent event) {
//        readFirebase2();
    }

    public void addData() {

        DocumentReference docRef = App.fstore.collection("FileAComplain").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", firstNameField.getText());
        data.put("lastName", lastNameField.getText());
        data.put("address", addressField.getText());
        data.put("telephone", telephoneField.getText());
        data.put("complaintDate", dateField.getText());
        data.put("complaintTime", timeField.getText());
        data.put("locationOfComplaint", locationField.getText());
        data.put("complaintDescription", complaintDescriptionLabel.getText());
        data.put("witnessDetal", additionalEvidenceLabel.getText());
        data.put("complainantSignature", signatureField.getText());
        data.put("complaintSignDate", signatureDateField.getText());

        ApiFuture<WriteResult> result = docRef.set(data);

        fName = firstNameField.getText();
        lName = lastNameField.getText();
        address = addressField.getText();
        date = dateField.getText();
        time = timeField.getText();
        telephone = telephoneField.getText();
        signature = signatureField.getText();
        signatureDate = signatureDateField.getText();
        location = locationField.getText();
        complaintDescription = complaintDescriptionLabel.getText();
        additionalEvidence = additionalEvidenceLabel.getText();

        if (fName.isBlank()) {

        } else {

            if (address.isBlank()) {

            } else if (date.isBlank()) {

            } else if (telephone.isBlank()) {

            } else if (signature.isBlank()) {

            } else if (signatureDate.isBlank()) {

            } else if (location.isBlank()) {

            } else if (complaintDescription.isBlank()) {

            } else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setContentText("You have successfully submitted the complaint form!");
                a.show();

                // String fname, string lastname, String address, String telephone, String complaintDate, String complaintTime, 
                // String locationOfComplaint, String complaintDescription, String witnessDetal,
                //  String complainantSignature, String complaintSignDate) 
                Complaint c = new Complaint(fName, lName, address, telephone, date, time, location,
                        complaintDescription, additionalEvidence, signature, signatureDate);
                StoreAndBackUpData.getComplaints().put(c.getComplaint_id(), c);
                submitBrn.setVisible(false);
            }

        }

    }

//    public boolean readFirebase2() {
//        key = false;
//
//        //asynchronously retrieve all documents
//        ApiFuture<QuerySnapshot> future = App.fstore.collection("Reference").get();
//        // future.get() blocks on response
//        List<QueryDocumentSnapshot> documents;
//        try {
//            documents = future.get().getDocuments();
//            if (documents.size() > 0) {
//                System.out.println("Outing....");
//                for (QueryDocumentSnapshot document : documents) {
//                    outputField.setText(outputField.getText() + "Firstname: " + document.getData().get("firstName")
//                            + " , LastName: " + document.getData().get("lastName")
//                            + " , Address: " + document.getData().get("address")
//                            + " , Telephone: " + document.getData().get("telephone")
//                            + " , Complaint Date: " + document.getData().get("complaintDate")
//                            + " , Complaint Time:  " + document.getData().get("complaintTime")
//                            + " , Location Of Complaint: " + document.getData().get("locationOfComplaint")
//                            + " , Complaint Description: " + document.getData().get("complaintDescription")
//                            + " , Witness Detal: " + document.getData().get("witnessDetal")
//                            + " , Complainant Signature: " + document.getData().get("complainantSignature")
//                            + " , Complaint Sign Date: \n" + document.getData().get("complaintSignDate"));
//                    System.out.println(" ");
//                    //  System.out.println(document.getfName() + " => " + document.getData().get("fName"));
//
//                    complaint = new Complaint(String.valueOf(document.getData().get("firstname")),
//                            document.getData().get("lastName").toString(),
//                            document.getData().get("address").toString(),
//                            document.getData().get("telephone").toString(),
//                            document.getData().get("complaintDate").toString(),
//                            document.getData().get("complaintTime").toString(),
//                            document.getData().get("locationOfComplaint").toString(),
//                            document.getData().get("complaintDescription").toString(),
//                            document.getData().get("witnessDetal").toString(),
//                            document.getData().get("complainantSignature").toString(),
//                            document.getData().get("complaintSignDate").toString());
//                    
//                            complaints.put(complaint.getComplaint_id(), complaint);
//                    listOfComplaint.add(complaint);
//                }
//            } else {
//                System.out.println("No data");
//            }
//            key = true;
//
//        } catch (InterruptedException | ExecutionException ex) {
//            ex.printStackTrace();
//        }
//        return key;
//    }

}
