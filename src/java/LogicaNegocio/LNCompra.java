 
package LogicaNegocio;
 
import AccesoDatos.ADCompra; 
import Entidades.Compra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNCompra {
    //atributos
    private String _mensaje;
//metodos
    public String getMensaje() {
        return _mensaje;
    }
 //hace el llamado hacia el acceso a  datos para insertar
    public int Insertar(Compra Compra) throws Exception {
        int id = -1;
        ADCompra ADCompra;
        try {
            ADCompra = new ADCompra();
            id = ADCompra.Insertar(Compra);
            _mensaje = ADCompra.getMensaje();

        } catch (Exception e) {
            throw e;
        }
        return id;
    }
//hace el llamado hacia el acceso a  datos para obtener datos y devuelve un ResultSet
    public ResultSet ListarRegistro(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADCompra ADCompra;
        try {
            ADCompra = new ADCompra();
            resultado = ADCompra.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//hace el llamado hacia el acceso a  datos para obtener datos y devuelve un ArrayList
    public ArrayList<Compra> ListarRegistro(String condicion) throws Exception {
        ArrayList<Compra> resultado;
        ADCompra ADCompra;
        try {
            ADCompra = new ADCompra();
            resultado = ADCompra.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//hace el llamado hacia el acceso a  datos para obtener datos y devuelve un Compra
    public Compra ObtenerRegistro(String condicion) throws Exception {
        Compra resultado;
        ADCompra ADCompra;
        try {
            ADCompra = new ADCompra();
            resultado = ADCompra.ObtenerRegistro(condicion);
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
//hace el llamado hacia el acceso a  datos para Modificar
    public int Modificar(Compra compra) throws Exception {

        int resultado = -1;
        ADCompra ADCompra;
        try {
            ADCompra = new ADCompra();
            resultado = ADCompra.Modificar(compra);
            _mensaje = ADCompra.getMensaje();

        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//hace el llamado hacia el acceso a  datos para eliminar
    public int Eliminar(Compra compra) throws Exception {

        int resultado = -1;
        ADCompra ADCompra;
        try {
            ADCompra = new ADCompra();
            resultado = ADCompra.Eliminar(compra);
            _mensaje = ADCompra.getMensaje();

        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
    
    
    
    
    public int[] obtenerIdProductoEmpleadoProveedor(String[] condicion) throws SQLException, Exception {
        int[] id = new int[3];

        ADCompra ADCompra;
        ResultSet rs = null;
        try { 
             ADCompra = new ADCompra();
             id = ADCompra.obtenerIdProductoEmpleadoProveedor(condicion);
        } catch (SQLException e) {
            throw e;
        }

        return id;
    }
    
    

}
