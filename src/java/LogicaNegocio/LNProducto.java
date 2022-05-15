 
package LogicaNegocio;

import AccesoDatos.ADProducto;
import Entidades.Producto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNProducto {
    //atributos
    private String _mensaje;
//metodos
    public String getMensaje() {
        return _mensaje;
    }
 //metodo para tener acceso a datos y insertar
    public int Insertar(Producto producto) throws Exception {
        int id = -1;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            id = adproducto.Insertar(producto);
            _mensaje = adproducto.getMensaje();

        } catch (Exception e) {
            throw e;
        }
        return id;
    }
 //metodo para tener acceso a datos y obtener la informacion
    public ResultSet ListarRegistro(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            resultado = adproducto.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y obtener la informacion
    public ArrayList<Producto> ListarRegistro(String condicion) throws Exception {
        ArrayList<Producto> resultado;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            resultado = adproducto.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y obtener la informacion
    public Producto ObtenerRegistro(String condicion) throws Exception {
        Producto resultado;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            resultado = adproducto.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje = "Producto recuperado exitosamente";
            } else {
                _mensaje = "Producto no existe";
            }
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
    //metodo para tener acceso a datos y Modificar
     public int Modificar (Producto producto) throws Exception{
        
        int resultado =-1;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            resultado=adproducto.Modificar(producto);
            _mensaje=adproducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    
    
    //metodo para tener acceso a datos y Eliminar
    public int Eliminar (Producto producto) throws Exception{
        
        int resultado =-1;
        ADProducto adproducto;
        try {
            adproducto = new ADProducto();
            resultado=adproducto.Eliminar(producto);
            _mensaje=adproducto.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    
    
}
