/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;
 
import Entidades.Cliente;
import Entidades.Compra;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author alequ
 */
public class ADCompra {
//Atributos 
    private String _mensaje;

    //metodos
    public String getMensaje() {
        return _mensaje;
    }
//metodo para hacer la coneccion a la base de datos
    public ADCompra() throws Exception {

         _mensaje="";
    }
    
//metodo para insertar enviadno una ejecucion de un procedimiento almacenado
    public int Insertar(Compra compra) throws Exception {
        // int id = -1;
        int resultado = -1;
 
        //FECHA DATE A SQL.DATE
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String today = formato.format(compra.getFechaCompra());
        java.sql.Date date1 = java.sql.Date.valueOf(today);

        CallableStatement stmt = null;
        String sentencia;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();

            sentencia = "exec AgregarCompraProducto ?,?,?,?,?,?";
            stmt = _cnn.prepareCall(sentencia);

            stmt.setInt(1, compra.getIdProducto());
            stmt.setInt(2, compra.getIdVendedor());
            stmt.setInt(3, compra.getIdEmpleado());
            stmt.setInt(4, compra.getCantidadCompra());
            stmt.setDate(5, date1);
            stmt.registerOutParameter(6, java.sql.Types.INTEGER);

            stmt.execute();

            resultado = stmt.getInt(6);
            
            if(resultado==1){
                _mensaje = "Compra ingresada satisfactoriamente";
            }else {
                _mensaje = "Ocurrio un error no se pudieron ingresar los datos";
            }
            stmt.close();
            _cnn.close();
        } catch (Exception e) {
            throw e;
        }

        return resultado;

    }

    //metodo para listar registros enviando un comando sql y devolviendo un resultSet
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_compra,ID_Vendedor,ID_Empleado,ID_Producto,CantidadCompra,fechaCompra from compra";
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

    //metodo para listar registros enviando un comando sql y devolviendo un ArrayList
    public ArrayList<Compra> ListarRegistros(String condicion) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        ArrayList<Compra> lista = new ArrayList();
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_compra,ID_Vendedor,ID_Empleado,ID_Producto,CantidadCompra,fechaCompra from compra";
            if (!condicion.equals("")) {
                sentencia ="select id_compra,ID_Vendedor,ID_Empleado,Compra.ID_Producto,CantidadCompra,fechaCompra from Compra inner join Producto on Compra.ID_Producto=Producto.ID_Producto";
                sentencia = String.format("%s where %s ", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Compra(rs.getInt("id_compra"), rs.getInt("ID_Empleado"),  rs.getInt("ID_Vendedor"),rs.getInt("ID_Producto"), rs.getInt("CantidadCompra"), rs.getDate("fechaCompra")));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    //metodo para listar registros enviando un comando sql y devolviendo una objeto Compra
    public Compra ObtenerRegistro(String condicion) throws SQLException, ClassNotFoundException {
        Compra compra = new Compra();
        ResultSet rs = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select id_compra,ID_Vendedor,ID_Empleado,ID_Producto,CantidadCompra,fechaCompra from compra";

            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s ", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            if (rs.next()) {
                compra.setIdCompra(rs.getInt(1));
                compra.setIdVendedor(rs.getInt(2));
                compra.setIdEmpleado(rs.getInt(3));
                compra.setIdProducto(rs.getInt(4));
                compra.setCantidadCompra(rs.getInt(5));
                compra.setFechaCompra(rs.getDate(6));
                compra.setExiste(true);
            }

        } catch (SQLException e) {
            throw e;
        }
        return compra;
    }

    //metodo para Modificar un registro enviando un comando sql y devolviendo un 1 si todo salio correctamente
    public int Modificar(Compra compra) throws Exception {
        int resultado = 0;
        String sentencia = "update Compra set ID_Vendedor=?,ID_Empleado=?,ID_Producto=?,CantidadCompra=?,fechaCompra=? where ID_Compra=?";

        //FECHA DATE A SQL.DATE
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String today = formato.format(compra.getFechaCompra());
        java.sql.Date date1 = java.sql.Date.valueOf(today);

         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, compra.getIdVendedor());
            ps.setInt(2, compra.getIdEmpleado());
            ps.setInt(3, compra.getIdProducto());
            ps.setInt(4, compra.getCantidadCompra());
            ps.setDate(5, date1);
            ps.setInt(6, compra.getIdCompra());
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
    
//metodo para Eliminar un registro enviando un comando sql y devolviendo un 1 si todo salio correctamente
    public int Eliminar(Compra compra) throws Exception {
        int resultado = 0;
        String sentencia = "delete compra where id_compra=?";
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, compra.getIdCompra());
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

    //metodo para Obtener el id de producto empleado y proveedor enviandole un comando sql con el nombre para poder hacer una busqueda de la informacion
    public int[] obtenerIdProductoEmpleadoProveedor(String[] condicion) throws SQLException, ClassNotFoundException {
        int[] id = new int[3];

        ResultSet rs = null;
         Connection _cnn = null;

        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            //Producto
            String sentencia = String.format("select ID_Producto from Producto where nombre =%s ", condicion[1]);

            rs = stm.executeQuery(sentencia);

            id[0] = rs.getInt(1);

            //Proveedor
            sentencia = String.format("select ID_Proveedor from Proveedor where NombreProveedor =%s ", condicion[2]);

            rs = stm.executeQuery(sentencia);

            id[1] = rs.getInt(1);

            //Empleado
            sentencia = String.format("select ID_Empleado from Empleado where NombreEmpleado =%s ", condicion[3]);

            rs = stm.executeQuery(sentencia);

            id[2] = rs.getInt(1);

        } catch (SQLException e) {
            throw e;
        }

        return id;
    }

}
