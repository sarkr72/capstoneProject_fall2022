package modelview;

import com.mycompany.mvvmexample.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Complaint;
import models.StoreAndBackUpData;

public class FileAComplaintController implements Initializable {

    private TreeMap<String, Complaint> complaints = StoreAndBackUpData.getComplaints();
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
    private Button backBtn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextArea additionalEvidenceLabel;

    @FXML
    private TextField addressField;

    @FXML
    private TextArea complaintDescriptionLabel;

    @FXML
    private TextField dateField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField signatureDateField;

    @FXML
    private TextField signatureField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField timeField;

    @FXML
    private Button submitBrn;

    @FXML
    void submit(ActionEvent event) {
        name = nameField.getText();
        address = addressField.getText();
        date = dateField.getText();
        time = timeField.getText();
        telephone = telephoneField.getText();
        signature = signatureField.getText();
        signatureDate = signatureDateField.getText();
        location = locationField.getText();
        complaintDescription = complaintDescriptionLabel.getText();
        additionalEvidence = additionalEvidenceLabel.getText();

        if (name.isBlank()) {
            System.err.println("called");
        } else {

            String[] n = name.split(" ");
            fName = n[0];
            lName = n[1];

            if (address.isBlank()) {

            } else if (date.isBlank()) {

            } else if (telephone.isBlank()) {

            } else if (signature.isBlank()) {

            } else if (signatureDate.isBlank()) {

            } else if (location.isBlank()) {

            } else if (complaintDescription.isBlank()) {

            } else {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.CONFIRMATION);
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

    @FXML
    void backToLoggedIn(ActionEvent event) throws IOException {
        if (LoggedInPageController.loggedIn.compareTo("true") == 0) {
            App.setRoot("LoggedInView.fxml");
        } else {
            App.setRoot("HomePageView.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Image image = new Image("/controller/image.png", 645, 650, false, false);
//        imageview.setImage(image);
//        imageview.setFitHeight(645);
//        imageview.setFitWidth(650);

    }
}
