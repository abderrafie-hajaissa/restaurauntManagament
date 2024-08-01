/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restrauntsysteme;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

public class ForgetPassForm implements Initializable {

    @FXML
    private TextField fo_Pass_Answer;

    @FXML
    private AnchorPane fo_Pass_ForgetPassForm;

    @FXML
    private Button fo_Pass_Proccedbtn;

    @FXML
    private ComboBox<?> fo_Pass_Question;

    @FXML
    private TextField fo_Pass_Username;

    /**
     * **********************************************************************************************
     */
    @FXML
    private AnchorPane fo_Confi_PassForm;

    @FXML
    private PasswordField fo_confi_NewPassword;

    @FXML
    private Button Confirm_PassBtn;

    @FXML
    private Button btn_exit;

    private Alert alert;
    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;

    public void SwitchFormPass(ActionEvent event) {

        if (fo_Pass_Username.getText().isEmpty() || fo_Pass_Question.getSelectionModel().getSelectedItem() == null
                || fo_Pass_Answer.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Information is Empty!!");
            alert.showAndWait();

        } else {

            String username = fo_Pass_Username.getText();
            String answer = fo_Pass_Answer.getText();

            String requtte = "SELECT * FROM `login` WHERE `username`=? AND `question`=? AND `answer`=?";

            try {

                prepare = Database.ConnectionDB().prepareStatement(requtte);

                prepare.setString(1, username);
                prepare.setString(2, (String) fo_Pass_Question.getSelectionModel().getSelectedItem());
                prepare.setString(3, answer);

                result = prepare.executeQuery();

                if (result.next()) {

                    fo_Pass_ForgetPassForm.setVisible(false);
                    fo_Confi_PassForm.setVisible(true);

                } else {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Information is Not Correct!!");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Connection Faible!", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * ***************************************************************************************************
     */
    /**
     * ***************************************************************************************************
     */
    /**
     * ***************************************************************************************************
     */
    public String[] listquestion = {"What Is Favorit Food ?", "What Is Favorit Drink ?", "What Is Favorit Sose ?"};

    public void listquestion() {

        List<String> ListQ = new ArrayList<>();

        for (String data : listquestion) {
            ListQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(ListQ);
        fo_Pass_Question.setItems(listData);
    }

    /**
     * ************************************************************************************************************
     */
    /**
     * ************************************************************************************************************
     */
    /**
     * ************************************************************************************************************
     */
    /**
     * ************************************************************************************************************
     */
    public void changePassword() {
        if (fo_confi_NewPassword.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please Complet The all Information!");
            alert.showAndWait();
        } else {

            String sql = "UPDATE `login` SET `password`=? WHERE username ='" + fo_Pass_Username.getText() + "'";
            connection = Database.ConnectionDB();

            try {

                prepare = connection.prepareStatement(sql);
                prepare.setString(1, fo_confi_NewPassword.getText());

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("information  Message");
                alert.setHeaderText(null);
                alert.setContentText("Password is update sussufuly");
                alert.showAndWait();

            } catch (Exception e) {
            }
        }

    }
///////////////////////////////////////////////////////////////////////////////////////

    public void exitSystem() {
        System.exit(0);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        listquestion();

    }
}
