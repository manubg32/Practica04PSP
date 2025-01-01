package db;

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
        return (conn == null) ? null : conn.createStatement().executeQuery(sql);
    }
    public static int executeIDU(String sql) throws SQLException {
        return (conn == null) ? null : conn.createStatement().executeUpdate(sql);
    }
    public static boolean executeCreate(String sql) throws SQLException{
        return (conn == null ) ? null : conn.createStatement().execute(sql);
    }
    public static ResultSet executeSTQuery (String sql, Object...param) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(sql);
        int index = 1;
        for(Object o : param) {
            pstmt.setObject(index++, o);
        }
        return pstmt.executeQuery();
    }
    public static int executeSTIDU(String sql, Object...param) throws SQLException{
        PreparedStatement pstmt = conn.prepareStatement(sql);
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
        try(ResultSet rs = conn.createStatement().executeQuery(sql)){
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            String[] fields = new String[colCount];
            for(int i=1; i<=colCount; i++) {
                fields[i-1] = rsmd.getColumnName(i);
            }
            return fields;
        }
    }
}
