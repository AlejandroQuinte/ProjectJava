 
package LogicaNegocio;

import AccesoDatos.ADEmpleado;
import Entidades.Empleado;
import Entidades.Producto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNEmpleado {
    //atributos
    private String _mensaje;
//metodos
    public String getMensaje() {
        return _mensaje;
    }
    //metodo para tener acceso a datos y insertar
    public int Insertar(Empleado empleado) throws Exception {
        int id = -1;
         ADEmpleado ADEmpleado;
        try {
            ADEmpleado = new ADEmpleado();
            id = ADEmpleado.Insertar(empleado);
            _mensaje = ADEmpleado.getMensaje();

        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //metodo para tener acceso a datos y Obtener datos
    public ResultSet ListarRegistro(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADEmpleado ADEmpleado;
        try {
            ADEmpleado = new ADEmpleado();
            resultado = ADEmpleado.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y Obtener datos
    public ArrayList<Empleado> ListarRegistro(String condicion) throws Exception {
        ArrayList<Empleado> resultado;
        ADEmpleado ADEmpleado;
        try {
            ADEmpleado = new ADEmpleado();
            resultado = ADEmpleado.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }
//metodo para tener acceso a datos y Obtener datos
    public Empleado ObtenerRegistro(String condicion) throws Exception {
        Empleado resultado;
        ADEmpleado ADEmpleado;
        try {
            ADEmpleado = new ADEmpleado();
            resultado = ADEmpleado.ObtenerRegistro(condicion);
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
     public int Modificar (Empleado empleado) throws Exception{
        
        int resultado =-1;
        ADEmpleado ADEmpleado;
        try {
            ADEmpleado = new ADEmpleado();
            resultado=ADEmpleado.Modificar(empleado);
            _mensaje=ADEmpleado.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    
    
    //metodo para tener acceso a datos y Eliminar
    public int Eliminar (Empleado empleado) throws Exception{
         
        int resultado =-1;
        ADEmpleado ADEmpleado;
        try {
            ADEmpleado = new ADEmpleado();
            resultado=ADEmpleado.Eliminar(empleado);
            _mensaje=ADEmpleado.getMensaje();
            
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    
}
