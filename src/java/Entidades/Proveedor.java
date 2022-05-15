 
package Entidades;

/**
 *
 * @author alequ
 */
public class Proveedor {
    //atributos
    private int IdProveedor;
    private String Marca;
    private String NombreProveedor;
    private String ApellidoProveedor;
    private int Telefono;
    private boolean existe;

    //metoodos
    public Proveedor(int IdProveedor, String Marca, String NombreProveedor, String ApellidoProveedor, int Telefono ) {
        this.IdProveedor = IdProveedor;
        this.Marca = Marca;
        this.NombreProveedor = NombreProveedor;
        this.ApellidoProveedor = ApellidoProveedor;
        this.Telefono = Telefono;
        this.existe = true;
    }
    
    public Proveedor () {
        this.IdProveedor = 0;
        this.Marca = "";
        this.NombreProveedor = "";
        this.ApellidoProveedor = "";
        this.Telefono = 0;
        this.existe = false;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getNombreProveedor() {
        return NombreProveedor;
    }

    public void setNombreProveedor(String NombreProveedor) {
        this.NombreProveedor = NombreProveedor;
    }

    public String getApellidoProveedor() {
        return ApellidoProveedor;
    }

    public void setApellidoProveedor(String ApellidoProveedor) {
        this.ApellidoProveedor = ApellidoProveedor;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
    
}
