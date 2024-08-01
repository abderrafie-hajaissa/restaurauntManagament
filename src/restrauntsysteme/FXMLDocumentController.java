/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restrauntsysteme;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author Abderrafie
 */
public class FXMLDocumentController {

    @FXML
    private ImageView si_log_image;

    @FXML
    private AnchorPane si_SideLoginForm;

    @FXML
    private Button si_log_CreatBtn;

    @FXML
    private Button si_log_LoginBtn;

    @FXML
    private PasswordField si_log_Password;

    @FXML
    private TextField si_log_Username;

    /**
     * *******************************************************************************
     */
    /**
     * *******************************************************************************
     */
    @FXML
    private AnchorPane pi_regSideForm;

    @FXML
    private Button pi_reg_loginNow;

    @FXML
    private ComboBox<?> pi_reg_Question;

    @FXML
    private TextField pi_reg_Username;

    @FXML
    private TextField pi_reg_answer;

    @FXML
    private PasswordField pi_reg_password;

    @FXML
    private Button pi_reg_SignUp;

    private Alert alert;
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * ****************************************************************************************
     */
    public void SwitchForm(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == si_log_CreatBtn) {
            slider.setNode(si_SideLoginForm);
            slider.setToY(680);
            slider.setDuration(Duration.seconds(.5));

                Listquestion();

           
            slider.play();
            
        } else if (event.getSource() == pi_reg_loginNow) {

            slider.setNode(si_SideLoginForm);
            slider.setToY(0);
            slider.setDuration(Duration.seconds(.5));

        
            slider.play();

        }

    }

    /**
     * *******************************************************************************
     */
    public String[] listquestion = {"What Is Favorit Food ?", "What Is Favorit Drink ?", "What Is Favorit Sose ?"};

    public void Listquestion() {

        List<String> listQ = new ArrayList<>();

        for (String data : listquestion) {
            listQ.add(data);

        }
        ObservableList listData = FXCollections.observableArrayList(listQ);
        pi_reg_Question.setItems(listData);

    }

    /**
     * **********************************************************************************
     */
    public void RegisterBtn() {

        if (pi_reg_Username.getText().isEmpty() || pi_reg_password.getText().isEmpty()
                || pi_reg_Question.getSelectionModel().getSelectedItem() == null || pi_reg_answer.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message! ");
            alert.setHeaderText(null);
            alert.setContentText("Your Information Is Empty!");
            alert.showAndWait();

        } else {

            String username = pi_reg_Username.getText();
            String password = pi_reg_password.getText();
            String answer = pi_reg_answer.getText();

            String requett = "INSERT INTO `login`(`username`, `password`, `question`, `answer`) VALUES (?,?,?,?)";

            try {
                prepare = Database.ConnectionDB().prepareStatement(requett);

                prepare.setString(1, username);
                prepare.setString(2, password);
                prepare.setString(3, (String) pi_reg_Question.getSelectionModel().getSelectedItem());
                prepare.setString(4, answer);

                if (prepare.executeUpdate() != 0) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message! ");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Account Has  been created!!");
                    alert.showAndWait();

                    pi_reg_Username.setText("");
                    pi_reg_password.setText("");
                    pi_reg_Question.getSelectionModel().clearSelection();
                    pi_reg_answer.setText("");

                    TranslateTransition slider = new TranslateTransition();

                    slider.setNode(si_SideLoginForm);
                    slider.setToY(0);
                    slider.setDuration(Duration.seconds(.5));

                    slider.setOnFinished((ActionEvent e) -> {

                        si_SideLoginForm.setVisible(true);
                        pi_regSideForm.setVisible(false);

                    });
                    slider.play();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message! ");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Account Has not been created!!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
            }

        }
    }

    /**
     * **********************************************************************************************************
     */
    /**
     * **********************************************************************************************************
     */
    /**
     * **********************************************************************************************************
     */
    public void LoginBtn() throws IOException {

        if (si_log_Username.getText().isEmpty() || si_log_Password.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message! ");
            alert.setHeaderText(null);
            alert.setContentText("Username Or Password Is Empty");
            alert.showAndWait();
        } else {

            String username = si_log_Username.getText();
            String password = si_log_Password.getText();

            String query = "SELECT * FROM login WHERE `username`=? AND `password`=?";

            try {

                prepare = Database.ConnectionDB().prepareStatement(query);

                prepare.setString(1, username);
                prepare.setString(2, password);

                result = prepare.executeQuery();

                if (result.next()) {

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Succufuly!");
                    alert.showAndWait();

                    //link YOUR MAINFORM AND LOGIN Succefuly
                    data.username = si_log_Username.getText();

                    Parent root = FXMLLoader.load(getClass().getResource("mainFrom.fxml"));

                    Stage stage = new Stage();

                    Scene scene = new Scene(root);
                    stage.setTitle("Restraunt System");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);
                    stage.setScene(scene);
                    stage.show();

                    si_log_LoginBtn.getScene().getWindow().hide();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Username Or password Is not Correct!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Connection Faible", "Error Message", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    /**
     * ******************************************************************************************************************
     */

    public void ForgetPasswordLink() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ForgetPasswordForm.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.resizableProperty().setValue(false);
        stage.setTitle("Forget Password!!");
        stage.setScene(scene);
        stage.show();

    }

}
