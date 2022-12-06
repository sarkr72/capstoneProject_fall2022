/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelview;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.mvvmexample.App;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Officer;
import models.StoreAndBackUpData;

public class SignUpController implements Initializable {

    private String fName;
    private String lName;
    private TreeMap<String, Officer> officerAccounts = StoreAndBackUpData.getOfficerAccounts();
    private int dCode = 4651;
    // TC: add stuff
    private boolean key;
    public static Officer officer;
    private Officer officer2;
    private ObservableList<Officer> listOfOfficer = FXCollections.observableArrayList();
    @FXML
    private ImageView imageview;
    @FXML
    private Label confirmLabel;

    @FXML
    private TextField departmentCodeField;

    @FXML
    private Label departmentCodeLabel;
    @FXML
    private TextField badgeNumberField;

    @FXML
    private TextField fNameField;

    @FXML
    private TextField lNameField;

    @FXML
    private Label fNameLabel;

    @FXML
    private Label lNameLabel;

    @FXML
    private Button submitBtn;
    @FXML
    private Button backBtn;

    @FXML
    private Pane pane;

    @FXML
    void goBackToHomePage(ActionEvent event) throws IOException {
        App.setRoot("HomePageView.fxml");
    }
    
    @FXML
    void submitMethod(ActionEvent event) {
        fName = fNameField.getText();
        lName = lNameField.getText();

        if (fName.isBlank()) {
            fNameLabel.setText("Enter your first name");
            lNameLabel.setText("");
            departmentCodeLabel.setText("");
        } else {
            String type = "";
            for (int i = 0; i < fName.length(); i++) {
                char ch = fName.charAt(i);
                if (Character.isDigit(ch)) {
                    type = "digit";
                }
            }
            if (type.compareTo("digit") == 0) {
                fNameLabel.setText("Enter letters only");
                lNameLabel.setText("");
                departmentCodeLabel.setText("");
            } else {
                if (lName.isBlank()) {
                    lNameLabel.setText("Enter your last name");
                    fNameLabel.setText("");
                    departmentCodeLabel.setText("");
                } else {
                    for (int i = 0; i < lName.length(); i++) {
                        char ch = lName.charAt(i);
                        if (Character.isDigit(ch)) {
                            type = "digit";
                        }
                    }
                    if (type.compareTo("digit") == 0) {
                        lNameLabel.setText("Enter letters only");
                        fNameLabel.setText("");
                        departmentCodeLabel.setText("");
                    } else {
                        if (departmentCodeField.getText().isBlank()) {
                            departmentCodeLabel.setText("Enter your department code");
                            fNameLabel.setText("");
                            lNameLabel.setText("");
                        } else {
                            for (int i = 0; i < departmentCodeField.getText().length(); i++) {
                                char ch = departmentCodeField.getText().charAt(i);
                                if (Character.isLetter(ch)) {
                                    type = "letter";
                                    System.err.println(i + " " + ch);
                                }
                            }
                            if (type.compareTo("letter") == 0) {
                                departmentCodeLabel.setText("Enter digits only");
                                fNameLabel.setText("");
                                lNameLabel.setText("");
                            } else {
                                if (dCode == (Integer.parseInt(departmentCodeField.getText()))) {
                                    
                                    departmentCodeLabel.setText("");
                                     officer2 = new Officer(fName, lName, dCode + "", rand());
                                    officerAccounts.put(officer2.getBadgeN(), officer2);
                                    badgeNumberField.setText(officer2.getBadgeN());
                                    submitBtn.setDisable(true);
                                    confirmLabel.setVisible(true);
                                    addData();
//                                    readFirebase();
                                    
                                } else {
                                    departmentCodeLabel.setText("Department code does not match");
                                    fNameLabel.setText("");
                                    lNameLabel.setText("");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private String rand() {
        int counter = 0;
        String str = "";
        while (counter < 4) {
            int random = (int) (Math.random() * (10 + 1 - 0)) + 0;
            str = str + random;
            counter++;
        }
        if (StoreAndBackUpData.getOfficerAccounts().containsKey(str)) {
            return rand();
        } else {
            return str;
        }
    }

    public void addData() {

        DocumentReference docRef = App.fstore.collection("SignUp").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", fNameField.getText());
        data.put("lastName", lNameField.getText());
        data.put("departmentCodd", departmentCodeField.getText());
        data.put("badgeNumber", badgeNumberField.getText());
        System.out.println("badge add data: " + badgeNumberField.getText());
        //  data.put("Age", Integer.parseInt(ageField.getText()));
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);

        // clearTextField();
    }

    // TC: added stuff
//    public  boolean readFirebase() {
//        key = false;
//
//        ApiFuture<QuerySnapshot> future = App.fstore.collection("SignUp").get();
//        List<QueryDocumentSnapshot> documents;
//        try {
//            documents = future.get().getDocuments();
//            if (documents.size() > 0) {
//                for (QueryDocumentSnapshot document : documents) {
////                    outputField.setText(outputField.getText() + "Firstname: " + document.getData().get("firstName")
////                            + " , LastName: " + document.getData().get("lastName")
////                            + " , Address: " + document.getData().get("departmentCodd")
////                            + " , Telephone: " + document.getData().get("badgeNumber"));
//                    System.out.println(" ");
//                    //  System.out.println(document.getfName() + " => " + document.getData().get("fName"));
//
//                    officer = new Officer(String.valueOf(document.getData().get("firstname")),
//                            String.valueOf(document.getData().get("lastName").toString()),
//                            String.valueOf(document.getData().get("departmentCodd").toString()),
//                            String.valueOf(document.getData().get("badgeNumber").toString()));
//                    System.out.println("badge read: "+ document.getData().get("badgeNumber").toString());
//                    StoreAndBackUpData.getOfficerAccounts().put(document.getData().get("badgeNumber").toString(), officer);
//                    listOfOfficer.add(officer);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        submitBtn.setDisable(false);
        confirmLabel.setVisible(false);
        Image image = new Image("image.png", 645, 650, false, false);
        imageview.setImage(image);
        imageview.setFitHeight(645);
        imageview.setFitWidth(650);
    }
}
