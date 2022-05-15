 
package Entidades;

/**
 *
 * @author alequ
 */
public class Cliente extends Persona{
    
    //atributos
    private int IdCliente;
    private boolean existe;
    //metodos
    public Cliente(int IdCliente, String Nombre, String Apellido, String CorreoElectronico, int Telefono) {
        super(Nombre, Apellido, CorreoElectronico, Telefono);
        this.IdCliente = IdCliente;
        this.existe = true;
    }
    
    public Cliente() {
        super("", "", "", 0);
        this.IdCliente = 0;
        this.existe = false;
    }

    public Cliente(int IdCliente) { 
        this.IdCliente = IdCliente;
        this.existe = true;
    }
  
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
