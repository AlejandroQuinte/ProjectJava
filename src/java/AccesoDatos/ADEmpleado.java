package AccesoDatos;
 
import Entidades.Cliente;
import Entidades.Empleado;
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
public class ADEmpleado {
//Atributos 
    private String _mensaje;
//Metodos
    public String getMensaje() {
        return _mensaje;
    }
//metodo para crear la coneccion a la base de datos
    public ADEmpleado() throws Exception {
       _mensaje="";
    }

    //metodo para insertar datos enviando un comando sql y devuelve el id del dato agregado
    public int Insertar(Empleado empleado) throws Exception {
        int id = -1;
        String sentencia = "insert into Empleado(NombreEmpleado,ApellidoEmpleado,CorreoElectronicoEmpleado,TelefonoEmpleado) values (?,?,?,?)";
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getCorreoElectronico());
            ps.setInt(4, empleado.getTelefono());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                _mensaje = "Empleado ingresado satisfactoriamente";
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }

        return id;
    }

    //Metodo que envia un comando sql para obtener los datos y devuelve un resultset
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException, Exception {
        ResultSet rs = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_empleado,NombreEmpleado,ApellidoEmpleado,CorreoElectronicoEmpleado,TelefonoEmpleado from Empleado";
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

    //Metodo que envia un comando sql para obtener los datos y devuelve un ArrayList
    public ArrayList<Empleado> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        ArrayList<Empleado> lista = new ArrayList();
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_empleado,Nombreempleado,Apellidoempleado,CorreoElectronicoempleado,TelefonoEmpleado from Empleado";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Empleado(rs.getInt("id_empleado"), rs.getString("Nombreempleado"), rs.getString("Apellidoempleado"), rs.getString("CorreoElectronicoempleado"), rs.getInt("TelefonoEmpleado")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

     //Metodo que envia un comando sql para obtener los datos y devuelve un objeto Empleado
    public Empleado ObtenerRegistro(String condicion) throws SQLException, ClassNotFoundException {
        Empleado empleado = new Empleado();
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_Empleado,NombreEmpleado,ApellidoEmpleado,CorreoElectronicoEmpleado,TelefonoEmpleado from empleado";

            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellido(rs.getString(3));
                empleado.setCorreoElectronico(rs.getString(4));
                empleado.setTelefono(rs.getInt(5));
                empleado.setExiste(true);
            }

        } catch (SQLException e) {
            throw e;
        }
        return empleado;
    }

    //Metodo que modifica un archivo enviando un comando sql y devuelde 1 si se ejecuto correctamete todo
    public int Modificar(Empleado empleado) throws Exception {
        int resultado = 0;
        String sentencia = "update Empleado set NombreEmpleado=?,ApellidoEmpleado=?,CorreoElectronicoEmpleado=?,TelefonoEmpleado=? where id_Empleado=?";

         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getCorreoElectronico());
            ps.setInt(4, empleado.getTelefono());
            ps.setInt(5, empleado.getIdEmpleado());
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

    //Metodo que Elimina un archivo enviando un comando sql y devuelde 1 si se ejecuto correctamete todo
    public int Eliminar(Empleado empleado) throws Exception {
        int resultado = 0;
        String sentencia = "delete Empleado where id_empleado=?";
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, empleado.getIdEmpleado());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro eliminado correctamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return resultado;
    }

}
