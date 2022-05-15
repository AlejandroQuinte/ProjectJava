package LogicaNegocio;

import AccesoDatos.ADPlantilla;
import Entidades.DatosPlanilla;
import java.util.ArrayList;

/**
 *
 * @author alequ
 */
public class LNPlantilla {

    //metodo para tener acceso a datos y Plantilla
    public void Plantilla(String nombreArchivo, String fecha, ArrayList<DatosPlanilla> compras) {

        ADPlantilla logico = new ADPlantilla();

        try {
            logico.Plantilla(nombreArchivo, fecha, compras);
        } catch (Exception e) {
            throw e;
        }

    }

    //metodo para tener acceso a datos y crearPlantilla
    public void crearPlantilla(String nombreArchivo, String fecha, ArrayList<DatosPlanilla> compras) throws Exception {
        ADPlantilla logico = new ADPlantilla();

        try {
            logico.crearPlantilla(nombreArchivo, fecha, compras);
        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para tener acceso a datos y crearPlantillaVenta
    public void crearPlantillaVenta(String nombreArchivo, String fecha, ArrayList<DatosPlanilla> compras) throws Exception {
        ADPlantilla logico = new ADPlantilla();

        try {
            logico.crearPlantillaVenta(nombreArchivo, fecha, compras);
        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para tener acceso a datos y crearPlantillaVenta
    public void crearCatalogo(String nombreArchivo, String fecha, ArrayList<DatosPlanilla> compras) throws Exception {
        ADPlantilla logico = new ADPlantilla();

        try {
            logico.crearCatalogo(nombreArchivo, fecha, compras);
        } catch (Exception e) {
            throw e;
        }
    }

    //metodo para tener acceso a datos y crearPlantillaVenta
    public void crearReporteVentas(String nombreArchivo, String fecha, ArrayList<DatosPlanilla> compras) throws Exception {
        ADPlantilla logico = new ADPlantilla();

        try {
            logico.crearReporteVentas(nombreArchivo, fecha, compras);
        } catch (Exception e) {
            throw e;
        }
    }
    //metodo para tener acceso a datos y crearPlantillaVenta

    public void crearReporteVentasP(String nombreArchivo, String fecha, ArrayList<DatosPlanilla> compras) throws Exception {
        ADPlantilla logico = new ADPlantilla();

        try {
            logico.crearReporteVentasP(nombreArchivo, fecha, compras);
        } catch (Exception e) {
            throw e;
        }
    }

}
