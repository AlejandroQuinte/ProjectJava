/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidades.DatosPlanilla;
import LogicaNegocio.LNPlantilla;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alequ
 */
@WebServlet(name = "CrearPDFCompra", urlPatterns = {"/CrearPDFCompra"})
public class CrearPDFCompra extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //Metodo para crear el pdf compra, obtiene los datos de los input y los y los guarda en la plantilla y los envia a crear la plantilla
            
            LNPlantilla logicaPlantilla = new LNPlantilla();
            
            DatosPlanilla plantilla = new DatosPlanilla();
            ArrayList<DatosPlanilla> datosPlanilla = new ArrayList();

            plantilla.setNombreProveedor(request.getParameter("txtNombreProveedor"));
            plantilla.setNombreProducto(request.getParameter("txtDescripcion"));
            plantilla.setCantidadCompra(Integer.parseInt(request.getParameter("txtCantidad")));
            plantilla.setPrecioProducto(Float.parseFloat(request.getParameter("txtPrecio")));
            plantilla.setNombreEmpleado(request.getParameter("txtNombreEmpleado"));
            plantilla.setMarcaProducto(request.getParameter("txtMarca"));

            datosPlanilla.add(plantilla);
            String fecha = request.getParameter("txtFechaFactura");
            String nombreArchivo = plantilla.getFechaCompra() + plantilla.getNombreProveedor()+ plantilla.getPrecioProducto();

            String ubicacion = "C:\\Users\\alequ\\Desktop\\ProyectoF3\\PDF\\ComprasProductos\\" + nombreArchivo + ".pdf";

            logicaPlantilla.Plantilla(ubicacion, fecha, datosPlanilla);
            logicaPlantilla.crearPlantilla(ubicacion, fecha, datosPlanilla);

            String respuesta = ubicacion;

            response.sendRedirect("FrmFacturarPDFCompra.jsp?create=" + respuesta);// + "?txtnumFacturar=" + entidad.getIdProducto()sendre
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
