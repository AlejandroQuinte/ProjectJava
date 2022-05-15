package AccesoDatos;

import Entidades.Empleado;
import Entidades.Proveedor;
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
public class ADProveedor {
//atributso 

    private String _mensaje;

    //metodos
    public String getMensaje() {
        return _mensaje;
    }

    public ADProveedor() throws Exception {
        _mensaje = "";
    }

    //metodo insertar
    public int Insertar(Proveedor proveedor) throws Exception {
        int id = -1;
        String sentencia = "insert into Proveedor(Marca,NombreProveedor,ApellidoProveedor,Telefono) values (?,?,?,?)";
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getMarca());
            ps.setString(2, proveedor.getNombreProveedor());
            ps.setString(3, proveedor.getApellidoProveedor());
            ps.setInt(4, proveedor.getTelefono());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                _mensaje = "Proveedor ingresado satisfactoriamente";
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }

        return id;
    }

    //metodo listar registros devuelve un Resultset
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException, Exception {
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_proveedor,Marca,NombreProveedor,ApellidoProveedor,Telefono from proveedor";
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

    //metodo listar registros devuelve un ArrayList
    public ArrayList<Proveedor> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        ArrayList<Proveedor> lista = new ArrayList();
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_proveedor,Marca,NombreProveedor,ApellidoProveedor,Telefono from Proveedor";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt("id_proveedor"), rs.getString("Marca"), rs.getString("NombreProveedor"), rs.getString("ApellidoProveedor"), rs.getInt("Telefono")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //metodo listar registros devuelve un Proveedor
    public Proveedor ObtenerRegistro(String condicion) throws SQLException, ClassNotFoundException {
        Proveedor proveedor = new Proveedor();
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_proveedor,Marca,NombreProveedor,ApellidoProveedor,Telefono from Proveedor";

            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setMarca(rs.getString(2));
                proveedor.setNombreProveedor(rs.getString(3));
                proveedor.setApellidoProveedor(rs.getString(4));
                proveedor.setTelefono(rs.getInt(5));
                proveedor.setExiste(true);
            }

        } catch (SQLException e) {
            throw e;
        }
        return proveedor;
    }

    //metodo para modificar el archivo devuelve un 1 si se ejecuto crrectamente
    public int Modificar(Proveedor proveedor) throws Exception {
        int resultado = 0;
        String sentencia = "update Proveedor set marca=?,nombreproveedor=?,apellidoproveedor=?,telefono=? where id_Proveedor=?";

        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, proveedor.getMarca());
            ps.setString(2, proveedor.getNombreProveedor());
            ps.setString(3, proveedor.getApellidoProveedor());
            ps.setInt(4, proveedor.getTelefono());
            ps.setInt(5, proveedor.getIdProveedor());
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

    //metodo para Eliminar el archivo devuelve un 1 si se ejecuto crrectamente
    public int Eliminar(Proveedor proveedor) throws Exception {
        int resultado = 0;
        String sentencia = "delete Proveedor where id_Proveedor=?";
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, proveedor.getIdProveedor());
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
