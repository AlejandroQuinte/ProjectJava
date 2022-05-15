/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.DatosPlanilla;
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
@WebServlet(name = "ReporteEmpleado", urlPatterns = {"/ReporteEmpleado"})
public class ReporteEmpleado extends HttpServlet {

     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //servlet en el que obtenemos los datos de las ventas o el nombre del empleado y la fecha, 
            String nombreEmpleado = request.getParameter("txtNombreEmpleado");
            String fecha = request.getParameter("txtFecha");

            LNVenta logica = new LNVenta();
            ArrayList<VentasMesE> datos;
            //hacemos una busqueda de ese empleado con el metodo, despues una vez obtenido los datos se ahce una revision si esta vacio es que no se obtuvo nada
            datos = logica.ListarRegistroEmpleado("", fecha, nombreEmpleado);

            //sino esta vacio se entra y crea un objeto plantilla en donde metemos los datos del empleado nombre etc y del producto y lo enviamos al metodo
            //de crear reporte ventas
            if (!datos.isEmpty()) {
                LNPlantilla logicaPlantilla = new LNPlantilla();

                DatosPlanilla plantilla;
                ArrayList<DatosPlanilla> datosPlanilla = new ArrayList();

                for (VentasMesE registro : datos) {
                    plantilla = new DatosPlanilla();
                    plantilla.setCodigo(registro.getIdVenta());
                    plantilla.setMarcaProducto(registro.getMarca());
                    plantilla.setNombreProducto(registro.getNombre());
                    plantilla.setNombreEmpleado(registro.getNombreEmpleado());
                    plantilla.setCantidadCompra(registro.getCantidadVendida());
                    plantilla.setFechaCompra(registro.getFechaVenta());
                    datosPlanilla.add(plantilla);
                }

                String nombreArchivo = "ReporteEmpleado";

                String ubicacion = "C:\\Users\\alequ\\Desktop\\ProyectoF3\\PDF\\" + nombreArchivo + ".pdf";

                logicaPlantilla.Plantilla(ubicacion, fecha, datosPlanilla);
                logicaPlantilla.crearReporteVentas(ubicacion, fecha, datosPlanilla);

                String respuesta = ubicacion;

                response.sendRedirect("ReporteVentas.jsp?create=" + respuesta);// + "?txtnumFacturar=" + entidad.getIdProducto()sendre
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
