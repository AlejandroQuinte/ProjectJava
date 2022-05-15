 
package Entidades;

/**
 *
 * @author alequ
 */
public class Empleado extends Persona{
    //Atributos
    private int IdEmpleado;
    private boolean existe;

    //Metodos
    public Empleado(int IdEmpleado, String Nombre, String Apellido, String CorreoElectronico, int Telefono) {
        super(Nombre, Apellido, CorreoElectronico, Telefono);
        this.IdEmpleado = IdEmpleado;
        this.existe = true;
    }

    public Empleado() {
        this.IdEmpleado = 0;
        this.existe = false;
    }

    public Empleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
        this.existe = true;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    
 
    
}
