package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database;

/**
 * Clase que gestiona la conexion con la BD en MySQL
 */
public class Conn {

	private static Connection conn = null;
    private static String url;
    private static String user;
    private static String pass;
    
    private Conn() {
        url = "jdbc:mysql://localhost:3306/psp04";  // declaramos el host
        user = "root"; //Declaramos el usuario
        pass = ""; //Declaramos la contraseña
        
     // Previamente he creado la base de datos "psp04" desde DBeaver ya que si no me
     		// decía que no existía
     		// Establecemos la conexion conn MySQL desde XAMPP y creamos la base de datos
     		// conn una conexión a MySQL desde DBeaver
        
     // En el paquete libs está el jdbc utilizado, debemos darle click derecho y Add
     		// as Library...
        
}
    // Metodo que crea y devuelve la conexion
    public static Connection open() throws SQLException {
        if(conn == null) {
            new Conn();
        }
        return conn = DriverManager.getConnection(url,user,pass);
    }

	
    public static void close() throws SQLException{
        if(conn!=null) {
            conn.close();
        }
    }
    
    public static int executeIDU(String sql) throws SQLException {
        return (conn == null) ? null : conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(sql);
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        return (conn == null) ? null : conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
    }
    

}
