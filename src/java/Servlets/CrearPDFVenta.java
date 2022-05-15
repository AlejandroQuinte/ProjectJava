/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Entidades.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidades.DatosPlanilla;
import Entidades.DetalleFactura;
import Entidades.Empleado;
import Entidades.Factura;
import Entidades.Producto;
import LogicaNegocio.BL_Detalle;
import LogicaNegocio.BL_Factura;
import LogicaNegocio.LNCliente;
import LogicaNegocio.LNEmpleado;
import LogicaNegocio.LNPlantilla;
import LogicaNegocio.LNProducto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alequ
 */
@WebServlet(name = "CrearPDFVenta", urlPatterns = {"/CrearPDFVenta"})
public class CrearPDFVenta extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //Metodo para crear el pdf Venta, obtiene los datos de los input y de la base de datos y los guarda en la plantilla y los envia a crear la plantilla
            String fecha="";
            
            LNPlantilla logicaPlantilla = new LNPlantilla();

            DatosPlanilla plantilla = new DatosPlanilla();
            ArrayList<DatosPlanilla> datosPlanilla = new ArrayList();

            //
            int numFactura = Integer.parseInt(request.getParameter("txtCodigoFactura"));

            BL_Detalle logicaDetalle = new BL_Detalle();
            List<DetalleFactura> datosDetalle = null;
            datosDetalle = logicaDetalle.ListarRegistros("NUM_FACTURA=" + numFactura);

            LNCliente logicaC = new LNCliente();
            Cliente cliente = new Cliente();

            LNProducto logicaProducto = new LNProducto();
            Producto producto = new Producto();

            LNEmpleado logicaEmpleado = new LNEmpleado();
            Empleado empleado = new Empleado();

            BL_Factura logicaFactura = new BL_Factura();
            Factura facturaC = new Factura();
            //List<Factura> datosFacturas = logicaFactura.ListarRegistros("");

            for (DetalleFactura registro : datosDetalle) {
                plantilla = new DatosPlanilla();
                //obtenemos mas informacion de la base de datos uniendo todas las tablas
                empleado = logicaEmpleado.ObtenerRegistro("ID_Empleado=" + 1);
                producto = logicaProducto.ObtenerRegistro("ID_Producto=" + registro.getIdProducto());
                facturaC = logicaFactura.ObtenerRegistro("NUM_FACTURA=" + registro.getIdFactura());
                cliente = logicaC.ObtenerRegistro("ID_CLIENTE=" + facturaC.getIdCliente());

                //Guardamos en variables para que sea mas facil de leer
                int codigoVenta = registro.getIdFactura();
                String nombreCliente = cliente.getNombre();
                String nombreProducto = registro.getNombreProducto();
                String nombreEmpleado = empleado.getNombre();
                int cantidadCompra = registro.getCantidad();
                double Precio = producto.getPrecioVenta();
                String marca = producto.getMarca();
                String fechaCompra = String.valueOf(facturaC.getFecha());
                
                //Agregamos los datos a la plantilla
                plantilla.setNombreCliente(nombreCliente);
                plantilla.setNombreProducto(nombreProducto);
                plantilla.setCantidadCompra(cantidadCompra);
                plantilla.setPrecioProducto((float)Precio);
                plantilla.setNombreEmpleado(nombreEmpleado);
                plantilla.setMarcaProducto(marca);

                datosPlanilla.add(plantilla);
                
                 fecha = fechaCompra;
                
            }
            //

           
            String nombreArchivo = plantilla.getFechaCompra() + plantilla.getNombreCliente() + plantilla.getPrecioProducto();

            String ubicacion = "C:\\Users\\alequ\\Desktop\\ProyectoF3\\PDF\\" + nombreArchivo + ".pdf";

            logicaPlantilla.Plantilla(ubicacion, fecha, datosPlanilla);
            logicaPlantilla.crearPlantillaVenta(ubicacion, fecha, datosPlanilla);

            String respuesta = ubicacion;

            response.sendRedirect("FrmFacturarPDFVenta.jsp?create=" + respuesta);// + "?txtnumFacturar=" + entidad.getIdProducto()sendre
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
