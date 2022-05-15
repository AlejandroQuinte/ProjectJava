/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;
 
import Entidades.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class ADCliente {
    //Atributos 
    private String _mensaje;

    //Metodos
    //metodo para obtener el mensaje
    public String getMensaje() {
        return _mensaje;
    }
    
    //metodo para crear la coneccion con la base de datos
    public ADCliente() throws Exception {
        _mensaje="";
    }

    //metodo para insertar cliente enviandole el comando sql y colocando los datos en los signos de pregunta, retorna el id del cliente
    public int Insertar(Cliente cliente) throws Exception {
        int id_cliente = -1;
        String sentencia = "insert into Cliente(Nombre,Apellido,CorreoElectronico,Telefono) values (?,?,?,?)";
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreoElectronico());
            ps.setInt(4, cliente.getTelefono());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id_cliente = rs.getInt(1);
                _mensaje = "Cliente ingresado satisfactoriamente";
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }

        return id_cliente;

    }

    //Metodo para el mostrado de datos en la tabla,enviando el sql comando apra seleccionar la informacion, devuelve un resultSet
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException, Exception {
        ResultSet rs = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_cliente,Nombre,Apellido,CorreoElectronico,Telefono from cliente";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            if (!orden.equals("")) {
                sentencia = String.format("%s order by %s", sentencia, orden);
            }
            rs = stm.executeQuery(sentencia);

        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return rs;
    }
    
    //Metodo para el mostrado de datos en la tabla,enviando el sql comando apra seleccionar la informacion, devuelve un arrayList
    public ArrayList<Cliente> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        ArrayList<Cliente> lista = new ArrayList();
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_cliente,Nombre,Apellido,CorreoElectronico,Telefono from cliente";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("CorreoElectronico"), rs.getInt("telefono")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

     //Metodo para obtener los datos del cliente ,enviando el sql comando apra seleccionar la informacion, devuelve un Cliente
    public Cliente ObtenerRegistro(String condicion) throws SQLException, ClassNotFoundException {
        Cliente cliente = new Cliente();
        ResultSet rs = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_cliente,Nombre,Apellido,CorreoElectronico,Telefono from cliente";

            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido(rs.getString(3));
                cliente.setCorreoElectronico(rs.getString(4));
                cliente.setTelefono(rs.getInt(5));
                cliente.setExiste(true);
            }

        } catch (SQLException e) {
            throw e;
        }
        return cliente;
    }

    
    //metodo para modificar, envia un comando sql para modigicar el archivo
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = 0;
        String sentencia = "update Cliente set nombre=?,apellido=?,correoelectronico=?,telefono=? where id_cliente=?";

         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreoElectronico());
            ps.setInt(4, cliente.getTelefono());
            ps.setInt(5, cliente.getIdCliente());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro modificado satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return resultado;
    }

    //Metodo para eliminar al cliente enviando una ejecucion de procedimietno almacenado
    public int Eliminar(Cliente cliente) throws Exception {
        int resultado = 0;
        String sentencia;
        CallableStatement stmt = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();

            sentencia = "exec EliminarCliente ?,?";
            stmt = _cnn.prepareCall(sentencia);

            stmt.setInt(1, cliente.getIdCliente());
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            stmt.execute();

            resultado = stmt.getInt(2);

            if (resultado > 0) {
                _mensaje = "Registro eliminado correctamente";
            } else {
                _mensaje = "Registro NO se puede eliminar, dato conectado con las Ventas realizadas";
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            stmt.close();
            _cnn.close();
        }
        return resultado;
    }

}
