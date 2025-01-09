package controller;

import view.PnlEntrar;

import javax.swing.*;
import java.sql.SQLException;

public class CtrlSalir {

    public CtrlSalir(){ }
    public static void logout(){
        PnlEntrar.user = null;
        PnlEntrar.idAlumn = null;
        try{
            Conn.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
