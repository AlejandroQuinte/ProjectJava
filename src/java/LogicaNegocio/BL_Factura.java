/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.DA_Facturas;
import Entidades.DetalleFactura;
import Entidades.Factura;
import java.util.List;

/**
 *
 * @author Steven
 */
public class BL_Factura {

    private String _Mensaje;

    public String getMensaje() {
        return _Mensaje;
    }
//Metodo para mostrar los registros enviando una condicion hacia la clase de acceso a datos
    public List<Factura> ListarRegistros(String condicion) throws Exception {
        List<Factura> Datos;

        try {
            DA_Facturas Da = new DA_Facturas();
            Datos = Da.ListarRegistros(condicion);
        } catch (Exception ex) {
            Datos = null;
            throw ex;
        }

        return Datos;
    }
//Metodo para mostrar un registro enviando una condicion hacia la clase de acceso a datos
    public Factura ObtenerRegistro(String condicion) throws Exception {
        Factura entidad;
        DA_Facturas accesoDatos = new DA_Facturas();

        try {
            entidad = accesoDatos.ObtenerRegistro(condicion);

            if (entidad.isExisteRegistro()) {
                _Mensaje = "Factura recuperada satisfactoriamente";
            } else {
                _Mensaje = "La factura no existe";
            }

        } catch (Exception e) {
            throw e;
        }

        return entidad;
    }
//Metodo para insertar los registros enviando una condicion hacia la clase de acceso a datos
    public int Insertar(Factura Entidad, DetalleFactura EntidadDetalle) throws Exception {

        int resultado = 0;

        try {
            DA_Facturas DA = new DA_Facturas();
            resultado = DA.Insertar(Entidad, EntidadDetalle);
            _Mensaje = DA.getMensaje();
        }
        catch(Exception ex){
            resultado = -1;
            throw ex;
        }
        return resultado;
    }
    //Metodo para modificar los registros enviando una condicion hacia la clase de acceso a datos
    public int ModificarFactura(Factura Entidad) throws Exception{
        int idfactura = 0;
        
        try{
            DA_Facturas DA = new DA_Facturas();
            
            idfactura = DA.ModificarFactura(Entidad);
        }catch(Exception ex){
            throw ex;
        }
        
        return idfactura;
    }
    //Metodo para mostrar el estado los registros enviando una condicion hacia la clase de acceso a datos
    public int ModificarEstado(Factura Entidad) throws Exception {
    
        int resultado = 0;
        
        try{
            DA_Facturas DA = new DA_Facturas();
            
            resultado = DA.ModificarEstado(Entidad);
            _Mensaje = DA.getMensaje();
        }catch(Exception ex){
            throw ex;
        }
        
        return resultado;
    }
}
