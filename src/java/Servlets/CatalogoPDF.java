/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.DatosPlanilla;
import Entidades.Producto;
import LogicaNegocio.LNPlantilla;
import LogicaNegocio.LNProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alequ
 */
@WebServlet(name = "CatalogoPDF", urlPatterns = {"/CatalogoPDF"})
public class CatalogoPDF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            //Este servlet carga todos los productos de la base de datos y los guarda en una lista de productos, para que asi puedan ver el catalogo de productos, se guarda en Descargars
            //de la pc
            LNProducto logica = new LNProducto();
            LNPlantilla logicaPlantilla = new LNPlantilla();

            Producto productos = new Producto();
            DatosPlanilla planilla;

            ArrayList<Producto> ListaProductos;
            ArrayList<DatosPlanilla> datosPlanilla = new ArrayList();
            ListaProductos = logica.ListarRegistro("");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = dateFormat.format(date);

            for (Producto catalogo : ListaProductos) {
                planilla = new DatosPlanilla();
                planilla.setCodigo(catalogo.getIdProducto());
                planilla.setNombreProducto(catalogo.getNombre());
                planilla.setMarcaProducto(catalogo.getMarca());
                planilla.setPrecioProducto(catalogo.getPrecioVenta());

                datosPlanilla.add(planilla);
            }

            String ubicacion = "C:\\Users\\alequ\\Downloads\\" + "Catalogo" + ".pdf";

            logicaPlantilla.Plantilla(ubicacion, today, datosPlanilla);
            logicaPlantilla.crearCatalogo(ubicacion, today, datosPlanilla);
            
            
            response.sendRedirect("index.html?create=" + ubicacion);// + "?txtnumFacturar=" + entidad.getIdProducto()sendre
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CrearPDFVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CrearPDFVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
