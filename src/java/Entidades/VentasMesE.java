package Entidades;

/**
 *
 * @author alequ
 */
public class VentasMesE {

    private int idVenta;
    private String Nombre;
    private String Marca;
    private int cantidadVendida;
    private String nombreEmpleado;
    private String fechaVenta;

    public VentasMesE(int idVenta, String Nombre, String Marca, int cantidadVendida, String nombreEmpleado, String fechaVenta) {
        this.idVenta = idVenta;
        this.Nombre = Nombre;
        this.Marca = Marca;
        this.cantidadVendida = cantidadVendida;
        this.nombreEmpleado = nombreEmpleado;
        this.fechaVenta = fechaVenta;
    }

    public VentasMesE() {
        this.idVenta = 0;
        this.Nombre = "";
        this.Marca = "";
        this.cantidadVendida = 0;
        this.nombreEmpleado = "";
        this.fechaVenta = "";
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

}
