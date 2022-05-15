 
package Entidades;

/**
 *
 * @author alequ
 */
public class Persona {
    //atributos
    private String Nombre;
    private String Apellido;
    private String CorreoElectronico;
    private int Telefono;

    //metodos
    public Persona(String Nombre, String Apellido, String CorreoElectronico, int Telefono) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.CorreoElectronico = CorreoElectronico;
        this.Telefono = Telefono;
    }
    
    
    public Persona() {
        this.Nombre = "";
        this.Apellido = "";
        this.CorreoElectronico = "";
        this.Telefono = 0;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String CorreoElectronico) {
        this.CorreoElectronico = CorreoElectronico;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }
    
    
    
}
