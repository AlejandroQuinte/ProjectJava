/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.DetalleFactura;
import Entidades.Factura;
import LogicaNegocio.BL_Factura;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


@WebServlet(name = "FacturarD", urlPatterns = {"/FacturarD"})
public class Facturar extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         
        try{
            BL_Factura LogicaFactura = new BL_Factura();
            Factura EntidadFactura= new Factura();
            DetalleFactura EntidadDetalle = new DetalleFactura();
            int resultado;
            String mensaje= "";
            //se revisa si el dato existe o si esta nulll, si no esta nulo entra
            if(request.getParameter("txtNombreCliente")!= null && 
                    !request.getParameter("txtNombreCliente").equals("")){
                //se colocan los datos en la entidad factura
                EntidadFactura.setIdCliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                EntidadFactura.setIdFactura(Integer.parseInt(request.getParameter("txtnumFactura")));
                //se crea ek formato de fecha
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString  = request.getParameter("txtFechaFactura");
                Date fecha = formato.parse(fechaString);
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                //se coloca la fecha
                EntidadFactura.setFecha(fechasql);
                EntidadFactura.setIdCliente(Integer.parseInt(request.getParameter("txtIdCliente")));
                EntidadFactura.setEstado("Pendiente");
                //se revisa si existen esos datos
                if(!(request.getParameter("txtdescripcion").equals("")) &&
                        !(request.getParameter("txtcantidad").equals("")) &&
                        !(request.getParameter("txtprecio").equals(""))){
                    //se colocan los demas datos a la entidad factura
                    EntidadDetalle.setIdFactura(-1);
                    EntidadDetalle.setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
                    EntidadDetalle.setCantidad(Integer.parseInt(request.getParameter("txtcantidad")));
                    EntidadDetalle.setPrecio(Double.parseDouble(request.getParameter("txtprecio")));
                    //se obtiene el resultado del insertar y el mensaje
                    resultado = LogicaFactura.Insertar(EntidadFactura, EntidadDetalle);
                    mensaje = LogicaFactura.getMensaje();
                    
                }else{
                    //dado caso que, se modifica
                    resultado = LogicaFactura.ModificarFactura(EntidadFactura);
                }
                //se hace un response hacia la pagina
                response.sendRedirect("Frm_facturar.jsp?msgFac="+mensaje+"&txtnumFactura="+resultado);
                
            }else{
                
                response.sendRedirect("Frm_facturar.jsp?txtnumFactura="+
                        request.getParameter("txtnumFactura"));
                
            }
            
        }catch(Exception ex){
            out.print(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
