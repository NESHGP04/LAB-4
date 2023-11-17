/* Laboratorio #4
 * Marinés García 23391
 * CLASS
 */
import java.util.*;
import java.io.*;

public class Reserva {

    //Declaración variables
    private List<Cliente> reservList;
    private String archivoCSV;
    private String usuario;
   
    //Constructor de la clase
    public Reserva(){
        reservList = new ArrayList<>();
    }

    //Carga arraylist desde CSV
    public void cargarDesdeCSV(String archivoCSV){ 
        reservList = cargarCSV(archivoCSV);
    }

    private List<Cliente> cargarCSV(String archivoCSV){ //Crea archivo CSV con la posición de cada campo
        List<Cliente> clienteArray = new ArrayList<>();
        String linea;

        try(BufferedReader reader = new BufferedReader(new FileReader(archivoCSV))){
            reader.readLine(); //Quitamos primera línea para encabezados

            while((linea = reader.readLine()) != null){
                String[] campos = linea.split(",");

                if(campos.length >= 10){
                    String usuario = campos[0];
                    String contra = campos[1];
                    String tipo = campos [2];

                    Cliente clientes = null;
                    clientes = new Cliente(usuario, contra, tipo, "", "", 0, "", 0, 0, 0);
                    
                    if(clientes != null){
                        clienteArray.add(clientes); //Si el array no está vacio, se agregan los inputs al CSV
                    }
                }
                
            }
            System.out.println("\nUsuario cargados exitosamente!");
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar el usuario ...");
        }
        return clienteArray;
    }

    public void agregarCliente(Cliente cliente){
        reservList.add(cliente);
        guardarCSV("Reserva.csv", reservList); //Guarda los cambios en el csv
    }

    //Guarda info en CSV
    private void guardarCSV(String archivoCSV, List<Cliente> clientes){
        try(PrintWriter writer = new PrintWriter(new FileWriter(archivoCSV))){
            writer.println("Usuario,Contraseña,Tipo"); //Escribe encabezados en CSV
            
            for(Cliente c : clientes){ //Escribe los datos de TODOS los clientes
                writer.print(c.getUsuario()+",");
                writer.println(c.getContraseña()+",");
                writer.print(c.getTipo()+",");
                writer.println();
        }
        System.out.println("Usuarios guardados exitosamente en el archivo CSV.");
        } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error al guardar usuarios en el archivo CSV.");
        }
    }

    //Clase para hacer lista de clientes de una categoría específica
    public List<Cliente> listarClientes(String categoria){
            List<Cliente> listaFiltrada = new ArrayList<>();
            for(Cliente cliente : reservList){ //Busca en array
                if(cliente.getTipo().equals(categoria)){
                    listaFiltrada.add(cliente); //Filtra listado
                }
            }
            return listaFiltrada; //Muestra listado
    }

    //Busca usuarios 
    public Cliente buscarUsuario(String estado) {
            for (Cliente c : reservList) { //Busca en array
                if (c.getUsuario().equals(estado)) {
                    return c; //Devuelve el usuario ingresado
                }
            }
            return null; // Devuelve null si no se encuentra el usuario
    }

    public boolean verificarCredenciales(String usuario, String contr) {
        // Buscar el usuario en el ArrayList
        for (Cliente usuarios : reservList) {
            if (usuarios.getUsuario().equals(usuario) && usuarios.getContraseña().equals(contr)) {
                return true; // Credenciales válidas
            }
        }
        return false; // Credenciales inválidas
    }
}
