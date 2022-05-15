package Servlets;

import Entidades.Cliente;
import LogicaNegocio.LNCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EliminarCliente")
/**
 *
 * @author alequ
 */
public class EliminarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
          throws IOException,ServletException{
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        try {
            //metodo para eliminar un dato de la base de datos obtiene el parametro IDEliminar y lo envia a eliminar
            LNCliente logica = new LNCliente();
            String id = request.getParameter("idEliminar");
            
            int codigo = Integer.parseInt(id);
            Cliente cliente = new Cliente();
            cliente.setIdCliente(codigo);
            int resultado = logica.Eliminar(cliente);
            String mensaje = logica.getMensaje();
            mensaje= URLEncoder.encode(mensaje, "UTF-8");
            
            
            
            response.sendRedirect("FrmListarClientes.jsp?mensajeServletEliminarCliente="+mensaje+"&resultado"+resultado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
