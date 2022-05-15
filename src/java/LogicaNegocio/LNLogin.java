 
package LogicaNegocio;

import AccesoDatos.ADLogin;
import java.sql.CallableStatement;

/**
 *
 * @author alequ
 */
public class LNLogin {
    //atributos
    private String _mensaje;
    
    //metodos
    public String getMensaje(){
        return _mensaje;
    }
    
    //metodo para tener acceso a datos y ComprobarLogin
    public int ComprobarLogin(String user, String password)  throws Exception{
        int resultado =-1;
        
        ADLogin adloginl;
        try {
            adloginl = new ADLogin();
            resultado = adloginl.ComprobarLogin(user, password);
            
        } catch (Exception e) {
            throw e;
        }
        
        
        
        return resultado;
    }
    
    
    
   
    
    
    
    
}
