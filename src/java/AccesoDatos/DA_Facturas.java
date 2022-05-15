/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.DetalleFactura;
import Entidades.Factura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven
 */
public class DA_Facturas {

    private String _Mensaje;

    public String getMensaje() {
        return _Mensaje;
    }

    public DA_Facturas() {
        _Mensaje = "";
    }

    public List<Factura> ListarRegistros(String Condicion) throws Exception {
        ResultSet Rs = null;
        Factura entidad;
        List<Factura> ListaF = new ArrayList<>();
        Connection _conexion = null;
        try {
            _conexion = ClaseConexion.getConnection();
            Statement ST = _conexion.createStatement();
            String Sentencia;

            Sentencia = "SELECT NUM_FACTURA, F.ID_CLIENTE, NOMBRE, FECHA, ESTADO FROM Factura F "
                    + "INNER JOIN CLIENTE ON CLIENTE.ID_CLIENTE = F.ID_CLIENTE";

            if (!Condicion.equals("")) {
                Sentencia = String.format("%s Where %s", Sentencia, Condicion);
            }

            Rs = ST.executeQuery(Sentencia);
            while (Rs.next()) {
                entidad = new Factura(Rs.getInt("NUM_FACTURA"),
                        Rs.getInt("Id_cliente"),
                        Rs.getString("Nombre"),
                        Rs.getDate("Fecha"),
                        Rs.getString("Estado"));
                ListaF.add(entidad);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return ListaF;
    }

    public Factura ObtenerRegistro(String condicion) throws Exception {
        ResultSet RsDatos = null;
        Factura Entidad = new Factura();
        String sentencia;
        Connection _conexion = null;
        sentencia = "SELECT NUM_FACTURA, F.ID_CLIENTE, NOMBRE, FECHA, ESTADO FROM FACTURA F "
                + "INNER JOIN CLIENTE ON CLIENTE.ID_CLIENTE=F.ID_CLIENTE";
        if (!condicion.equals("")) {
            sentencia = String.format("%s where %s", sentencia, condicion);
        }

        try {
            _conexion = ClaseConexion.getConnection();
            Statement ST = _conexion.createStatement();
            RsDatos = ST.executeQuery(sentencia);
            if (RsDatos.next()) {
                Entidad.setIdFactura(RsDatos.getInt("NUM_FACTURA"));
                Entidad.setIdCliente(RsDatos.getInt("ID_CLIENTE"));
                Entidad.setNombreCliente(RsDatos.getString("NOMBRE"));
                Entidad.setFecha(RsDatos.getDate("FECHA"));
                Entidad.setEstado(RsDatos.getString("estado"));
                Entidad.setExisteRegistro(true);
            } else {
                Entidad.setExisteRegistro(false);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return Entidad;
    }

    public int Insertar(Factura EntidadFactura, DetalleFactura EntidadDetalle) throws Exception {
        CallableStatement CS;
        int resultado;
        int idFactura;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();

            _Conexion.setAutoCommit(false);

            CS = _Conexion.prepareCall("{call Guardar_Factura(?,?,?,?,?)}");
            CS.setInt(1, EntidadFactura.getIdFactura());
            CS.setInt(2, EntidadFactura.getIdCliente());
            CS.setDate(3, EntidadFactura.getFecha());
            CS.setString(4, EntidadFactura.getEstado());
            CS.setString(5, _Mensaje);
            CS.registerOutParameter(1, Types.INTEGER);

            resultado = CS.executeUpdate();
            idFactura = CS.getInt(1);

            CS = _Conexion.prepareCall("{call Guardar_Detalle(?,?,?,?,?)}");
            CS.setInt(1, idFactura);
            CS.setInt(2, EntidadDetalle.getIdProducto());
            CS.setInt(3, EntidadDetalle.getCantidad());
            CS.setDouble(4, (double) EntidadDetalle.getPrecio());
            CS.setString(5, _Mensaje);

            CS.registerOutParameter(5, Types.VARCHAR);
            resultado = CS.executeUpdate();

            _Mensaje = CS.getString(5);
            _Conexion.commit();
        } catch (ClassNotFoundException | SQLException ex) {
            _Conexion.rollback();
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return idFactura;
    }

    public int ModificarFactura(Factura EntidadFactura) throws Exception {
        int idfactura = 0;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("update Factura set ID_CLIENTE = ? where NUM_FACTURA = ?");

            PS.setInt(1, EntidadFactura.getIdCliente());
            PS.setInt(2, EntidadFactura.getIdFactura());

            PS.executeUpdate();
            idfactura = EntidadFactura.getIdFactura();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return idfactura;
    }

    public int ModificarEstado(Factura EntidadFactura) throws Exception {
        int resultado = 0;
        Connection _Conexion = null;
        try {
            _Conexion = ClaseConexion.getConnection();
            PreparedStatement PS = _Conexion.prepareStatement("Update Factura set Estado = ? where NUM_FACTURA = ?");

            PS.setString(1, EntidadFactura.getEstado());
            PS.setInt(2, EntidadFactura.getIdFactura());

            resultado = PS.executeUpdate();
        } catch (Exception ex) {
            resultado = -1;
            throw ex;
        } finally {
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        if(resultado > 0){
            _Mensaje = "Factura Cancelada";
        }else{
            _Mensaje = "No se ha podido Cancelar la factura";
        }
        return resultado;
    }
}
