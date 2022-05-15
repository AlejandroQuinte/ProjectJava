/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Compra;
import Entidades.Venta;
import LogicaNegocio.LNCompra;
import LogicaNegocio.LNVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alequ
 */
@WebServlet(name = "FacturarCompra", urlPatterns = {"/FacturarCompra"})
public class FacturarCompra extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            LNCompra logica = new LNCompra();
            Compra entidad = new Compra();

            int resultado;
            String mensaje = "";

            if (request.getParameter("txtNombreProveedor") != null && !request.getParameter("txtNombreProveedor").equals("")) {

                //se obtiene los datos una vez que se revisa si el nombre del proveedor no esta vacio o nullo
                entidad.setIdProducto(Integer.parseInt(request.getParameter("txtidProducto")));
                entidad.setIdEmpleado(1); //ponemos el mismo valor del empleado
                entidad.setIdVendedor(Integer.parseInt(request.getParameter("txtidProveedor")));
                entidad.setCantidadCompra(Integer.parseInt(request.getParameter("txtCantidad")));
                //se hace el formato de fecha y se coloca la fecha de ompra
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString = request.getParameter("txtFechaFactura");
                Date fecha = formato.parse(fechaString);
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                entidad.setFechaCompra(fechasql);
                //se insertan los datos
                resultado = logica.Insertar(entidad);
                mensaje = logica.getMensaje();
                 
                //se hace un reenvio hacia la otra pagina frmFacturasCompras
                response.sendRedirect("FrmFacturarCompras.jsp?msgFac=" +mensaje+ "&txtnumFacturar=" + entidad.getIdProducto());// + "?txtnumFacturar=" + entidad.getIdProducto()

            } else {
                response.sendRedirect("FrmFacturarCompras.jsp?txtnumFacturar=" + request.getParameter("txtnumFactura"));
            }

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
