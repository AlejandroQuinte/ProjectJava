
package Entidades;

/**
 *
 * @author alequ
 */
public class Producto {
    //atributos
    private int IdProducto;
    private String Marca;
    private String Nombre;
    private int CantidadMedida;
    private String TipoMedida;
    private float PrecioCompra;
    private float PrecioVenta;
    private String TipoMoneda;
    private int CantidadProducto;
    private boolean existe;

    //metodos
    public Producto(int IdProducto, String Marca, String Nombre, int CantidadMedida, String TipoMedida, float PrecioCompra, float PrecioVenta, String TipoMoneda, int CantidadProducto ) {
        this.IdProducto = IdProducto;
        this.Marca = Marca;
        this.Nombre = Nombre;
        this.CantidadMedida = CantidadMedida;
        this.TipoMedida = TipoMedida;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
        this.TipoMoneda = TipoMoneda;
        this.CantidadProducto = CantidadProducto;
        this.existe = true;
    }
    
    public Producto() {
        this.IdProducto = 0;
        this.Marca = "";
        this.Nombre = "";
        this.CantidadMedida = 0;
        this.TipoMedida = "";
        this.PrecioCompra = 0;
        this.PrecioVenta = 0;
        this.TipoMoneda = "";
        this.CantidadProducto = 0;
        this.existe = false;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCantidadMedida() {
        return CantidadMedida;
    }

    public void setCantidadMedida(int CantidadMedida) {
        this.CantidadMedida = CantidadMedida;
    }

    public String getTipoMedida() {
        return TipoMedida;
    }

    public void setTipoMedida(String TipoMedida) {
        this.TipoMedida = TipoMedida;
    }

    public float getPrecioCompra() {
        return PrecioCompra;
    }

    public void setPrecioCompra(float PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public float getPrecioVenta() {
        return PrecioVenta;
    }

    public void setPrecioVenta(float PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }

    public String getTipoMoneda() {
        return TipoMoneda;
    }

    public void setTipoMoneda(String TipoMoneda) {
        this.TipoMoneda = TipoMoneda;
    }

    public int getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(int CantidadProducto) {
        this.CantidadProducto = CantidadProducto;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
    
}
