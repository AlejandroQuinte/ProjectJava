package AccesoDatos;

import Entidades.Proveedor;
import Entidades.Venta;
import Entidades.VentaMesP;
import Entidades.VentasMesE;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class ADVenta {
//atributos

    private String _mensaje;

    //metodos
    public String getMensaje() {
        return _mensaje;
    }
//metodo para crear la coneccion con la base de datos

    public ADVenta() throws Exception {
        _mensaje = "";
    }

    //metodo para insertar enviando un comando sql para ejecutar un procedimiento almacenado en la BD
    public int Insertar(Venta venta) throws Exception {
        int resultado = -1;
        int id = -1;

        //FECHA DATE A SQL.DATE
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String today = formato.format(venta.getFechaVenta());
        java.sql.Date date1 = java.sql.Date.valueOf(today);
 
        CallableStatement stmt = null;
        String sentencia;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();

            sentencia = "exec AgregarVentaProducto ?,?,?,?,?,?";
            
            stmt = _cnn.prepareCall(sentencia);
          
            stmt.setInt(1, venta.getIdProducto());
            stmt.setInt(2, venta.getIdCliente());
            stmt.setInt(3, venta.getIdEmpleado());
            stmt.setInt(4, venta.getCantidadVentidad());
            stmt.setDate(5, date1);
            stmt.registerOutParameter(6, java.sql.Types.INTEGER); 
            stmt.execute();
 
            resultado = stmt.getInt(6);

            switch (resultado) {
                case 1:
                    _mensaje = "Venta ingresada satisfactoriamente";
                    break;
                case 2:
                    _mensaje = "No hay productos para vender";
                    break;
                default:
                    _mensaje = "Ocurrio un error no se pudieron ingresar los datos";
                    break;
            }
            stmt.close();
            _cnn.close();
        } catch (Exception e) {
            throw e;
        }

        return resultado;
    }

    //metodo que envia un comando sql y devuelve un resultset
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException, Exception {
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_venta,ID_Cliente,ID_Empleado,ID_Producto,CantidadVendida,fechaVenta from Venta";
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

    //metodo que envia un comando sql y devuelve un ArrayList
    public ArrayList<Venta> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        ArrayList<Venta> lista = new ArrayList();
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();

            String sentencia = "select id_venta,ID_Empleado,ID_Cliente,ID_Producto,CantidadVendida,fechaVenta from Venta";

            if (!condicion.equals("")) {
                sentencia = "select ID_Venta,Venta.ID_Cliente,ID_Empleado,Venta.ID_Producto,CantidadVendida,fechaVenta from Venta inner join Cliente\n"
                        + "on Cliente.ID_Cliente= Venta.ID_Cliente ";
                sentencia = String.format("%s where %s ", sentencia, condicion);
            }

            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Venta(rs.getInt("id_venta"), rs.getInt("ID_Empleado"), rs.getInt("ID_Cliente"), rs.getInt("ID_Producto"), rs.getInt("CantidadVendida"), rs.getDate("fechaVenta")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //metodo que envia un comando sql y devuelve un Venta
    public Venta ObtenerRegistro(String condicion) throws SQLException, ClassNotFoundException {
        Venta venta = new Venta();
        ResultSet rs = null;
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_venta,ID_Cliente,ID_Empleado,ID_Producto,CantidadVendida,fechaVenta from Venta";

            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                venta.setIdVenta(rs.getInt(1));
                venta.setIdCliente(rs.getInt(2));
                venta.setIdEmpleado(rs.getInt(3));
                venta.setIdProducto(rs.getInt(4));
                venta.setCantidadVentidad(rs.getInt(5));
                venta.setFechaVenta(rs.getDate(6));
                venta.setExiste(true);
            }

        } catch (SQLException e) {
            throw e;
        }
        return venta;
    }

    //metodo para modificar un archivo enviando un comando sql 
    public int Modificar(Venta venta) throws Exception {
        int resultado = 0;
        String sentencia = "update Venta set id_empleado=?,id_cliente=?,id_producto=?,cantidadvendida=?,fechaventa=? where id_Venta=?";

        //CallableStatement cst = _cnn.prepareCall("exec ");
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, venta.getIdCliente());
            ps.setInt(2, venta.getIdEmpleado());
            ps.setInt(3, venta.getIdProducto());
            ps.setInt(4, venta.getCantidadVentidad());
            ps.setDate(5, new java.sql.Date(venta.getFechaVenta().getDay(), venta.getFechaVenta().getMonth(), venta.getFechaVenta().getYear()));
            ps.setInt(5, venta.getIdVenta());
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

    //metodo para Eliminar un archivo enviando un comando sql 
    public int Eliminar(Venta venta) throws Exception {
        int resultado = 0;
        String sentencia = "delete venta where id_venta=?";
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, venta.getIdVenta());
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

    //metodo que envia un comando sql y devuelve un ArrayList
    public ArrayList<VentasMesE> ListarRegistrosEmpleados(String condicion, String fecha,String nombreEmpleado) throws SQLException, ClassNotFoundException {
        int resultado = 0;
        ResultSet rs = null;
        ArrayList<VentasMesE> lista = new ArrayList();
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            String sentencia = "select ID_Venta,Nombre,Marca,CantidadVendida,NombreEmpleado,fechaVenta from Venta inner join Empleado on Venta.ID_Empleado=Empleado.ID_Empleado  \n"
                    + "inner join Producto on Producto.ID_Producto= Venta.ID_Producto ";

            Statement stm = _cnn.createStatement();

            if (!fecha.equals("") || !condicion.equals("") || !nombreEmpleado.equals("")) {
                sentencia = String.format("%s where  fechaVenta= '%s' and NombreEmpleado='%s'  order by CantidadVendida desc", sentencia, fecha,nombreEmpleado); 
            }
            rs = stm.executeQuery(sentencia);

            while (rs.next()) {

                lista.add(new VentasMesE(rs.getInt("id_venta"), rs.getString("Nombre"), rs.getString("Marca"), rs.getInt("CantidadVendida"), rs.getString("NombreEmpleado"), String.valueOf(rs.getDate("fechaVenta"))));

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //metodo que envia un comando sql y devuelve un ArrayList
    public ArrayList<VentaMesP> ListarRegistrosProductos(String condicion, String fecha) throws SQLException, ClassNotFoundException {
        int resultado = 0;
        ResultSet rs = null;
        ArrayList<VentaMesP> lista = new ArrayList();
        Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            String sentencia = "select ID_Venta,Nombre,Marca,CantidadVendida from Venta inner join Producto on Producto.ID_Producto= Venta.ID_Producto ";

            Statement stm = _cnn.createStatement();

            if (!fecha.equals("")) {
                sentencia = String.format("%s where fechaVenta= '%s'  order by CantidadVendida desc", sentencia, fecha);

            }
            rs = stm.executeQuery(sentencia);

            while (rs.next()) {

                lista.add(new VentaMesP(rs.getInt("id_venta"), rs.getString("Nombre"), rs.getString("Marca"), rs.getInt("CantidadVendida")));

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

}
