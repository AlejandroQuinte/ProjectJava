/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.DatosPlanilla;
import Entidades.VentaMesP;
import Entidades.VentasMesE;
import LogicaNegocio.LNPlantilla;
import LogicaNegocio.LNVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alequ
 */
@WebServlet(name = "ReporteProducto", urlPatterns = {"/ReporteProducto"})
public class ReporteProducto extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //metodo en el que se obtiene la fecha
            String fecha = request.getParameter("txtFecha");

            LNVenta logica = new LNVenta();
            ArrayList<VentaMesP> datos;
            datos = logica.ListarRegistroProducto("", fecha);
            //se hace un envio a resitrar productos en el que si existe esa fecha con esos productos devuevle un array lleno, sino es vacio, y si es vacio no entra  en el if
            //si entra guarda los datos en un obtjeto plantilla y los guarda y crea en el pdf
            if (!datos.isEmpty()) {
                LNPlantilla logicaPlantilla = new LNPlantilla();

                DatosPlanilla plantilla;
                ArrayList<DatosPlanilla> datosPlanilla = new ArrayList();

                for (VentaMesP registro : datos) {
                    plantilla = new DatosPlanilla();
                    plantilla.setCodigo(registro.getIdVenta());
                    plantilla.setMarcaProducto(registro.getMarca());
                    plantilla.setNombreProducto(registro.getNombre()); 
                    plantilla.setCantidadCompra(registro.getCantidadVendida());
                    plantilla.setFechaCompra(fecha);
                    datosPlanilla.add(plantilla);
                }

                String nombreArchivo = "ReporteProducto";

                String ubicacion = "C:\\Users\\alequ\\Desktop\\ProyectoF3\\PDF\\" + nombreArchivo + ".pdf";

                logicaPlantilla.Plantilla(ubicacion, fecha, datosPlanilla);
                logicaPlantilla.crearReporteVentasP(ubicacion, fecha, datosPlanilla);

                String respuesta = ubicacion;

                response.sendRedirect("ReporteVentas.jsp?createP=" + respuesta);// + "?txtnumFacturar=" + entidad.getIdProducto()sendre
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
