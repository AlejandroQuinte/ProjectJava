 
package LogicaNegocio;

import AccesoDatos.ADEmpleado;
import AccesoDatos.ADProveedor;
import Entidades.Empleado;
import Entidades.Proveedor;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNProveedor {
    //atributos
    private String _mensaje;
//metodos
    public String getMensaje() {
        return _mensaje;
    }
//metodo para tener acceso a datos y Insertar
    public int Insertar(Proveedor proveedor) throws Exception {
        int id = -1;
         ADProveedor ADProveedor;
        try {
           ADProveedor = new ADProveedor();
            id =ADProveedor.Insertar(proveedor);
            _mensaje =ADProveedor.getMensaje();

        } catch (Exception e) {
            throw e;
        }
        return id;
    }
//metodo para tener acceso a datos y obtener la informacion
    public ResultSet ListarRegistro(String condicion, String orden) throws Exception {
        ResultSet resultado;
       ADProveedor ADProveedor;
        try {
           ADProveedor = new ADProveedor();
            resultado =ADProveedor.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y obtener la informacion
    public ArrayList<Proveedor> ListarRegistro(String condicion) throws Exception {
        ArrayList<Proveedor> resultado;
      ADProveedor ADProveedor ;
        try {
            ADProveedor = new ADProveedor();
            resultado = ADProveedor.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y obtener la informacion
    public Proveedor ObtenerRegistro(String condicion) throws Exception {
        Proveedor resultado;
        ADProveedor ADProveedor;
        try {
            ADProveedor = new ADProveedor();
            resultado = ADProveedor.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje = "Empleado recuperado exitosamente";
            } else {
                _mensaje = "Empleaado no existe";
            }
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
    //metodo para tener acceso a datos y Modificar
     public int Modificar (Proveedor proveedor) throws Exception{
        
        int resultado =-1;
        ADProveedor ADProveedor;
        try {
            ADProveedor = new ADProveedor();
            resultado=ADProveedor.Modificar(proveedor);
            _mensaje=ADProveedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    
    
    //metodo para tener acceso a datos y Eliminar
    public int Eliminar (Proveedor proveedor) throws Exception{
         
        int resultado =-1;
        ADProveedor ADProveedor;
        try {
            ADProveedor = new ADProveedor();
            resultado=ADProveedor.Eliminar(proveedor);
            _mensaje=ADProveedor.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    
}
