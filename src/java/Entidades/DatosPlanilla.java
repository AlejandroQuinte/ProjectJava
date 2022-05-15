 
package Entidades;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alequ
 */
public class DatosPlanilla {
    //atributos
    private int codigo;
    private String nombreProducto;
    private String MarcaProducto;
    private String nombreProveedor;
    private String nombreCliente;
    private String nombreEmpleado;
    private int cantidad;
    private float precioProducto;
    private String fechaCompra;
    
    //metodos 
    public DatosPlanilla(int codigo, String nombreProducto, String MarcaProducto, String nombreProveedor, String nombreCliente, String nombreEmpleado, int cantidad, float precioProducto, String fechaCompra) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.MarcaProducto = MarcaProducto;
        this.nombreProveedor = nombreProveedor;
        this.nombreCliente = nombreCliente;
        this.nombreEmpleado = nombreEmpleado;
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
        this.fechaCompra = fechaCompra;
    }
    
    public DatosPlanilla( ) {
        this.codigo = 0;
        this.nombreProducto = "";
        this.MarcaProducto = "";
        this.nombreProveedor = "";
        this.nombreCliente = "";
        this.nombreEmpleado = "";
        this.cantidad = 0;
        this.precioProducto = 0;
        
        
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        
        this.fechaCompra = dtf2.format(LocalDateTime.now());
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarcaProducto() {
        return MarcaProducto;
    }

    public void setMarcaProducto(String MarcaProducto) {
        this.MarcaProducto = MarcaProducto;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getCantidadCompra() {
        return cantidad;
    }

    public void setCantidadCompra(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    
}
