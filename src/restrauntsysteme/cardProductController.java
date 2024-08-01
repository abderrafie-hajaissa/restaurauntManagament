package restrauntsysteme;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import jdk.nashorn.internal.codegen.CompilerConstants;

public class cardProductController implements Initializable {

    @FXML
    private AnchorPane card_Form;

    @FXML
    private Button prod_AddBtn;

    @FXML
    private Spinner<Integer> prod_Spinner;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    private productData prodData;
    private Image image;

    private String prodID;
    private String type;
    private String prod_date;
    private String prod_image;

    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private SpinnerValueFactory<Integer> spin;

    private Alert alert;

    public void setData(productData prodData) {
        this.prodData = prodData;
        prod_image = prodData.getImage();
        prod_date = String.valueOf(prodData.getDate());
        type = prodData.getType();
        prod_name.setText(prodData.getProductName());
        prodID = prodData.getProductId();
        prod_price.setText(String.valueOf(prodData.getPrice()) + " DH");
        String path = "File:" + prodData.getImage();
        image = new Image(path, 149, 129, false, true);
        prod_imageView.setImage(image);
        pr = prodData.getPrice();

    }

    public void Quatity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_Spinner.setValueFactory(spin);
    }

    private int qty;
    private double totalP;
    private double pr;

    public  void AddBtn() {
        mainFormController mForm = new mainFormController();
        mForm.customerID();
        
        

        qty = prod_Spinner.getValue();
        String check = "";
        String checkAvailable = "SELECT `Statue` FROM `product` WHERE `prod_id` = ?";
        connection = Database.ConnectionDB();

        try {
            int checkStck = 0;
            String checkStock = "SELECT `Stock` FROM `product` WHERE `prod_id` = ?";
            prepare = connection.prepareStatement(checkStock);
            prepare.setString(1, prodID);
            result = prepare.executeQuery();

            if (result.next()) {
                checkStck = result.getInt("Stock");
            }

            if (checkStck == 0) {
                String updateStock = "UPDATE `product` SET `Stock`=0, `Statue`='Unavailable' WHERE `prod_id`=?";
                prepare = connection.prepareStatement(updateStock);
                prepare.setString(1, prodID);
                prepare.executeUpdate();
            }

            prepare = connection.prepareStatement(checkAvailable);
            prepare.setString(1, prodID);
            result = prepare.executeQuery();

            if (result.next()) {
                check = result.getString("Statue");
            }

            if (!check.equals("Available") || qty == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Check Statue The Product Or Quantity!");
                alert.showAndWait();
            } else {
                if (checkStck < qty) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This product is out of stock.");
                    alert.showAndWait();
                } else {
                    prod_image = prod_image.replace("\\", "\\\\");

                    String insertData = "INSERT INTO `customer`(`customer_id`,`prod_id`, `prod_name`, `type`,`quantity`, `price`, `date`, `image`, `username`) VALUES (?,?,?,?,?,?,?,?,?)";
                    prepare = connection.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(data.cID));
                    prepare.setString(2, prodID);

                    prepare.setString(3, prod_name.getText());
                    prepare.setString(4, type);
                    prepare.setString(5, String.valueOf(qty));
                    totalP = qty * pr;
                    prepare.setString(6, String.valueOf(totalP));
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(7, String.valueOf(sqlDate));
                    prepare.setString(8, prod_image);
                    prepare.setString(9, data.username);

                    int rowsAffected = prepare.executeUpdate();

                    if (rowsAffected > 0) {
                        int upStock = checkStck - qty;

                        String updateStock = "UPDATE product SET Stock = ? WHERE prod_id = ?";
                        prepare = connection.prepareStatement(updateStock);
                        prepare.setInt(1, upStock);
                        prepare.setString(2, prodID);
                        prepare.executeUpdate();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Added!");
                        alert.showAndWait();
                        mForm.menuGetTotal();
                       
                        
                        JfxUtils.controller.order();
                        
                        
                        
                        
                    } else {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to add the product.");
                        alert.showAndWait();

                        mForm.menuGetTotal();
                        
                        

                }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Quatity();
    }
}
