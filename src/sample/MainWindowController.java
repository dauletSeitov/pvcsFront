package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;
import tools.Response;
import tools.Utils;
import tools.models.Incident;
import tools.models.IncidentType;
import tools.models.Row;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML    public RadioButton rb1;    @FXML    public RadioButton rb3;    @FXML    public RadioButton rb7;
    @FXML    public RadioButton rb6;    @FXML    public RadioButton rb5;    @FXML    public RadioButton rb4;
    @FXML    public RadioButton rb2;    @FXML    public RadioButton rb8;    @FXML    public RadioButton rb9;
    @FXML    public RadioButton rb10;

    @FXML
    public TextArea description;
    @FXML
    private TableView demandsTable;
    @FXML
    private ComboBox vehikleType;
    @FXML
    private ComboBox incidentType;

    @FXML
    private WebView googleMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // map
        WebEngine webEngine = googleMap.getEngine();
        webEngine.load("https://www.google.com/maps");
        // map


        // incident
        Response<IncidentType[]> incident = Utils.GET("incident/", IncidentType[].class);
        incidentType.getItems().setAll(Arrays.asList(incident.getResponse()));
        incidentType.setConverter(new StringConverterImpl<>());
        incidentType.getSelectionModel().selectFirst();
        // incident


        //vehicle
        Response<IncidentType[]> vehicles = Utils.GET("incident-type/", IncidentType[].class);
        vehikleType.getItems().setAll(Arrays.asList(vehicles.getResponse()));
        vehikleType.setConverter(new StringConverterImpl<>());
        vehikleType.getSelectionModel().selectFirst();
        //vehicle


        TableColumn cl1 = new TableColumn("id");
        cl1.setCellValueFactory(new PropertyValueFactory<Row, String>("id"));

        TableColumn cl2 = new TableColumn("incident");
        cl2.setCellValueFactory(new PropertyValueFactory<Row, String>("incidentType"));

        TableColumn cl3 = new TableColumn("vehicle");
        cl3.setCellValueFactory(new PropertyValueFactory<Row, String>("vehicleType"));

        TableColumn cl4 = new TableColumn("description");
        cl4.setCellValueFactory(new PropertyValueFactory<Row, String>("description"));

        demandsTable.getColumns().addAll(cl1, cl2, cl3, cl4);

        Response<Incident[]> incidents = Utils.GET("incident/", Incident[].class);

        ObservableList cd = FXCollections.observableArrayList(incidents.getResponse());
        demandsTable.setItems(cd);


        ToggleGroup toggleGroup = new ToggleGroup();
        rb1.setToggleGroup(toggleGroup);
        rb1.setSelected(true);
        rb3.setToggleGroup(toggleGroup);
        rb7.setToggleGroup(toggleGroup);
        rb6.setToggleGroup(toggleGroup);
        rb5.setToggleGroup(toggleGroup);
        rb4.setToggleGroup(toggleGroup);
        rb2.setToggleGroup(toggleGroup);
        rb8.setToggleGroup(toggleGroup);
        rb9.setToggleGroup(toggleGroup);
        rb10.setToggleGroup(toggleGroup);
        rb1.setSelected(true);

        description.setPromptText("enter the specification ...");

    }


}


class StringConverterImpl<T extends IncidentType> extends StringConverter<T>{


    @Override
    public String toString(T t) {
        return t.getName();
    }

    @Override
    public T fromString(String s) {
        return null;
    }

//            public AirPort fromString(String string) {
//                return combo.getItems().stream().filter(ap ->
//                        ap.getName().equals(string)).findFirst().orElse(null);
//            }
}
