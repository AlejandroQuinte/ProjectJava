 
package Entidades;

 
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author alequ
 */
public class Venta {
    //atributos
    private int IdVenta;
    private int IdEmpleado;
    private int IdCliente;
    private int IdProducto;
    private int CantidadVentidad;
    private Date FechaVenta;
    private boolean existe;

    //metodos
    public Venta(int IdVenta, int IdEmpleado, int IdCliente, int IdProducto, int CantidadVentidad, Date FechaVenta) {
        this.IdVenta = IdVenta;
        this.IdEmpleado = IdEmpleado;
        this.IdCliente = IdCliente;
        this.IdProducto = IdProducto;
        this.CantidadVentidad = CantidadVentidad;
        this.FechaVenta = FechaVenta;
        this.existe = true;
    }
    
    public Venta() {
        this.IdVenta = 0;
        this.IdEmpleado = 0;
        this.IdCliente = 0;
        this.IdProducto = 0;
        this.CantidadVentidad = 0;
        this.FechaVenta = (Date)(Calendar.getInstance().getTime());
        this.existe = false;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getCantidadVentidad() {
        return CantidadVentidad;
    }

    public void setCantidadVentidad(int CantidadVentidad) {
        this.CantidadVentidad = CantidadVentidad;
    }

    public Date getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(Date FechaVenta) {
        this.FechaVenta = FechaVenta;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
}
