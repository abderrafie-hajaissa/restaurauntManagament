/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restrauntsysteme;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Database {
    
    public static Connection ConnectionDB(){
        Connection connection=null;
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost/systemrestraunt","root","");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connection Is Faible", "Erorr Message", JOptionPane.ERROR_MESSAGE);
        }
    return connection ;
    }
    
}
