/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Proveedor;
import LogicaNegocio.LNProveedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alequ
 */
@WebServlet(name = "CrearModificarProveedor", urlPatterns = {"/CrearModificarProveedor"})
public class CrearModificarProveedor extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            //Metodo para crear o modificar Proveedor si el Proveedor tiene un id mayor a 0 se modifica sino se crea
            LNProveedor logica = new LNProveedor();
            Proveedor proveedor = new Proveedor();
            int resultado;
            proveedor.setIdProveedor(Integer.parseInt(request.getParameter("txtCodigo")));
            proveedor.setNombreProveedor(new String (request.getParameter("txtNombre").getBytes("ISO-8859-1"),"UTF-8"));
            proveedor.setApellidoProveedor(request.getParameter("txtApellido"));
            proveedor.setMarca(request.getParameter("txtMarca"));
            proveedor.setTelefono(Integer.parseInt(request.getParameter("txtTelefono")));
            
            
            if(proveedor.getIdProveedor()>0){
                resultado = logica.Modificar(proveedor);
                
            }else{
                resultado=logica.Insertar(proveedor);
                
            }
            response.sendRedirect("FrmListarProveedores.jsp");
            
            
        }catch(Exception ex){
            out.println(ex.getMessage());
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
