package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tools.models.Row;

import java.net.URL;
import java.util.ArrayList;
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

        WebEngine webEngine = googleMap.getEngine();
        webEngine.load("https://www.google.com/maps");


        //final Response<List> incident = Utils.GET("incident", .class);

        //System.out.println(incident);



        List<String> list = new ArrayList<>();
        list.add("убиство");
        list.add("кража");
        list.add("кража со взломом");
        list.add("массовые волнения");
        list.add("причинение вреда здоровью");
        incidentType.getItems().setAll(list);
        incidentType.getSelectionModel().selectFirst();



        List<String> vehicleList = new ArrayList<>();
        vehicleList.add("мотоцикл");
        vehicleList.add("автомобиль");
        vehicleList.add("саомлет");
        vehicleList.add("вертолет");
        vehicleList.add("броне автомобиль");
        vehikleType.getItems().setAll(vehicleList);
        vehikleType.getSelectionModel().selectFirst();


        TableColumn firstNameCol = new TableColumn("id");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Row, String>("id"));

        TableColumn lastNameCol = new TableColumn("incident");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Row, String>("incident"));

        TableColumn emailCol = new TableColumn("vehicle");
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Row, String>("vehicle"));


        TableColumn description_ = new TableColumn("description");
        description_.setCellValueFactory(
                new PropertyValueFactory<Row, String>("description"));

        demandsTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol, description_);
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

