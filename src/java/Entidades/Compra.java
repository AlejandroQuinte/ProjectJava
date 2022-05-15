 
package Entidades;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alequ
 */
public class Compra {
    //atributos
    private int IdCompra;
    private int IdEmpleado;
    private int IdVendedor;
    private int IdProducto;
    private int CantidadCompra;
    private Date FechaCompra;
    private boolean existe;

    //metodos
    public Compra(int IdCompra, int IdEmpleado, int IdVendedor, int IdProducto, int CantidadCompra, Date FechaCompra ) {
        this.IdCompra = IdCompra;
        this.IdEmpleado = IdEmpleado;
        this.IdVendedor = IdVendedor;
        this.IdProducto = IdProducto;
        this.CantidadCompra = CantidadCompra;
        this.FechaCompra = FechaCompra;
        this.existe = true ;
    }
    
    public Compra( ) {
        this.IdCompra = 0;
        this.IdEmpleado = 0;
        this.IdVendedor = 0;
        this.IdProducto = 0;
        this.CantidadCompra = 0;
        this.FechaCompra =  (Date)(Calendar.getInstance().getTime());
        this.existe = false ;
    }

    public int getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(int IdCompra) {
        this.IdCompra = IdCompra;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdVendedor() {
        return IdVendedor;
    }

    public void setIdVendedor(int IdVendedor) {
        this.IdVendedor = IdVendedor;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getCantidadCompra() {
        return CantidadCompra;
    }

    public void setCantidadCompra(int CantidadCompra) {
        this.CantidadCompra = CantidadCompra;
    }

    public Date getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(Date FechaCompra) {
        this.FechaCompra = FechaCompra;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
