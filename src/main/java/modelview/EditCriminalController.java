
package modelview;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.mycompany.mvvmexample.App;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import models.Criminal;
import models.StoreAndBackUpData;

/**
 * FXML Controller class
 *
 * @author Rsand
 */
public class EditCriminalController implements Initializable {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField MonthField;
    @FXML
    private TextField DayField;
    @FXML
    private TextField YearField;
    @FXML
    private TextField placeOfBirthField;
    @FXML
    private TextField StreetField;
    @FXML
    private TextField CityField;
    @FXML
    private ComboBox<String> stateComboBox;
    @FXML
    private TextField PostalCodeField;
    @FXML
    private RadioButton MaleRadio;
    @FXML
    private ToggleGroup sexChoice;
    @FXML
    private RadioButton FemaleRadio;
    @FXML
    private TextField feetField;
    @FXML
    private TextField inchesField;
    @FXML
    private TextField weightField;
    @FXML
    private ComboBox<String> raceComboBox;
    @FXML
    private ComboBox<String> ethnicityComboBox;
    @FXML
    private ComboBox<String> eyeColorComboBox;
    @FXML
    private ComboBox<String> hairColorComboBox;
    @FXML
    private Button saveButton;
    private Criminal criminal;

    private ObservableList<String> stateList = FXCollections
            .observableArrayList("Alabama", "Alaska", "American Samoa", "Arizona",
                    "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
                    "District of Columbia", "Florida", "Georgia", "Guam", "Hawaii",
                    "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
                    "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
                    "Minor Outlying Islands", "Mississippi", "Missouri", "Montana", "Nebraska",
                    "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
                    "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Pennsylvania",
                    "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
                    "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");

    private ObservableList<String> raceList = FXCollections
            .observableArrayList("White", "Black or African American", "American Indian or Alaska Native",
                    "Asian", "Native Hawaiian or Other Pacific Islander", "Other");

    private ObservableList<String> ethnicityList = FXCollections
            .observableArrayList("Hispanic or Latino", "Not Hispanic or Latino");

    private ObservableList<String> eyeColorList = FXCollections
            .observableArrayList("Brown", "Blue", "Green", "Hazel", "Amber", "Gray");

    private ObservableList<String> hairColorList = FXCollections
            .observableArrayList("Black", "Blonde", "Brown", "Red", "White");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criminal = CriminalListController.criminal;
        firstNameField.setText(criminal.getfName());
        lastNameField.setText(criminal.getlName());
        MonthField.setText(criminal.getDate_of_birth().substring(0, 2));
        DayField.setText(criminal.getDate_of_birth().substring(3, 5));
        YearField.setText(criminal.getDate_of_birth().substring(6));
        placeOfBirthField.setText(criminal.getPlace_of_birth());

        String[] address = criminal.getAddress().split(",");
        StreetField.setText(address[0]);
        CityField.setText(address[1]);

        stateComboBox.setItems(stateList);
        stateComboBox.setValue(address[2]);
        PostalCodeField.setText(address[3]);

        MaleRadio.setToggleGroup(sexChoice);
        FemaleRadio.setToggleGroup(sexChoice);

//        if (criminal.getSex().equals("Male")) {
//            sexChoice.selectToggle(MaleRadio);
//        } else {
//            sexChoice.selectToggle(FemaleRadio);
//        }

        String[] height = criminal.getHeight().split("' ");
        feetField.setText(height[0]);
        inchesField.setText(height[1].replace("\"", ""));

        weightField.setText(criminal.getWeight());
        raceComboBox.setItems(raceList);
        raceComboBox.setValue(criminal.getRace());
        ethnicityComboBox.setItems(ethnicityList);
        ethnicityComboBox.setValue(criminal.getEthnicity());
        eyeColorComboBox.setItems(eyeColorList);
        eyeColorComboBox.setValue(criminal.getEyeColor());
        hairColorComboBox.setItems(hairColorList);
        hairColorComboBox.setValue(criminal.getHairColor());
    }

    @FXML
    private void backButtonPress(ActionEvent event) throws IOException {
        App.setRoot("CriminalDetailView.fxml");
    }

    @FXML
    private void saveButtonPress(ActionEvent event) throws IOException {
        criminal.setfName(firstNameField.getText());
        criminal.setlName(lastNameField.getText());
        String dateOfBirth = MonthField.getText()
                + "/" + DayField.getText()
                + "/" + YearField.getText();
        criminal.setDate_of_birth(dateOfBirth);
        criminal.setPlace_of_birth(placeOfBirthField.getText());
        String address = StreetField.getText()
                + ", " + CityField.getText()
                + ", " + stateComboBox.getValue()
                + ", " + PostalCodeField.getText();
        criminal.setAddress(address);
        criminal.setSex(((RadioButton) sexChoice.getSelectedToggle()).getText());
        String height = feetField.getText() + "' " + inchesField.getText() + "\"";
        criminal.setHeight(height);
        criminal.setWeight(weightField.getText());
        criminal.setRace(raceComboBox.getValue());
        criminal.setEthnicity(ethnicityComboBox.getValue());
        criminal.setEyeColor(eyeColorComboBox.getValue());
        criminal.setHairColor(hairColorComboBox.getValue());

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Criminal Detail Changes Saved");
        a.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    App.setRoot("CriminalDetailView.fxml");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
//        StoreAndBackUpData.getCriminals().put(criminal.getId(), criminal);
        addData();
        System.out.println(criminal.toString());

    }
    
    public void addData() {

        DocumentReference docRef = App.fstore.collection("AddCriminal").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("firstName", firstNameField.getText());
        data.put("lastName", lastNameField.getText());
        data.put("month", MonthField.getText());
        data.put("day", DayField.getText());
        data.put("year", YearField.getText());
        data.put("placeOfBirth", placeOfBirthField.getText());
        data.put("street", StreetField.getText());
        data.put("city", CityField.getText());
        data.put("stateComboBox", stateComboBox.getValue());
        data.put("postalCode", PostalCodeField.getText());
        data.put("maleRadio", MaleRadio.getText());
        data.put("femaleRadio", FemaleRadio.getText());
        data.put("feet", feetField.getText());
        data.put("inches", inchesField.getText());
        data.put("weight", weightField.getText());
        data.put("raceComboBox", raceComboBox.getValue());
        data.put("ethnicityComboBox", ethnicityComboBox.getValue());
        data.put("eyeColorComboBox", eyeColorComboBox.getValue());
        data.put("hairColorComboBox", hairColorComboBox.getValue());

        //  data.put("Age", Integer.parseInt(ageField.getText()));
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);

        // clearTextField();
    }
}
