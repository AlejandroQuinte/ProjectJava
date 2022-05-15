/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Producto;
import LogicaNegocio.LNProducto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alequ
 */
public class CrearModificarProducto extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            
            //Metodo para crear o modificar producto si el Producto tiene un id mayor a 0 se modifica sino se crea
            
            LNProducto logica = new LNProducto();
            Producto producto = new Producto();
            int resultado;

            
            producto.setIdProducto(Integer.parseInt(request.getParameter("txtCodigo")));
            producto.setMarca(new String(request.getParameter("txtMarca").getBytes("ISO-8859-1"), "UTF-8"));
            producto.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));
            producto.setCantidadMedida(Integer.parseInt(request.getParameter("txtCantidadMedida")));
            producto.setTipoMedida(new String(request.getParameter("seleccionarMedida").getBytes("ISO-8859-1"), "UTF-8"));
            producto.setPrecioCompra(Float.parseFloat(request.getParameter("txtPrecioCompra")));
            producto.setPrecioVenta(Float.parseFloat(request.getParameter("txtPrecioVenta")));
            producto.setTipoMoneda(new String(request.getParameter("seleccionarMoneda").getBytes("ISO-8859-1"), "UTF-8"));
            producto.setCantidadProducto(Integer.parseInt(request.getParameter("txtCantidadProducto")));
            
 
            if (producto.getIdProducto() > 0) {
                resultado = logica.Modificar(producto);

            } else {
                resultado = logica.Insertar(producto);

            }

            response.sendRedirect("FrmListarProductos.jsp");

        } catch (Exception ex) {
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
