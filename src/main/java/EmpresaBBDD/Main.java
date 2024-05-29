package EmpresaBBDD;

public class Main {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.iniciarBBDD();

        Menu menu= new Menu();
        menu.abrirMenu();
    }
}
