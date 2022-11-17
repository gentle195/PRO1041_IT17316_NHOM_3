/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author baphuoc
 */
public class JDBC_Helper {
    static String user ="sa";
    static String pass  ="123";
    static String url = "jdbc:sqlserver://localhost\\LAPTOP-NOPN753G\\SQLEXPRESS:1433;databaseName=QUANLYCUAHANGGIAY";
    static{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static ResultSet Query(String sql,Object...args){
        Connection con = null;
        PreparedStatement psvm = null;
        ResultSet rs = null ;
        try {
            con = getConnection();
            psvm = con.prepareStatement(sql);
            for(int i =0; i<args.length;i++){
                psvm.setObject(i+1, args[i]);
            }
            return rs = psvm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            close(psvm, con,rs);
            return null;
        }
    }
    public static int Update(String sql,Object...args){
        Connection con = null;
        PreparedStatement psvm = null;
        try {
             con = getConnection();
            psvm = con.prepareStatement(sql);
            for(int i =0; i<args.length;i++){
                psvm.setObject(i+1, args[i]);
            }
            return psvm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            close(psvm, con);
            return 0;
        }
    }
    public static int Call(String sql,Object...args){
        Connection con = null;
        PreparedStatement psvm = null;
        try {
            con = getConnection();
            psvm = con.prepareCall(sql);
            for(int i =0; i<args.length;i++){
                psvm.setObject(i+1, args[i]);
            }
            return psvm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            close(psvm, con);
            return 0;
        }
    }
    public static void close(PreparedStatement psvm,Connection con){
        try {
            con.close();
            psvm.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    public static void close(PreparedStatement psvm,Connection con,ResultSet rs){
        try {
            rs.close();
            con.close();
            psvm.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(JDBC_Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        Connection con = getConnection();
        if(con!= null){
            System.out.println("ket noi thanh cong toi "+user);
        }else{
            System.out.println("ket noi ko thanh cong");
        }
    }
}
