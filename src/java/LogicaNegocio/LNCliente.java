package LogicaNegocio;

import AccesoDatos.ADCliente;
import Entidades.Cliente;
import Entidades.Producto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNCliente {
//atributos
    private String _mensaje;
//metodos
    public String getMensaje() {
        return _mensaje;
    }
    //hace el llamado hacia el acceso a  datos para insertar datos
    public int Insertar(Cliente cliente) throws Exception {
        int id = -1;
        ADCliente ADCliente;
        try {
            ADCliente = new ADCliente();
            id = ADCliente.Insertar(cliente);
            _mensaje = ADCliente.getMensaje();

        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    //hace el llamado hacia el acceso a  datos para obtener datos con un resultSet
    public ResultSet ListarRegistro(String condicion, String orden) throws Exception {
        ResultSet resultado;
        ADCliente ADCliente;
        try {
            ADCliente = new ADCliente();
            resultado = ADCliente.ListarRegistros(condicion, orden);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }

  //hace el llamado hacia el acceso a  datos para obtener datos con un ArrayList
    public ArrayList<Cliente> ListarRegistro(String condicion) throws Exception {
        ArrayList<Cliente> resultado;
        ADCliente ADCliente;
        try {
            ADCliente = new ADCliente();
            resultado = ADCliente.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }

    //hace el llamado hacia el acceso a  datos para obtener datos con un Cliente
    public Cliente ObtenerRegistro(String condicion) throws Exception {
        Cliente resultado;
        ADCliente ADCliente;
        try {
            ADCliente = new ADCliente();
            resultado = ADCliente.ObtenerRegistro(condicion);
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

    //hace el llamado hacia el acceso a  datos para modificar
    public int Modificar(Cliente cliente) throws Exception {

        int resultado = -1;
        ADCliente ADCliente;
        try {
            ADCliente = new ADCliente();
            resultado = ADCliente.Modificar(cliente);
            _mensaje = ADCliente.getMensaje();

        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }

    //hace el llamado hacia el acceso a  datos para eliminar
    public int Eliminar(Cliente cliente) throws Exception {

        int resultado = -1;
        ADCliente ADCliente;
        try {
            ADCliente = new ADCliente();
            resultado = ADCliente.Eliminar(cliente);
            _mensaje = ADCliente.getMensaje();

        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }

}
