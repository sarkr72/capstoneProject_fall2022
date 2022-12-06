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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Complaint;
import models.StoreAndBackUpData;
import models.Uniteable;

public class ShowSelectedComplaintController implements Initializable {

    private TreeMap<String, Complaint> complaints = StoreAndBackUpData.getComplaints();
    private Complaint complaint;

    @FXML
    private TextArea additionalEvidenceLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private TextArea complaintDescriptionLabel;
    @FXML
    private Button backBtn;
    @FXML
    private Label dateLabel;

    @FXML
    private Label locationOfComplaintLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label signatureDateLabel;

    @FXML
    private Label signatureLabel;

    @FXML
    private Label timeLabel;

    private void showComplaint() {
        complaint = ComplaintHistoryController.complaint;
        if (complaints.containsValue(complaint)) {
            nameLabel.setText(complaint.getFirstName() + " " + complaint.getLastName());
            addressLabel.setText(complaint.getAddress());
            dateLabel.setText(complaint.getComplaintDate());
            phoneLabel.setText(complaint.getTelephone());
            signatureLabel.setText(complaint.getFirstName() + " " + complaint.getLastName());
            signatureDateLabel.setText(complaint.getComplaintSignDate());
            timeLabel.setText(complaint.getComplaintTime());
            additionalEvidenceLabel.setText(complaint.getWitnessDetal());
            complaintDescriptionLabel.setText(complaint.getComplaintDescription());
            locationOfComplaintLabel.setText(complaint.getLocationOfComplaint());
        } else {
        }
    }

    @FXML
    void backToComplaintHistory(ActionEvent event) throws IOException {
        App.setRoot("ComplaintsHistoryView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showComplaint();
    }
}