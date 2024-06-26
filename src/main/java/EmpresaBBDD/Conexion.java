package EmpresaBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Conexion implements IFconexion{
    private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3309/Empresa?allowPublicKeyRetrieval=true&useSSL=false";
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
                if (!tablaBBDD(cn, "Dietas")){
                    stm = cn.createStatement();
                    stm.executeUpdate("CREATE TABLE Dietas (id INT(10) NOT NULL AUTO_INCREMENT, empleado VARCHAR(100), departamento VARCHAR(100), euros FLOAT, concepto VARCHAR(150), PRIMARY KEY (id))");
                    System.out.println("La tabla Dietas ha sido creada");
                }else {
                    stm = cn.createStatement();
                    System.out.println("La tabla ya existe");
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

    public void crearDieta() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ha escogido agregar una dieta, por favor, ingrese el nombre del empleado: ");
        String empleado = sc.nextLine();
        String dptmt = seleccionarDptmt();

        System.out.println("Ingrese la cantidad en euros (hasta dos decimales): ");
        float euros = sc.nextFloat();
        sc.nextLine();
        System.out.println("Introduzca el concepto: ");
        String concepto = sc.nextLine();

        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            cn = conectar();
            stm = cn.createStatement();
            String query = "INSERT INTO Dietas (empleado, departamento, euros, concepto) VALUES ('" + empleado + "', '" + dptmt + "', '" + euros + "', '" + concepto + "');";
            stm.executeUpdate(query);
            System.out.println("Dieta creada correctamente.");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private String seleccionarDptmt() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        boolean continuar = false;
        String dptmt;
        System.out.println("Seleccione un departamento: \n\t1. Informatica\n\t2. Ventas\n\t3. Recursos Humanos");
        while (continuar == false){
            try{
                opcion = sc.nextInt();
                if (opcion >= 1 && opcion <= 3){
                    continuar = true;
                }else {
                    System.out.println("Opcion no valida, por favor introduce solo un numero valido");
                }
            }catch(Exception e){
                System.out.println("Opcion no valida, por favor introduce solo un numero valido");
                sc.next();
            }
        }
        if (opcion == 1){
            dptmt = "Informatica";
        }else if (opcion == 2){
            dptmt = "Ventas";
        }else{
            dptmt = "Recursos Humanos";
        }
        return dptmt;
    }

    public String mostrarDietasInformatica() {
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        String lista = null;
        try {
            cn = conectar();
            stm = cn.createStatement();
            String query = "SELECT * FROM Dietas WHERE departamento LIKE 'Informatica' AND euros > 30;";
            rs = stm.executeQuery(query);

            ArrayList<Dieta> dietas = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("empleado");
                String dptmt = rs.getString("departamento");
                float euros = rs.getFloat("euros");
                String concepto = rs.getString("concepto");

                dietas.add(new Dieta(id, nombre, dptmt, euros, concepto));
            }
            lista = "Dietas del departamento de informática superiores a 30€: " + dietas;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return lista;
    }

    public String mostrarDietas() {
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        String lista = null;
        System.out.println("Ha escogido mostrar todas las dietas.");
        String departamento = seleccionarDptmt();
        try {
            cn = conectar();
            stm = cn.createStatement();
            String query = "SELECT * FROM Dietas WHERE departamento LIKE '" + departamento + "';";
            rs = stm.executeQuery(query);

            ArrayList<Dieta> dietas = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("empleado");
                String dptmt = rs.getString("departamento");
                float euros = rs.getFloat("euros");
                String concepto = rs.getString("concepto");

                dietas.add(new Dieta(id, nombre, dptmt, euros, concepto));
            }
            lista = "Dietas del departamento  " + dietas;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return lista;

    }

    public void aumentarDietas(){
        Connection cn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            cn = conectar();
            stm = cn.createStatement();
            String query = "UPDATE Dietas SET euros = euros*1.1 WHERE departamento LIKE 'Ventas';";
            stm.executeUpdate(query);
            System.out.println("Dietas actualizadas correctamente.");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
