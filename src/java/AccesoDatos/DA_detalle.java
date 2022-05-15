/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Entidades.DetalleFactura;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Steven
 */
public class DA_detalle {
    
    private String _Mensaje;
    
    public String getMensaje(){
        return _Mensaje;
    }
    
    public DA_detalle(){
        _Mensaje = "";
    }
    
    public List<DetalleFactura> ListarRegistros(String Condicion) throws Exception{
        ResultSet Rs = null;
        DetalleFactura entidad;
        List<DetalleFactura> Lista = new ArrayList<>();
        Connection _conexion = null;
        try{
            _conexion = ClaseConexion.getConnection();
            Statement ST = _conexion.createStatement();
            String Sentencia;
            
            Sentencia = "SELECT NUM_FACTURA, DETALLE_FACTURA.ID_PRODUCTO , PRODUCTO.Nombre, CANTIDAD, PRECIO_VENTA"
                    + " FROM DETALLE_FACTURA "
                    + "INNER JOIN PRODUCTO ON DETALLE_FACTURA.ID_PRODUCTO = PRODUCTO.ID_PRODUCTO";
            if(!Condicion.equals("")){
                Sentencia = String.format("%s Where %s", Sentencia, Condicion);
            }
            Rs = ST.executeQuery(Sentencia);
            while(Rs.next()){
                entidad = new DetalleFactura(Rs.getInt("NUM_FACTURA"),
                        Rs.getInt("id_producto"),
                        Rs.getString("Nombre"),
                        Rs.getInt("cantidad"),
                        Rs.getInt("precio_venta"));
                Lista.add(entidad);
            }
        }catch(Exception ex){
            throw ex;
        }finally{
            if(_conexion != null){
                ClaseConexion.close(_conexion);
            }
        }
        return Lista;
    }
    
    public int Eliminar(DetalleFactura Entidad) throws Exception{
        CallableStatement CS = null;
        int resultado = 0;
        Connection _Conexion = null;
        try{
            _Conexion = ClaseConexion.getConnection();
            CS = _Conexion.prepareCall("{call eliminar_Detalle(?,?,?)}");
            
            CS.setInt(1, Entidad.getIdFactura());
            CS.setInt(2, Entidad.getIdProducto());
            CS.setString(3, _Mensaje);
            
            CS.registerOutParameter(3, Types.VARCHAR);
            resultado = CS.executeUpdate();
            
            _Mensaje=CS.getString(3);
            
        }catch(Exception ex){
            resultado = -1;
            throw ex;
        }finally{
            if(_Conexion != null){
                ClaseConexion.close(_Conexion);
            }
        }
        return resultado;
    }
    
    
}
