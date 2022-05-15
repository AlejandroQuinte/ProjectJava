/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Factura;
import LogicaNegocio.BL_Factura;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven
 */

@WebServlet(name = "CancelarFactura", urlPatterns = {"/CancelarFactura"})
public class CancelarFactura extends HttpServlet {

     //Metodo pra cancelar la factura obteniendo el id de la factura revisando el estado en el que se encuentra y poniendole el estado cancelada, y devolviendo un nuevo id
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            BL_Factura Logica = new BL_Factura();
            int idFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
            Factura EntidadFactura = Logica.ObtenerRegistro("Num_Factura =" + idFactura);
            EntidadFactura.setEstado("Cancelada");
            Logica.ModificarEstado(EntidadFactura);
            String mensaje = Logica.getMensaje();
            response.sendRedirect("Frm_facturar.jsp?txtnumFactura="+-1+"&msgFac="+mensaje);
        } catch (Exception e) {
            out.print(e.getMessage());
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
