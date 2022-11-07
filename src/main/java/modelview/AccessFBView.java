package modelview;

import com.mycompany.mvvmexample.App;
import viewmodel.AccessDataViewModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.mvvmexample.FirestoreContext;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Criminal;

public class AccessFBView {

    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField IdField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField DateOfCrimeField;
    @FXML
    private TextField TypeOfCrimeField;
    @FXML
    private TextField PunishmentPeriodField;

    @FXML
    private Button writeButton;
    @FXML
    private Button readButton;

    @FXML
    private TextArea outputField;
    private boolean key;
    private ObservableList<Criminal> listOfCriminal = FXCollections.observableArrayList();
    private Criminal criminal;

    public ObservableList<Criminal> getListOfCriminal() {
        return listOfCriminal;
    }

    void initialize() {

        AccessDataViewModel accessDataViewModel = new AccessDataViewModel();
        fNameField.textProperty().bindBidirectional(accessDataViewModel.fNameProperty());
        lNameField.textProperty().bindBidirectional(accessDataViewModel.lnameProperty());
        IdField.textProperty().bindBidirectional(accessDataViewModel.idProperty());
        addressField.textProperty().bindBidirectional(accessDataViewModel.addressProperty());
        DateOfCrimeField.textProperty().bindBidirectional(accessDataViewModel.dateOfCrimeProperty());
        TypeOfCrimeField.textProperty().bindBidirectional(accessDataViewModel.typeOfCrimeProperty());
        PunishmentPeriodField.textProperty().bindBidirectional(accessDataViewModel.punishmentPeriodProperty());

        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());
    }

    @FXML
    private void addRecord(ActionEvent event) {
        addData();
    }

    @FXML
    private void readRecord(ActionEvent event) {
        readFirebase();
    }

    public void addData() {

        DocumentReference docRef = App.fstore.collection("Reference").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("fName", fNameField.getText());
        data.put("lName", lNameField.getText());
        data.put("id", IdField.getText());
        data.put("address", addressField.getText());
        data.put("date_of_crime", DateOfCrimeField.getText());
        data.put("type_of_crime", TypeOfCrimeField.getText());
        data.put("punishment_period", PunishmentPeriodField.getText());

        //  data.put("Age", Integer.parseInt(ageField.getText()));
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public boolean readFirebase() {
        key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = App.fstore.collection("Reference").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (documents.size() > 0) {
                System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents) {
                    outputField.setText(outputField.getText() + "First Name: " + document.getData().get("fName")
                            + " , Last Name: " + document.getData().get("lName")
                            + " , id: " + document.getData().get("id")
                            + " , address: " + document.getData().get("address")
                            + " , Date of Crime:  " + document.getData().get("date_of_crime")
                            + " , Type of Crime: " + document.getData().get("type_of_crime")
                            + " , Punishment Period: \n" + document.getData().get("punishment_period"));
                            System.out.println(" ");
                  //  System.out.println(document.getfName() + " => " + document.getData().get("fName"));
                    criminal = new Criminal(String.valueOf(document.getData().get("fName")),
                            document.getData().get("lName").toString(),
                            document.getData().get("id").toString(),
                            document.getData().get("address").toString(),
                            document.getData().get("date_of_crime").toString(),
                            document.getData().get("type_of_crime").toString(),
                            document.getData().get("punishment_period").toString());

                    //Integer.parseInt(document.getData().get("Age").toString()));
                    listOfCriminal.add(criminal);
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
