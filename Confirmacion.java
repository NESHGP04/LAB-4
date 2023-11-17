/* Laboratorio #4
 * Marinés García 23391
 * CLASS
 */
import java.util.*;
import java.io.*;

public class Confirmacion {

    //Declaración variables
    private List<Cliente> confiList;
    private String archivoCSV;
    private String usuario;
   
    //Constructor de la clase
    public Confirmacion(){
        confiList = new ArrayList<>();
    }

    //Carga arraylist desde CSV
    public void cargarDesdeCSV(String archivoCSV){ 
        confiList = cargarCSV(archivoCSV);
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
                    String fecha = campos[1];
                    String allAround = campos [2];
                    int boletos = Integer.parseInt(campos[3]);
                    String aereo = campos [4];
                    int tarjetas = Integer.parseInt(campos[5]);
                    int asientos = Integer.parseInt(campos[6]);
                    int maletas = Integer.parseInt(campos[7]);

                    Cliente clientes = null;
                    clientes = new Cliente(usuario, "", "", fecha, allAround, boletos, aereo, tarjetas, asientos, maletas);
                    
                    if(clientes != null){
                        clienteArray.add(clientes); //Si el array no está vacio, se agregan los inputs al CSV
                    }
                }
                
            }
            System.out.println("Usuarios cargados exitosamente!");
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar el usuario...");
        }
        return clienteArray;
    }

    public void agregarCliente(Cliente cliente){
        confiList.add(cliente);
        guardarCSV("Confirmación.csv", confiList); //Guarda los cambios en el csv
    }

    //Guarda info en CSV
    private void guardarCSV(String archivoCSV, List<Cliente> clientes){
        try(PrintWriter writer = new PrintWriter(new FileWriter(archivoCSV))){
            writer.println("Usuario,Fecha,All Around,Boletos,Aereolínea,Tarjeta,Asientos,Maletas"); //Escribe encabezados en CSV
            
            for(Cliente c : clientes){ //Escribe los datos de TODOS los clientes
                writer.print(c.getUsuario()+",");
                writer.println(c.getFecha()+",");
                writer.print(c.getAllAround()+",");
                writer.print(c.getBoletos()+",");
                writer.print(c.getAereolinea()+",");
                writer.print(c.getTarjeta()+",");
                writer.print(c.getAsientos()+",");
                writer.println(c.getMaletas());

                writer.println();
            }
        System.out.println("Usuarios guardados exitosamente en el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        System.out.println("Error al guardar usuario en el archivo CSV.");
        }
    }

    //Clase para hacer lista de clientes de una categoría específica
    public List<Cliente> listarClientes(String categoria){
            List<Cliente> listaFiltrada = new ArrayList<>();
            for(Cliente cliente : confiList){ //Busca en array
                if(cliente.getTipo().equals(categoria)){
                    listaFiltrada.add(cliente); //Filtra listado
                }
            }
            return listaFiltrada; //Muestra listado
    }

    //Busca usuarios 
    public Cliente buscarUsuario(String estado) {
            for (Cliente c : confiList) { //Busca en array
                if (c.getUsuario().equals(estado)) {
                    return c; //Devuelve el usuario ingresado
                }
            }
            return null; // Devuelve null si no se encuentra el usuario
    }
}

