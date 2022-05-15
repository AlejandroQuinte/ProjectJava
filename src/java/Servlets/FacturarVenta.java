package Servlets;
 
import Entidades.Venta; 
import LogicaNegocio.LNVenta;
import java.io.IOException;
import java.io.PrintWriter; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Facturar", urlPatterns = {"/Facturar"})
public class FacturarVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            LNVenta logica = new LNVenta();
            Venta entidad = new Venta();

            int resultado;
            String mensaje = "";

            if (request.getParameter("txtNombreCliente") != null && !request.getParameter("txtNombreCliente").equals("")) {

               //se obtiene los datos una vez que se revisa si el nombre no esta vacio o nullo
                entidad.setIdProducto(Integer.parseInt(request.getParameter("txtidProducto")));
                entidad.setIdEmpleado(1); 
                entidad.setIdCliente(Integer.parseInt(request.getParameter("txtidCliente")));
                entidad.setCantidadVentidad(Integer.parseInt(request.getParameter("txtCantidad")));
                //se hace el formato de fecha y se coloca la fecha de venta
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaString = request.getParameter("txtFechaFactura");
                Date fecha = formato.parse(fechaString);
                java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                entidad.setFechaVenta(fechasql);
                //se insertan los datos
                resultado = logica.Insertar(entidad);
                mensaje = logica.getMensaje();
                //se hace un reenvio hacia la otra pagina frmFacturasCompras
                response.sendRedirect("FrmFacturarVentas.jsp?msgFac=" +mensaje+ "&txtnumFacturar=" + entidad.getIdProducto());// + "?txtnumFacturar=" + entidad.getIdProducto()

            } else {
                response.sendRedirect("FrmFacturar.jsp?txtnumFacturar=" + request.getParameter("txtnumFactura"));
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FacturarVenta.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FacturarVenta.class.getName()).log(Level.SEVERE, null, ex);
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
