package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.StringConverter;
import tools.Response;
import tools.Utils;
import tools.models.AreaModel;
import tools.models.Incident;
import tools.models.IncidentType;
import tools.models.InsidentSendModel;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    public Label areaDescription;
    @FXML
    private Label vehikleTypeDescription;
    @FXML
    private Label incidentTypeDescription;
    @FXML    private RadioButton rb1;    @FXML    private RadioButton rb3;    @FXML    private RadioButton rb7;
    @FXML    private RadioButton rb6;    @FXML    private RadioButton rb5;    @FXML    private RadioButton rb4;
    @FXML    private RadioButton rb2;    @FXML    private RadioButton rb8;    @FXML    private RadioButton rb9;
    @FXML    private RadioButton rb10;

    @FXML
    private TextArea description;
    @FXML
    private TableView demandsTable;
    @FXML
    private ComboBox<IncidentType> vehikleType;
    @FXML
    private ComboBox<IncidentType> incidentType;
    @FXML
    private WebView googleMap;
    @FXML
    private ComboBox<AreaModel> area;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // map
        WebEngine webEngine = googleMap.getEngine();
        webEngine.load("https://www.google.com/maps");
        // map


        // incident
        Response<IncidentType[]> incident = Utils.GET("incident-type/", IncidentType[].class);
        incidentType.getItems().setAll(Arrays.asList(incident.getResponse()));
        incidentType.setConverter(new StringConverterImpl<>(incidentType));
        incidentType.getSelectionModel().selectFirst();
        // incident


        //vehicle
        Response<IncidentType[]> vehicles = Utils.GET("vehicle/", IncidentType[].class);
        vehikleType.getItems().setAll(Arrays.asList(vehicles.getResponse()));
        vehikleType.setConverter(new StringConverterImpl<>(vehikleType));
        vehikleType.getSelectionModel().selectFirst();
        //vehicle

        //Area
        Response<AreaModel[]> areas = Utils.GET("area/", AreaModel[].class);
        area.getItems().setAll(Arrays.asList(areas.getResponse()));
        area.setConverter(new StringConverter<AreaModel>() {
            @Override
            public String toString(AreaModel areaModel) {
                return areaModel.getName();
            }

            @Override
            public AreaModel fromString(String s) {
                return area.getItems().stream().filter(itm -> itm.getName().equals(s)).findFirst().orElse(null);
            }
        });
        area.getSelectionModel().selectFirst();
        //Area


        TableColumn cl1 = new TableColumn("id");
        cl1.setCellValueFactory(new PropertyValueFactory<InsidentSendModel, String>("id"));

        TableColumn cl2 = new TableColumn("incident");
        cl2.setCellValueFactory(new PropertyValueFactory<InsidentSendModel, String>("incidentType"));

        TableColumn cl3 = new TableColumn("vehicle");
        cl3.setCellValueFactory(new PropertyValueFactory<InsidentSendModel, String>("vehicleType"));

        TableColumn cl4 = new TableColumn("description");
        cl4.setCellValueFactory(new PropertyValueFactory<InsidentSendModel, String>("description"));

        TableColumn cl5 = new TableColumn("danger level");
        cl5.setCellValueFactory(new PropertyValueFactory<InsidentSendModel, String>("dangerLevel"));

        demandsTable.getColumns().addAll(cl1, cl2, cl3, cl4, cl5);

        refreshIncident();

        ToggleGroup toggleGroup = new ToggleGroup();
        rb1.setToggleGroup(toggleGroup);
        rb3.setToggleGroup(toggleGroup);
        rb7.setToggleGroup(toggleGroup);
        rb6.setToggleGroup(toggleGroup);
        rb5.setToggleGroup(toggleGroup);
        rb4.setToggleGroup(toggleGroup);
        rb2.setToggleGroup(toggleGroup);
        rb8.setToggleGroup(toggleGroup);
        rb9.setToggleGroup(toggleGroup);
        rb10.setToggleGroup(toggleGroup);

        areaOnAction(null);
        onActionincIdentType(null);
        onActionVehikleType(null);

        description.setPromptText("enter the specification ...");

        System.out.println("------------------------");
        System.out.println(incidentType.getValue());
    }

    public void areaOnAction (ActionEvent actionEvent) {


        AreaModel danger = area.getValue();

        areaDescription.setText(danger.getDescription());

        if (danger.getDangerLevel() == null){
            rb1.setSelected(true);
        } else {
            setLevel(danger.getDangerLevel());
        }

    }


    private int getLevel() {
        int result = 0;
        if (rb1.isSelected()) result = 1;
        if (rb2.isSelected()) result = 2;
        if (rb3.isSelected()) result = 3;
        if (rb4.isSelected()) result = 4;
        if (rb5.isSelected()) result = 5;
        if (rb6.isSelected()) result = 6;
        if (rb7.isSelected()) result = 7;
        if (rb8.isSelected()) result = 8;
        if (rb9.isSelected()) result = 9;
        if (rb10.isSelected()) result = 10;
        return result;
    }

    private void setLevel(int level){

        switch (level) {
            case 2 : rb2.setSelected(true); break;
            case 3 : rb3.setSelected(true); break;
            case 4 : rb4.setSelected(true); break;
            case 5 : rb5.setSelected(true); break;
            case 6 : rb6.setSelected(true); break;
            case 7 : rb7.setSelected(true); break;
            case 8 : rb8.setSelected(true); break;
            case 9 : rb9.setSelected(true); break;
            case 10 : rb10.setSelected(true); break;
            default : rb1.setSelected(true); break;
        }
    }


    public void sendToServer(ActionEvent actionEvent) {

        System.out.println(vehikleType.getSelectionModel().getSelectedItem());
        InsidentSendModel sendModel = new InsidentSendModel(
                description.getText(),
                description.getText(),
                getLevel(),
                "werew",
                vehikleType.getValue().getId(),
                incidentType.getValue().getId(),
                area.getSelectionModel().getSelectedItem().getId()
        );

        Utils.POST("incident/", sendModel);
        refreshIncident();
    }

    private void refreshIncident(){
        Response<Incident[]> incidents = Utils.GET("incident/", Incident[].class);
        ObservableList cd = FXCollections.observableArrayList(incidents.getResponse());
        demandsTable.setItems(cd);
    }

    public void declineOnAction(ActionEvent actionEvent) {
        description.setText("");
    }

    public void onActionincIdentType(ActionEvent actionEvent) {
        incidentTypeDescription.setText(incidentType.getValue().getDescription());
    }

    public void onActionVehikleType(ActionEvent actionEvent) {
        vehikleTypeDescription.setText(vehikleType.getValue().getDescription());

    }
}


class StringConverterImpl<T extends IncidentType> extends StringConverter<T>{
    private ComboBox<T> comboBox;
    public StringConverterImpl(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public String toString(T t) {
        return t.getName();
    }

    @Override
    public T fromString(String s) {
        return comboBox.getItems().stream().filter(itm -> itm.getName().equals(s)).findFirst().orElse(null);
    }

}
