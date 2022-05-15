package LogicaNegocio;

import AccesoDatos.ADCompra;
import AccesoDatos.ADVenta;
import Entidades.Compra;
import Entidades.Venta;
import Entidades.VentaMesP;
import Entidades.VentasMesE;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNVenta {

    //atributos
    private String _mensaje;
    
//metodos
    public String getMensaje() {
        return _mensaje;
    }
    //metodo para tener acceso a datos y Insertar
    public int Insertar(Venta venta) throws Exception {
        int id = -1;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            id = ADVenta.Insertar(venta);
            _mensaje = ADVenta.getMensaje();

        } catch (Exception e) {
            throw e;
        }
        return id;
    }
 //metodo para tener acceso a datos y obtener los datos
    public ResultSet ListarRegistro(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y obtener los datos
    public ArrayList<Venta> ListarRegistro(String condicion) throws Exception {
        ArrayList<Venta> resultado;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y obtener los datos
    public Venta ObtenerRegistro(String condicion) throws Exception {
        Venta resultado;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje = "Cliente recuperado exitosamente";
            } else {
                _mensaje = "Cliente no existe";
            }
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y Modificar
    public int Modificar(Venta venta) throws Exception {

        int resultado = -1;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.Modificar(venta);
            _mensaje = ADVenta.getMensaje();

        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y Eliminar
    public int Eliminar(Venta venta) throws Exception {

        int resultado = -1;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.Eliminar(venta);
            _mensaje = ADVenta.getMensaje();

        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
    
    
    //metodo para tener acceso a datos y obtener los datos
    public ArrayList<VentasMesE> ListarRegistroEmpleado(String condicion, String fecha,String nombreEmpleado) throws Exception {
        ArrayList<VentasMesE> resultado;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.ListarRegistrosEmpleados(condicion,fecha,nombreEmpleado);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
    
    //metodo para tener acceso a datos y obtener los datos
    public ArrayList<VentaMesP> ListarRegistroProducto(String condicion,String fecha) throws Exception {
        ArrayList<VentaMesP> resultado;
        ADVenta ADVenta;
        try {
            ADVenta = new ADVenta();
            resultado = ADVenta.ListarRegistrosProductos(condicion,fecha);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
    

}
