/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restrauntsysteme;

import java.util.Date;
import java.awt.Insets;
import java.io.File;
import java.net.URL;
import java.sql.Connection;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.sql.Statement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.HashMap;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

public class mainFormController implements Initializable {

    @FXML
    private Button Customers_Btn;

    @FXML
    private Button Dashbord_Btn;

    @FXML
    private Button Exite_Btn;
//******************************************************************************************/

    @FXML
    private AreaChart<?, ?> dahbord_ChartTotal;

    @FXML
    private Label dahbord_ProductSold;

    @FXML
    private Label dahbord_TodayE;

    @FXML
    private Label dahbord_TotalP;

    @FXML
    private AnchorPane dashborrd_Form;

    /**
     * ************************************************************************************
     */
    @FXML
    private Button Inventory_AddBtn;

    @FXML
    private Button Inventory_Btn;

    @FXML
    private Button Inventory_ClearBtn;

    @FXML
    private Button Inventory_DeleteBtn;

    @FXML
    private TableView<productData> Inventory_TableView;

    @FXML
    private Button Inventory_UpdateBtn;

    @FXML
    private Button Menu_Btn;

    @FXML
    private Label Username;

    @FXML
    private AnchorPane invontory_Form;

    @FXML
    private Button invontory_ImportBtn;

    @FXML
    private TableColumn<productData, String> invontory_col_Date;

    @FXML
    private TableColumn<productData, String> invontory_col_Price;

    @FXML
    private TableColumn<productData, String> invontory_col_ProductID;

    @FXML
    private TableColumn<productData, String> invontory_col_ProductName;

    @FXML
    private TableColumn<productData, String> invontory_col_Statue;

    @FXML
    private TableColumn<productData, String> invontory_col_Stock;

    @FXML
    private TableColumn<productData, String> invontory_col_Type;

    @FXML
    private ImageView invontory_imageView;

    @FXML
    private TextField inventory_Price;

    @FXML
    private TextField inventory_ProductID;

    @FXML
    private TextField inventory_ProductName;

    @FXML
    private TextField inventory_Stock;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private ComboBox<?> inventory_ListType;

    @FXML
    private ComboBox<?> inventory_Statue;

    @FXML
    private TextField invotory_search;

    /**
     * ******************************************************************
     */
    @FXML
    private AnchorPane Order_Form;

    @FXML
    public Button Order_Btn;

    @FXML
    private TextField Menu_Amount;

    @FXML
    private GridPane menu_GridPane;

    @FXML
    private Button Menu_PayBtn;

    @FXML
    private Button Menu_ReciepteBtn;

    @FXML
    private Button Menu_RemoveBtn;

    @FXML
    private ScrollPane Menu_ScrollPane;

    @FXML
    private Label Menu_Total;

    @FXML
    private Label Menu_change;

    @FXML
    private TableColumn<productData, String> Menu_col_ParoductName;

    @FXML
    private TableColumn<productData, String> Menu_col_price;

    @FXML
    private TableColumn<productData, String> Menu_col_quantity;

    @FXML
    private TableView<productData> Menu_tableView;

    /**
     * ******************************************************************
     */
    private Alert alert;
    private Image image;

    private Connection connection;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private ObservableList<productData> cardListData = FXCollections.observableArrayList();

    public  void displayBtn(ActionEvent event) {

        if (event.getSource() == Dashbord_Btn) {

            dashborrd_Form.setVisible(true);
            invontory_Form.setVisible(false);
            Order_Form.setVisible(false);

            dashboardDisplayTI();
            dashboardTotalI();
            dashboardProductSold();
            dashboardTotalChart();
        } else if (event.getSource() == Inventory_Btn) {

            dashborrd_Form.setVisible(false);
            invontory_Form.setVisible(true);
            Order_Form.setVisible(false);
            invontroySreache();

        } else if (event.getSource() == Order_Btn) {
                
                
                
            dashborrd_Form.setVisible(false);
            invontory_Form.setVisible(false);
            Order_Form.setVisible(true);
         
            JfxUtils.controller=this;
            
            menuDisplayCard();
            menuShowOrderData();
            menuGetOrder();
            menuDisplayTotal();
            menuSelectOrder();
           

        }

    }
    
