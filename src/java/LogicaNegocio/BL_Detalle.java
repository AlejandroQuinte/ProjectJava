/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.DA_detalle;
import Entidades.DetalleFactura;
import java.util.List;

/**
 *
 * @author Steven
 */
public class BL_Detalle {
     private String _Mensaje;
    
    public String getMensaje(){
        return _Mensaje;
    }
    
    //Metodo para mostrar los registros enviando una condicion hacia la clase de acceso a datos
    public List<DetalleFactura> ListarRegistros(String condicion)throws Exception{
        List<DetalleFactura> Datos;
        
        try{
            DA_detalle Da = new DA_detalle();
            Datos = Da.ListarRegistros(condicion);
        }catch(Exception ex){
            Datos = null;
            throw ex;
        }
        return Datos;
    }
    //Metodo para eliminar los registros enviando una entidad hacia la clase de acceso a datos
    public int Eliminar(DetalleFactura Entidad) throws Exception{
        int resultado = 0;
        try{
            DA_detalle DA= new DA_detalle();
            
            resultado = DA.Eliminar(Entidad);
            _Mensaje = DA.getMensaje();
        }catch(Exception ex){
            resultado = -1;
            throw ex;
        }
        
        return resultado;
    }
    
}
