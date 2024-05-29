package EmpresaBBDD;

import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private int opcion = 0;
    private boolean continuar = false;

    //Dado que esta clase solo contiene un método, no se ha creado una interfaz que los organice

    public void abrirMenu() {
        Conexion con = new Conexion();
        while (opcion != 6) {
            System.out.println("Menu de Usuarios, por favor escoja una opción:");
            System.out.println("1. Añadir una dieta\n2. Mostrar las dietas de Informática de más de 30€\n3. Aumentar el valor de las dietas de Ventas en un 10%\n4. Salir");
            System.out.println("Opcion escogida: ");
            while (continuar == false){
                try{
                    opcion = sc.nextInt();
                    if (opcion >= 1 && opcion <= 4){
                        continuar = true;
                    }else {
                        System.out.println("Opcion no valida, por favor introduce solo un numero valido");
                    }
                }catch(Exception e){
                    System.out.println("Opcion no valida, por favor introduce solo un numero valido");
                    sc.next();
                }
            }
            continuar = false;//reseteamos el continuar para la siguiente vuelta
            switch (opcion) {
                case 1:
                    con.crearDieta();
                    break;
                case 2:
                    System.out.println(con.mostrarDietasInformatica());
                    break;
                case 3:
                    con.aumentarDietas();
                    break;
                case 4:
                    break;
            }
        }
    }
}