   public void order(){
   
       dashborrd_Form.setVisible(false);
            invontory_Form.setVisible(false);
            Order_Form.setVisible(true);
         
            JfxUtils.controller=this;
            
            menuDisplayCard();
            menuShowOrderData();
            menuGetOrder();
            menuDisplayTotal();
            menuSelectOrder();
   }

    ////////////////////////////// /*For Display Username In MainForm*//////////////////////////////////////////////////////////
    public void DisplayUsername() {
        String user = data.username;

        Username.setText(user);

    }

    /**
     * ***********************************************************************************************************
     */
    public void extiSystem() {

        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message!");
            alert.setContentText("Are you Sure to be Exit this system?!");
            alert.setHeaderText(null);
            Optional<ButtonType> optional = alert.showAndWait();

            if (optional.get().equals(ButtonType.OK)) {

                //To hide mainForm
                Exite_Btn.getScene().getWindow().hide();

                //To visible LoginForm
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                stage.resizableProperty().setValue(false);
                
                Scene scene = new Scene(root);
                stage.setTitle("Restraunt Mangement System Admin");

                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
        }

    }
    /**
     * ******************************************************************************************************
     */

    private String[] ListType = {"Food", "Drink"};

    public void listQuestionType() {

        List<String> ListQ = new ArrayList();

        for (String data : ListType) {
            ListQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(ListQ);
        inventory_ListType.setItems(listData);
    }

    /**
     * ****************************************************************************************************
     */
    private String[] ListQStatue = {"Available", "Unavailable"};

    public void ListquestionStatue() {

        List<String> ListStatue = new ArrayList();

        for (String DataStatue : ListQStatue) {
            ListStatue.add(DataStatue);
        }
        ObservableList listData = FXCollections.observableArrayList(ListStatue);
        inventory_Statue.setItems(listData);
    }

    /**
     * ********************************************************************************************************
     *      *
     * ********************************************************************************************************
     * ********************************************************************************************************
     * @return
     */
    public ObservableList<productData> invontoryDataList() {

        ObservableList<productData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product  ";

        connection = Database.ConnectionDB();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            productData proData;

            while (result.next()) {

                proData = new productData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("Stock"),
                        result.getDouble("Price"),
                        result.getString("Statue"),
                        result.getString("Image"),
                        result.getDate("date"));

                listData.add(proData);
            }
        } catch (Exception e) {
        }
        return listData;
    }
    /**
     * **************************************** For Show data
     * *************************************
     */

    private ObservableList<productData> inventoryListeData;

    public void inventoryShowData() {

        inventoryListeData = invontoryDataList();

        connection = Database.ConnectionDB();

        invontory_col_ProductID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        invontory_col_ProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        invontory_col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        invontory_col_Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        invontory_col_Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        invontory_col_Statue.setCellValueFactory(new PropertyValueFactory<>("statue"));
        invontory_col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));

        Inventory_TableView.setItems(inventoryListeData);
    }

    /**
     * ******************************************************************************************************
     */
    /**
     * ******************************************************************************************************
     */
    /**
     * ******************************************************************************************************
     */
    /**
     * **********************************Button ADD In
     * inventory***************************************
     */
    public void inventoryAdd() {

        if (inventory_ProductID.getText().isEmpty() || inventory_ProductName.getText().isEmpty()
                || inventory_ListType.getSelectionModel().getSelectedItem() == null
                || inventory_Stock.getText().isEmpty() || inventory_Price.getText().isEmpty()
                || inventory_Statue.getSelectionModel().getSelectedItem() == null
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Information Is Empity!");
            alert.setHeaderText(null);
            alert.showAndWait();

        } else {

            String requtte = "SELECT `prod_id` FROM product WHERE prod_id='" + inventory_ProductID.getText() + "'";

            connection = Database.ConnectionDB();

            try {

                statement = connection.createStatement();
                result = statement.executeQuery(requtte);
                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText("This Product id Is ready Creat New Product id!!");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } else {

                    String sql = "INSERT INTO `product`"
                            + "(`prod_id`, `prod_name`, `type`, `Stock`, `Price`, `Statue`, `Image`)"
                            + " VALUES (?,?,?,?,?,?,?)";

                    prepare = connection.prepareStatement(sql);
                    prepare.setString(1, inventory_ProductID.getText());
                    prepare.setString(2, inventory_ProductName.getText());
                    prepare.setString(3, (String) inventory_ListType.getSelectionModel().getSelectedItem());
                    prepare.setString(4, inventory_Stock.getText());
                    prepare.setString(5, inventory_Price.getText());
                    prepare.setString(6, (String) inventory_Statue.getSelectionModel().getSelectedItem());

                    String path = data.path;
                    path = path.replace("\\", "\\\\");
                    prepare.setString(7, path);

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setContentText("This Product Is Added Sussufuly!!");
                    alert.setHeaderText(null);
                    alert.showAndWait();

                    inventoryShowData();
                    InventoryClearBtn();

                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * *****************************************************************************************************************
     */
    /**
     * *****************************************************************************************************************
     */
    /**
     * *****************************************************************************************************************
     */
    /**
     * *****************************************************************************************************************
     */
    /**
     * *****************************************Button Clear
     * *************************************************
     */
    public void InventoryClearBtn() {

        inventory_ProductID.setText("");
        inventory_ProductName.setText("");
        inventory_Stock.setText("");
        inventory_Price.setText("");
        inventory_ListType.getSelectionModel().clearSelection();
        inventory_Statue.getSelectionModel().clearSelection();
        data.path = "";
        invontory_imageView.setImage(null);
        data.id = 0;
    }

    /**
     * *********************************Import
     * Image*************************************
     */
    public void inventorybtnImport() {

        FileChooser openFile = new FileChooser();

        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if (file != null) {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 144, 127, false, true);
            invontory_imageView.setImage(image);
        }

    }

    /**
     * ********************************************************************************************
     */
    /**
     * ********************************************************************************************
     */
    /**
     * ********************************************************************************************
     */
    /**
     * ********************************************************************************************
     */
    /*For Selection Data in table view*/
    public void ClickData() {

        productData clickProduct = Inventory_TableView.getSelectionModel().getSelectedItem();

        int index = Inventory_TableView.getSelectionModel().getSelectedIndex();

        if ((index - 1) < -1) {
            return;
        }

        data.path = clickProduct.getImage();

        inventory_ProductID.setText(String.valueOf(clickProduct.getId()));
        inventory_ProductName.setText(String.valueOf(clickProduct.getProductName()));
        inventory_Stock.setText(String.valueOf(clickProduct.getStock()));
        inventory_Price.setText(String.valueOf(clickProduct.getPrice()));

        String path = "File:" + clickProduct.getImage();

        data.id = clickProduct.getId();
        image = new Image(path, 144, 127, false, true);

        invontory_imageView.setImage(image);
    }

    /**
     * **************************************************************************************************
     */
    /**
     * ************************For Update information
     * Product*****************************
     */
    public void inventoryUpdateBtn() {

        if (inventory_ProductID.getText().isEmpty() || inventory_ProductName.getText().isEmpty()
                || inventory_ListType.getSelectionModel().getSelectedItem() == null
                || inventory_Stock.getText().isEmpty() || inventory_Price.getText().isEmpty()
                || inventory_Statue.getSelectionModel().getSelectedItem() == null
                || data.path == null || data.id == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Information Is Empity!");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {

            String path = data.path;
            path = path.replace("\\", "\\\\");

            String Update = "UPDATE product SET prod_id='" + inventory_ProductID.getText() + "',"
                    + "prod_name='" + inventory_ProductName.getText() + "',"
                    + "type='" + inventory_ListType.getSelectionModel().getSelectedItem() + "',"
                    + "Stock='" + inventory_Stock.getText() + "',"
                    + "Price='" + inventory_Price.getText() + "',"
                    + "Statue='" + inventory_Statue.getSelectionModel().getSelectedItem() + "',"
                    + "Image='" + path + "' WHERE id='" + data.id + "' ";

            connection = Database.ConnectionDB();
            try {

                prepare = connection.prepareStatement(Update);

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setContentText("Your Informmtion Product is Update!!");
                alert.setHeaderText(null);
                alert.showAndWait();

                inventoryShowData();
                InventoryClearBtn();

            } catch (Exception e) {
            }
        }
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
    public void inventoryDeleteBtn() {

        if (inventory_ProductID.getText().isEmpty() || inventory_ProductName.getText().isEmpty()
                || inventory_Stock.getText().isEmpty() || inventory_Price.getText().isEmpty()
                || data.id == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Information Is Empity!");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {

            String delete = "DELETE FROM `product` WHERE id='" + data.id + "'";

            connection = Database.ConnectionDB();

            try {

                prepare = connection.prepareStatement(delete);

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message!!");
                alert.setContentText("This product Delete Is succufuly");
                alert.setHeaderText(null);
                alert.showAndWait();

                inventoryShowData();
                InventoryClearBtn();

            } catch (Exception e) {
            }

        }

    }

    public void invontroySreache() {

        FilteredList<productData> filter = new FilteredList<>(inventoryListeData, e -> true);

        invotory_search.textProperty().addListener((observabl, newValue, oldValue) -> {

            filter.setPredicate(predicateCategories -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateCategories.getProductId().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateCategories.getStatue().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<productData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(Inventory_TableView.comparatorProperty());
        Inventory_TableView.setItems(sortList);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////MENU ORDER CODAGE ////////////////////////////////////////////////
    public ObservableList<productData> menuGetData() {

        String sql = "SELECT * FROM product";

        ObservableList<productData> listData = FXCollections.observableArrayList();
        connection = Database.ConnectionDB();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            productData prod;

            while (result.next()) {
                prod = new productData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("Stock"),
                        result.getDouble("Price"),
                        result.getString("Image"),
                        result.getDate("date"));

                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    /**
     * ********************************************************************************************************************
     */
    public void menuDisplayCard() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_GridPane.getChildren().clear();
        menu_GridPane.getRowConstraints().clear();
        menu_GridPane.getColumnConstraints().clear();

        // إضافة البطاقات إلى الـ GridPane
        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("CardProduct.fxml"));
                AnchorPane pane = load.load();
                cardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                // التحقق من عدد البطاقات في الصف الحالي
                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                // إضافة البطاقة إلى الـ GridPane في الموضع المحدد
                menu_GridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new javafx.geometry.Insets(10));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public ObservableList<productData> menuGetOrder() {
        customerID();
        ObservableList<productData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer WHERE customer_id = " + CID;

        connection = Database.ConnectionDB();

        try {

            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            productData prod;

            while (result.next()) {
                prod = new productData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));
                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<productData> menuOrderListData;

    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();

        Menu_col_ParoductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        Menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        Menu_tableView.setItems(menuOrderListData);
    }

    private int CID;

    public void customerID() {

        String sql = "SELECT MAX(customer_id) FROM customer";
        connection = Database.ConnectionDB();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                CID = result.getInt("MAX(customer_id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM recipte";
            prepare = connection.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }

            if (CID == 0) {
                CID += 1;
            } else if (CID == checkID) {
                CID += 1;
            }

            data.cID = CID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////
    private double totalP;

    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(price) FROM customer WHERE customer_id = " + CID;

        connection = Database.ConnectionDB();

        try {

            prepare = connection.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(price)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public void menuDisplayTotal() {
        menuGetTotal();
        Menu_Total.setText(totalP + "DH");
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    private double amount;
    private double change;

    public void DisplayAmount() {

        menuGetTotal();

        if (Menu_Amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message!!");
            alert.setContentText("Please check Amount Or Total!");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {

            amount = Double.parseDouble(Menu_Amount.getText());
            change = 0;

            if (amount < totalP) {
                Menu_Amount.setText("");
            } else {

                change = (amount - totalP);
                Menu_change.setText(change + "DH");

            }

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void menuPayBtn() {

        if (totalP == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Adedd The Frist Order For Pay");
            alert.showAndWait();
        } else {
            menuGetTotal();
            String insertPay = "INSERT INTO recipte (customer_id, total, date, username) "
                    + "VALUES(?,?,?,?)";

            connection = Database.ConnectionDB();
            try {

                if (amount == 0) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Messaged");
                    alert.setHeaderText(null);
                    alert.setContentText("Check Amount !!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure For Payment?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        customerID();
                        menuGetTotal();
                        prepare = connection.prepareStatement(insertPay);
                        prepare.setString(1, String.valueOf(CID));
                        prepare.setString(2, String.valueOf(totalP));

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        prepare.setString(3, String.valueOf(sqlDate));
                        prepare.setString(4, data.username);

                        prepare.executeUpdate();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Succefuly Payment!!");
                        alert.showAndWait();
                        menuShowOrderData();

                        // تنظيف بيانات `TableView`
                        menuOrderListData.clear();
                        Menu_tableView.setItems(menuOrderListData);
                        // إعادة تعيين المجموع                       

                    } else {
                        alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("cancel Payment!.");
                        alert.showAndWait();
                    }
                }

            } catch (Exception e) {
                
            }
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        Menu_Total.setText("0.0 DH");
        Menu_Amount.setText("");
        Menu_change.setText("0.0 DH");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int getid;

    public void menuSelectOrder() {
        productData prod = Menu_tableView.getSelectionModel().getSelectedItem();
        int num = Menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        getid = prod.getId();

    }

    /**
     * **************************************************************************************************
     */

    public void menuRemoveBtn() {

        if (getid == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please Select Product For To remove !!");
            alert.showAndWait();
        } else {
            String deleteData = "DELETE FROM customer WHERE id = " + getid;
            connection = Database.ConnectionDB();
            try {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Remove this order?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connection.prepareStatement(deleteData);
                    prepare.executeUpdate();
                }

                menuShowOrderData();
            } catch (Exception e) {

            }

        }
    }

    /**
     * **************************************************************************************************
     */

    public void MenueClear() {
        menuRestart();
   
    }

    /**
     * **************************************************************************************************
     */
    public void menuReceiptBtn() {
        if (totalP == 0 || Menu_Amount.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please order first");
            alert.showAndWait();
            
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("getRecipte", (CID - 1));

            try {
                String reportPath = "src/restrauntsysteme/report.jrxml";
                JasperDesign jDesign = JRXmlLoader.load(reportPath);
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, map, connection);
                JasperViewer.viewReport(jPrint, false);

                menuRestart();
            } catch (Exception e) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while generating the receipt: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void dashboardDisplayTI() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(total) FROM recipte WHERE date = '"
                + sqlDate + "'";

        connection = Database.ConnectionDB();

        try {
            double ti = 0;
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getDouble("SUM(total)");
            }

            dahbord_TodayE.setText(ti + "");

        } catch (Exception e) {

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public void dashboardTotalI() {
        String sql = "SELECT SUM(total) FROM recipte";

        connection = Database.ConnectionDB();

        try {
            float ti = 0;
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getFloat("SUM(total)");
            }
            dahbord_TotalP.setText(ti + "");

        } catch (Exception e) {

        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void dashboardProductSold() {

        String sql = "SELECT COUNT(quantity) FROM customer";

        connection = Database.ConnectionDB();

        try {
            int q = 0;
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                q = result.getInt("COUNT(quantity)");
            }
            dahbord_ProductSold.setText(String.valueOf(q));

        } catch (Exception e) {

        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void dashboardTotalChart() {
        dahbord_ChartTotal.getData().clear();
        
        String sql = "SELECT date, SUM(total) FROM recipte GROUP BY date ORDER BY TIMESTAMP(date)";
        connection = Database.ConnectionDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getFloat(2)));
            }
            
           dahbord_ChartTotal.getData().add(chart);
            
        } catch (Exception e) {
          
        }
    }
    
   
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL location, ResourceBundle resources
    ) {

        /*1*/ DisplayUsername();

        /*2*/ listQuestionType();

        /*3*/ ListquestionStatue();

        /*4*/ inventoryShowData();

        /*5*/ menuDisplayCard();
        menuShowOrderData();
        menuGetOrder();
        menuDisplayTotal();

        /*6*/ dashboardDisplayTI();
        dashboardTotalI();
        dashboardProductSold();
        dashboardTotalChart();
    }

}
