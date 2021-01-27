package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String database = "sgcfei_proyecto";
    private static String hostname = "localhost";
    private static String port = "3306";
    private static String url = "jdbc:mysql://"+hostname+":"+port+"/"+database+"?allowPublicKeyRetrieval=true&useSSL=false";
    private static String username = "root";
    private static String password = "Itfollows2";
    private Connection conexion;

    public Connection getConnection() {
        conexion = null;
        try{
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, username, password);
            if(conexion == null){
                System.out.println("Error de conexion");
            }else{
                System.out.println("Conexion Establecida");
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return conexion;
    }

    public void cerrarConexion() {
        if(conexion!=null) {
            try {
                if(!conexion.isClosed()){
                    conexion.close();
                }
            } catch(SQLException ex) {
                Logger.getLogger(accesoDatos.Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}