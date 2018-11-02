package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import tools.Response;
import tools.Utils;
import tools.models.AuthModel;
import tools.models.Res;

import java.io.IOException;


public class Controller {

    @FXML
    private javafx.scene.control.TextField login;
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField pwd;

    public void doit(ActionEvent actionEvent) {
        System.out.println(login.getText());
        System.out.println(pwd.getText());
        showMainWindow(); //TODO delete it
        if(Utils.isEmpty(login.getText()) || Utils.isEmpty(pwd.getText())) {
            errorLabel.setText("login or password is empty");
            return;
        }

        //Response<Res> auth = Utils.GET("auth", Res.class, null);
        //System.out.println(auth.getResponse().getValue());

        final Response<Res> auth = Utils.POST("auth", new AuthModel(login.getText(), pwd.getText()), Res.class);

        final String value = auth.getResponse().getValue();

        if (!auth.getResponseState() || !value.equals("success")){
            errorLabel.setText("Incorrect login or password");
        }
        else {
            showMainWindow();
            errorLabel.setText("success");
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }

    }

    private void showMainWindow(){
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            Stage stage = new Stage();
            stage.setTitle("demand");
            stage.setScene(new Scene(root, 1500, 900));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




