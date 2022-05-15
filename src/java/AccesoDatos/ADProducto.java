package AccesoDatos;

import Entidades.Producto;
import java.sql.*;
import java.util.*;

/**
 *
 * @author alequ
 */
public class ADProducto {
//atributos

    private String _mensaje;
//metodos

    public String getMensaje() {
        return _mensaje;
    }

    //metodo para la coneccion
    public ADProducto() throws Exception {

        _mensaje = "";

    }

    //metodo para insertar los datos enviando un comando sql y los datos devuelve el id del Producto
    public int Insertar(Producto producto) throws Exception {
        int id = -1;
        String sentencia = "insert into Producto (Marca,Nombre,CantidadMedida,TipoMedida,PrecioCompra,PrecioVenta,TipoMoneda,CantidadProducto) values (?, ?, ?,?,?,?,?,?)";
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getMarca());
            ps.setString(2, producto.getNombre());
            ps.setInt(3, producto.getCantidadMedida());
            ps.setString(4, producto.getTipoMedida());
            ps.setFloat(5, producto.getPrecioCompra());
            ps.setFloat(6, producto.getPrecioVenta());
            ps.setString(7, producto.getTipoMoneda());
            ps.setInt(8, producto.getCantidadProducto());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                _mensaje = "Producto ingresado satisfactoriamente";
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return id;
    }

    //metodo para listar registro enviando un comadno sql y devuelve un resultSet
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_producto,Marca,Nombre,CantidadMedida,TipoMedida,PrecioCompra,PrecioVenta,TipoMoneda,CantidadProducto from producto";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);
            }
            if (!orden.equals("")) {
                sentencia = String.format("%s order by %s", sentencia, orden);
            }
            rs = stm.executeQuery(sentencia);

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return rs;
    }

    //metodo para listar registro enviando un comadno sql y devuelve un ArrayyList
    public ArrayList<Producto> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {
        ResultSet rs = null; 
        ArrayList<Producto> lista = new ArrayList();
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement(); 
            String sentencia = "select id_producto,Marca,Nombre,CantidadMedida,TipoMedida,PrecioCompra,PrecioVenta,TipoMoneda,CantidadProducto from producto";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Producto(rs.getInt("id_producto"), rs.getString("Marca"), rs.getString("Nombre"),
                        rs.getInt("CantidadMedida"), rs.getString("TipoMedida"), rs.getFloat("PrecioCompra"), rs.getFloat("PrecioVenta"), rs.getString("TipoMoneda"), rs.getInt("CantidadProducto")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //metodo para listar registro enviando un comadno sql y devuelve un Producto
    public Producto ObtenerRegistro(String condicion) throws SQLException, ClassNotFoundException {
        Producto producto = new Producto();
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select ID_PRODUCTO,Marca,Nombre,CantidadMedida,TipoMedida,PrecioCompra,PrecioVenta,TipoMoneda,CantidadProducto from producto";

            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
                producto.setMarca(rs.getString(2));
                producto.setNombre(rs.getString(3));
                producto.setCantidadMedida(rs.getInt(4));
                producto.setTipoMedida(rs.getString(5));
                producto.setPrecioCompra(rs.getFloat(6));
                producto.setPrecioVenta(rs.getFloat(7));
                producto.setTipoMedida(rs.getString(8));
                producto.setCantidadProducto(rs.getInt(9));
                producto.setExiste(true);
            }

        } catch (SQLException e) {
            throw e;
        }
        return producto;
    }

    //metodo para modificar enviando un comando sql y devuelve un un 1 si se ejecuto correctamente
    public int Modificar(Producto producto) throws Exception {
        int resultado = 0;
        String sentencia = "update Producto set marca=?,nombre=?,cantidadmedida=?,tipomedida=?,preciocompra=?,precioventa=?,tipomoneda=?,cantidadproducto=? where   ID_PRODUCTO=?";

        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, producto.getMarca());
            ps.setString(2, producto.getNombre());
            ps.setInt(3, producto.getCantidadMedida());
            ps.setString(4, producto.getTipoMedida());
            ps.setFloat(5, producto.getPrecioCompra());
            ps.setFloat(6, producto.getPrecioVenta());
            ps.setString(7, producto.getTipoMoneda());
            ps.setInt(8, producto.getCantidadProducto());
            ps.setInt(9, producto.getIdProducto());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro modificado satisfactoriamente";
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return resultado;
    }
    //metodo para Eliminar enviando un comando sql y devuelve un un 1 si se ejecuto correctamente

    public int Eliminar(Producto producto) throws Exception {
        int resultado = -1;
        String sentencia;
        CallableStatement stmt = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();

            sentencia = "exec EliminarProducto ?,?";
            stmt = _cnn.prepareCall(sentencia);

            stmt.setInt(1, producto.getIdProducto());
            stmt.registerOutParameter(2, java.sql.Types.INTEGER);

            stmt.execute();

            resultado = stmt.getInt(2);

            if (resultado > 0) {
                _mensaje = "Registro eliminado correctamente";
            } else {
                _mensaje = "Registro NO se puede eliminar, dato conectado con las compras realizadas";
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
