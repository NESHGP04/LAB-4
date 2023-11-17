/* Laboratorio #4
 * Marinés García 23391
 * MAIN
 */

import java.util.*;
import java.io.*;

public class Kayak{

    public static void Menu1(){
        System.out.println("\nMENU");
        System.out.println("\n1.Registrarse");
        System.out.println("2.Ingresar");
        System.out.println("3.Salir");
        System.out.println("Ingrese una de las opciones...");
    }

    public static void Menu2(){
        System.out.println("\nQue desea realizar?");
        System.out.println("\n1.Reservar viaje");
        System.out.println("2.Confirmar viaje");
        System.out.println("3.Salir");
        System.out.println("Ingrese una de las opciones...");
    }

    public static void main(String[] args) {

        //Declaración de variables
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        String allAroundI = null;
        Reserva reserv = new Reserva();
        Confirmacion confi = new Confirmacion();
        Cliente cliente = null;
        SistemaLogin sistemaLogin = new SistemaLogin();

        System.out.println("\n<<<Kayak>>>");

        while(!salir){
            Menu1();
            int opcionMenu1 = sc.nextInt();
            sc.nextLine();

            switch (opcionMenu1){
                case 1: //Registra usuarios
                    System.out.println("\n=== Registro ===");
                    
                    System.out.println("\nIngrese su nombre de usuario: ");
                    String userName = sc.nextLine();
                    System.out.println("\nIngrese su contraseña: ");
                    String password = sc.nextLine();
                    System.out.println("\nIngrese que tipo de plan quiere: \n1.Básico \n2.Premium");
                    int tipo = sc.nextInt();
                    
                    if(tipo==1){
                        cliente = new Cliente(userName, password, "Básico", "", "", 0, "", 0, 0, 0);
                        cliente.getBase();
                    } else if(tipo == 2){
                        cliente = new Cliente(userName, password, "Premium", "", "", 0,"", 0, 0, 0);
                    } else{
                        System.out.println("\nIngrese un número correcto...");
                    }

                    if (cliente != null) { //Si hay elementos a arraylist, se agrega al CSV
                        reserv.agregarCliente(cliente);
                        System.out.println("\nNuevo usuario agregado exitosamente!");
                    }
                    break;

                case 2: //Ingresa usuarios
                    System.out.println("\n=== Ingreso ===");
                    System.out.println("\nIngrese su nombre de usuario: ");
                    String user = sc.nextLine();
                    System.out.println("\nIngrese su contraseña de usuario: ");
                    String contra = sc.nextLine();

                    //reserv.verificarCredenciales(user,contra);
                    if(reserv.verificarCredenciales(user,contra) == true){
                        System.out.println("¡Inicio de sesión exitoso!");

                        Menu2();//Reserva o confirma vuelos
                        int opcionMenu2 = sc.nextInt();
                        sc.nextLine();

                        switch (opcionMenu2){
                            case 1: //Reservación de vuelos
                            System.out.print("\n== Reservación ==");
                            System.out.println("\nIngrese la fecha en la que realizará el viaje (dd/mm/yy): ");
                            String fecha = sc.nextLine();
                            System.out.println("\nIngrese si su viaje será de ida y vuelta (1) o solo ida (2): ");
                            int allAround = sc.nextInt();
                            sc.nextLine();
                            System.out.println("\nIngrese la cantidad de boletos que desea: ");
                            int boletos = sc.nextInt();
                            sc.nextLine();
                            System.out.println("\nIngrese la aereolínea en la que realizará el viaje: ");
                            String aereolinea = sc.nextLine();
                            System.out.println("\nIngrese su número de tarjeta: ");
                            int tarjeta = sc.nextInt();
                            sc.nextLine();

                            if(allAround == 1){ //Le da un valor a all around
                                allAroundI = "Ida y vuelta";
                            } else if(allAround == 2){
                                allAroundI = "Ida";
                            } else{
                                System.out.println("Error, por favor ingrese una opción correcta.");
                            }
                            
                            System.out.println("\nIngrese su plan a elegir: \n1.Básico \n2.Premium ");
                            int plan = sc.nextInt();
                            sc.nextLine();

                            if(plan == 1){ //Básico
                                cliente = new Cliente(user, "", "", fecha, allAroundI, boletos, aereolinea, tarjeta, 0, 0);
                                cliente.getBase();
                            } else if(plan == 2){ //Premium
                                System.out.println("\nIngrese los números de asientos que desea: ");
                                int asientos = sc.nextInt();
                                System.out.println("\nIngrese la cantidad de maletas que llevará: ");
                                int maletas = sc.nextInt();
                                cliente = new Cliente(user, "", "Premium", fecha, allAroundI, boletos, aereolinea, tarjeta, asientos, maletas);
                            } else{
                                System.out.println("Error, por favor ingrese una opción correcta.");
                            }

                            if(cliente != null){
                                reserv.agregarCliente(cliente);
                                System.out.println("\nNueva reserva agregada exitosamente!");
                            } else{
                                System.out.println("\nNo se ha podido guardar la reservación");
                            }

                            Cliente reservaEncontrada = reserv.buscarUsuario(user); //Busca en el sistema la reserva
                            if(reservaEncontrada != null){
                                System.out.println("\nRESERVA: " + reservaEncontrada.toStringR()); //Si lo que se busca no está vacío, se 'printea'
                            }else{
                                    System.out.println("No se encontraron resultados con ese criterio.");
                            }
                            break;

                        case 2: //Confirmación de vuelos
                            System.out.print("\n== Confirmación ==");
                            
                            Cliente confirmacionEncontrada = confi.buscarUsuario(user); //Busca en el sistema la confirmación del usuario
                            if(confirmacionEncontrada != null){
                                System.out.println("\nCONFIRMACIÓN: \n" + confirmacionEncontrada.toStringC()); //Si lo que se busca no está vacío, se 'printea'
                            }else{
                                System.out.println("\nNo se encontraron resultados con ese criterio.");
                            }
                            break;

                        default:
                            System.out.println("\nIngrese una opción válida...");
                            break;
                        }
                        
                    }else{
                        System.out.println("Error de autenticación. Usuario o contraseña incorrectos.");
                    }
                    break;

                case 3: //Sale del programa
                    System.out.println("\nGracias por utilizar el programa!");
                    salir = true;
                    break; 

                default:
                    System.out.println("\nIngrese una opción válida...");
                    break;
                
            }
        }
        sc.close();
    }
}
