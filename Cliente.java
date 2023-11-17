/* Laboratorio #4
 * Marinés García 23391
 * CLASS
 */

public class Cliente implements Premium, Base{

    //Declaración de variables
    private String usuario;
    private String contr;
    private String tipo;
    private String fecha;
    private String allAround;
    private int boletos;
    private String aereolinea;
    private int tarjeta;
    private int asientos;
    private int maletas;

    //Constructor
    public Cliente(String usuario, String contr, String tipo, String fecha, String allAround, int boletos, String aereolinea, int tarjeta, int asientos, int maletas){
        this.usuario = usuario;
        this.contr = contr;
        this.tipo = tipo;
        this.fecha = fecha;
        this.allAround = allAround;
        this.boletos = boletos;
        this.aereolinea = aereolinea;
        this.tarjeta = tarjeta;
        this.asientos = asientos;
        this.maletas = maletas;

    }

    //Setters y Getters
    public void setUsuario(String usuario){
        this.usuario=usuario;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public void setContraseña(String contr){
        this.contr=contr;
    }

    public String getContraseña(){
        return this.contr;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setFecha(String fecha){
        this.fecha=fecha;
    }

    public String getFecha(){
        return this.fecha;
    }

    public void setAllAround(String allAround){
        this.allAround=allAround;
    }

    public String getAllAround(){
        return this.allAround;
    }

    public void setBoletos(int boletos){
        this.boletos=boletos;
    }
    public int getBoletos(){
        return this.boletos;
    }
    public void setAereolinea(String aereolinea){
        this.aereolinea=aereolinea;
    }

    public String getAereolinea(){
        return this.aereolinea;
    }   

    public void setTarjeta(int tarjeta){
        this.tarjeta = tarjeta;
    }
    public int getTarjeta(){
        return this.tarjeta;
    }


    @Override
    public int getAsientos(){
        return asientos;
    }

    @Override
    public int getMaletas(){
        return maletas;
    }

    @Override
    public String getBase(){
        return "\nSerá habilitado pronto :)";
    }

    public String toStringR(){//To String para reservas
        return "\nUsuario: "+this.getUsuario()+ "\nFecha de vuelo:" + this.getFecha() + "\nAll Around: " + this.getAllAround() + "\nCantidad de boletos: " + this.getBoletos() + "\nAereolínea:" + this.getAereolinea();
    } 

    public String toStringC(){ //To String para confirmaciones
        return "\nUsuario: "+this.getUsuario()+ "\nMétodo de pago (tarjeta):" + this.getTarjeta() + "\nClase de vuelo: Primera clase " + this.getAllAround() + "\nCantidad de maletas: " + this.getMaletas();
    }    
}
