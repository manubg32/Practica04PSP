package db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Connection conn = null;
    private static String url;
    private static String user;
    private static String pass;
    private Database() {
        url = "jdbc:mysql://localhost:3306/psp04";
        user = "root"; //Declaramos el usuario
        pass = ""; //Declaramos la contraseña
    }
    public static Connection openConn() throws SQLException {
        if(conn == null) {
            new Database();
        }
        return conn = DriverManager.getConnection(url,user,pass);
    }
    public static void closeConn() throws SQLException{
        if(conn!=null) {
            conn.close();
        }
    }
    public static void beginCommit() throws SQLException{
        if(conn!=null) {
            conn.setAutoCommit(false);
        }
    }
    public static void commit() throws SQLException{
        if(conn!=null) {
            conn.commit();
        }
    }
    public static void rollbackCommit() throws SQLException{
        if(conn!=null) {
            conn.commit();
        }
    }
    public static ResultSet executeQuery(String sql) throws SQLException {
        return (conn == null) ? null : conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
    }
    public static int executeIDU(String sql) throws SQLException {
        return (conn == null) ? null : conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeUpdate(sql);
    }
    public static boolean executeCreate(String sql) throws SQLException{
        return (conn == null ) ? null : conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).execute(sql);
    }
    public static ResultSet executeSTQuery (String sql, Object...param) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int index = 1;
        for(Object o : param) {
            pstmt.setObject(index++, o);
        }
        return pstmt.executeQuery();
    }
    public static int executeSTIDU(String sql, Object...param) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        int index = 1 ;
        int filasAff=0;
        for(Object o : param) {
            pstmt.setObject(index++, o);
            filasAff++;
        }
        pstmt.executeUpdate();
        return filasAff;
    }

    public static DatabaseMetaData getMetaData() throws SQLException{
        return (conn == null) ? null : conn.getMetaData();
    }

    public static List<String> getTableNames() throws SQLException{
        List<String> lst = new ArrayList<>();
        try(ResultSet rs = getMetaData().getTables(null, null, null, new String[]{"TABLE"})){
            while(rs.next()) {
                lst.add(rs.getString("TABLE_NAME"));
            }
        }
        return lst;
    }
    public static String[] getFieldNames(String tabla) throws SQLException{
        String sql = "SELECT * FROM "+tabla;
        try(ResultSet rs = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql)){
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            String[] fields = new String[colCount];
            for(int i=1; i<=colCount; i++) {
                fields[i-1] = rsmd.getColumnName(i);
            }
            return fields;
        }
    }

    // Metodo para añadir imagen
    public static int setImage(String nombreImagen, FileInputStream fis, long length) throws SQLException {
        String sql = "INSERT INTO imagenes (nombre, imagen) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            pstmt.setString(1, nombreImagen);  // Establecer el nombre de la imagen
            pstmt.setBinaryStream(2, fis, length);  // Establecer el flujo de la imagen y su longitud

            return pstmt.executeUpdate();  // Ejecutar la actualización (insertar)
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Metodo para obtener imagen
    public static void getImage(int id, String outputPath) throws SQLException, IOException {
        String sql = "SELECT imagen FROM imagenes WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);  // Establecer el ID para recuperar la imagen

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Obtener el flujo de la imagen (BLOB)
                    InputStream inputStream = rs.getBinaryStream("imagen");

                    // Crear un archivo para guardar la imagen recuperada
                    try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        // Leer los bytes de la imagen y escribirlos en el archivo
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                        System.out.println("Imagen recuperada correctamente");
                    }
                } else {
                    System.out.println("No se encontró la imagen con el ID especificado.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


}
