package viewmodel;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SignUpViewModel {
    
    // TC: added this page
           
    private final StringProperty fName = new SimpleStringProperty();
    private final StringProperty lName = new SimpleStringProperty();
    private final StringProperty departmentCode = new SimpleStringProperty();
    private final StringProperty badgeNumber = new SimpleStringProperty();

    private final ReadOnlyBooleanWrapper writePossible = new ReadOnlyBooleanWrapper();
    
    public SignUpViewModel() {
        writePossible.bind(fName.isNotEmpty().and(badgeNumber.isNotEmpty()));
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public StringProperty departmentCodeProperty() {
        return departmentCode;
    }
     public StringProperty badgeNumberProperty() {
        return badgeNumber;
    }
        public ReadOnlyBooleanProperty isWritePossibleProperty() {
        return writePossible.getReadOnlyProperty();
    }

}
