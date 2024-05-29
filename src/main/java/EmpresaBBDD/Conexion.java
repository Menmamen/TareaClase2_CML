package EmpresaBBDD;

import java.sql.*;

public class Conexion {
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3308/databaseClase?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";

    public Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión OK");
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }
        return conexion;
    }

    public void iniciarBBDD() {
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            cn = conectar();
            if (cn != null) {
                if (!tablaBBDD(cn, "Usuarios")){
                    stm = cn.createStatement();
                    stm.executeUpdate("CREATE TABLE Usuarios (DNI VARCHAR(12) NOT NULL, nombre VARCHAR(45), pais VARCHAR(45), PRIMARY KEY (DNI))");
                    System.out.println("La tabla Usuarios ha sido creada");
                }else {
                    stm = cn.createStatement();
                    System.out.println("La tabla Usuarios ya existe");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public boolean tablaBBDD(Connection cn, String tabla) throws SQLException {
        boolean resultado = false;
        DatabaseMetaData databaseMetaData = cn.getMetaData();
        try {
            ResultSet rs = databaseMetaData.getTables(null, null, tabla, null);
            if (rs.next()) {
                resultado = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
}
