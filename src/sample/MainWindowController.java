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
import tools.models.Row;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public RadioButton rb1;
    public RadioButton rb3;
    public RadioButton rb7;
    public RadioButton rb6;
    public RadioButton rb5;
    public RadioButton rb4;
    public RadioButton rb2;
    public RadioButton rb8;
    public RadioButton rb9;
    public RadioButton rb10;
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
        Response<Incident[]> incident = Utils.GET("incident/", Incident[].class);
        incidentType.getItems().setAll(Arrays.asList(incident.getResponse()));

        incidentType.setConverter(new StringConverter<Incident>() {

            @Override
            public String toString(Incident incident) {
                return incident.getName();
            }

            @Override
            public Incident fromString(String s) {
                return null;
            }
//            @Override
//            public AirPort fromString(String string) {
//                return combo.getItems().stream().filter(ap ->
//                        ap.getName().equals(string)).findFirst().orElse(null);
//            }
        });
        incidentType.getSelectionModel().selectFirst();
        // incident



        Response<Incident[]> vehicles = Utils.GET("vehicle/", Incident[].class);

//        List<String> vehicleList = new ArrayList<>();
//        vehicleList.add("мотоцикл");
//        vehicleList.add("автомобиль");
//        vehicleList.add("саомлет");
//        vehicleList.add("вертолет");
//        vehicleList.add("броне автомобиль");
        vehikleType.getItems().setAll(Arrays.asList(incident.getResponse()));
        vehikleType.getSelectionModel().selectFirst();


        TableColumn cl1 = new TableColumn("id");
        cl1.setCellValueFactory(new PropertyValueFactory<Row, String>("id"));

        TableColumn cl2 = new TableColumn("incident");
        cl2.setCellValueFactory(new PropertyValueFactory<Row, String>("incident"));

        TableColumn cl3 = new TableColumn("vehicle");
        cl3.setCellValueFactory(new PropertyValueFactory<Row, String>("vehicle"));


        TableColumn cl4 = new TableColumn("description");
        cl4.setCellValueFactory(new PropertyValueFactory<Row, String>("description"));

        demandsTable.getColumns().addAll(cl1, cl2, cl3, cl4);
        ObservableList cd = FXCollections.observableArrayList(
                new Row(1L, "кража","мотоцикл", "кража на пересечении улиц Абай Ауезов"),
                new Row(2L, "убиство", "автомобиль", "убиство на Сатпаева Д23 кв 45"),
                new Row(3L, "массовые волнения", "броне автомобиль", "на центральной площади массовые волнения"));
        demandsTable.setItems(cd);


        ToggleGroup toggleGroup = new ToggleGroup();
        rb1.setToggleGroup(toggleGroup);
        rb3.setToggleGroup(toggleGroup);;
        rb7.setToggleGroup(toggleGroup);;
        rb6.setToggleGroup(toggleGroup);;
        rb5.setToggleGroup(toggleGroup);;
        rb4.setToggleGroup(toggleGroup);;
        rb2.setToggleGroup(toggleGroup);;
        rb8.setToggleGroup(toggleGroup);;
        rb9.setToggleGroup(toggleGroup);;
        rb10.setToggleGroup(toggleGroup);


        description.setPromptText("enter the specification ...");

    }
}

