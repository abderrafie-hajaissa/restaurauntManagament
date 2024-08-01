/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restrauntsysteme;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JfxUtils {
    
    public static mainFormController controller;

     public static Stage loadFxmlToStage(String fxml) {
        Stage stage=new Stage();
        try {

            Parent root = FXMLLoader.load(JfxUtils.class.getResource(fxml));
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene sc = new Scene(root);
            sc.setFill(Color.TRANSPARENT);
            stage.setScene(sc);
            return stage;
        } catch (IOException ex) {
            throw new IllegalStateException("cannot load FXML screen", ex);
        }
    }
    public static void changeScene(Stage stg, String newFXML) throws IOException {
        Parent root = FXMLLoader.load(JfxUtils.class.getResource(newFXML));
        stg.getScene().setRoot(root);
    
}
}
