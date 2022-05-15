<%-- 
    Document   : Frm_facturar
    Created on : 9/05/2022, 11:34:14 AM
    Author     : Steven
--%>

<%@page import="Servlets.Facturar"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="Entidades.*"%>
<%@page import="java.util.*"%>
<%@page import="LogicaNegocio.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/DataTables-1.10.21/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facturar</title>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
                <div class="container">
                    <a class="navbar-brand" href="index.html">Sistema Facturación <i class="fas fa-tasks"></i></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="navbar-collapse collapse d-sm-inline-flex flex-sm-row-reverse">
                        <ul class="navbar-nav flex-grow-1">
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="index.html">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarProductos.jsp">Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarClientes.jsp">Clientes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarProveedores.jsp">Proveedores</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="Frm_ListarFacturas.jsp">Facturación</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmFacturarCompras.jsp">Comprar Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="ReporteVentas.jsp">Reporte de Ventas</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>




        <div class="container">
            <div class="row">
                <div class="col-10">
                    <h1>Facturación</h1>
                </div>
            </div>
            <%
                int numFactura = -1;
                double total = 0;
                Factura EntidadFactura;
                BL_Factura logicaFactura = new BL_Factura();
                BL_Detalle logicaDetalle = new BL_Detalle();
                List<DetalleFactura> DatosDetalles = null;

                String nombreCli = "";

                if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    EntidadFactura = logicaFactura.ObtenerRegistro("Num_Factura= " + numFactura);
                    DatosDetalles = logicaDetalle.ListarRegistros("Num_Factura= " + numFactura);

                    nombreCli = EntidadFactura.getNombreCliente();
                } else {
                    EntidadFactura = new Factura();
                    EntidadFactura.setIdFactura(-1);
                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    EntidadFactura.setFecha(fechasql);
                }
            %>
            <br/>
            <form action="FacturarD" method="post" id="Frm_Facturar">
                <div class="form-group float-right">

                    <div class="input-group">
                        <label for="txtnumFactura" class="form-control">Num. Factura</label>
                        <input type="text" id="txtnumFactura" name="txtnumFactura" value="<%=EntidadFactura.getIdFactura()%>"
                               readonly class="form-control"/>
                    </div>


                    <div class="input-group">
                        <label for="txtFechaFactura" class="form-control">Fecha</label>
                        <input type="text" id="txtFechaFactura" name="txtFechaFactura" readonly value="<%=EntidadFactura.getFecha()%>"
                               required class="datepicker form-control"/>
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdCliente" name="txtIdCliente" value="<%=EntidadFactura.getIdCliente()%>"
                               readonly="" class="form-control"/>
                        <input type="text" id="txtNombreCliente" name="txtNombreCliente" value="<%=nombreCli%>"
                               readonly="" class="form-control" placeholder="Seleccione un cliente"/> &nbsp; &nbsp;
                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarCliente"><i class="fas fa-search"></i></a>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdProducto" name="txtIdProducto" value="" readonly class="form-control"/>
                        <input type="text" id="txtdescripcion" name="txtdescripcion" value="" class="form-control" readonly="true"
                               placeholder="Seleccione un producto"/> &nbsp; &nbsp;
                        <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                            <i class="fas fa-search"></i></a>&nbsp; &nbsp;
                        <input type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control"
                               placeholder="Cantidad"/> &nbsp; &nbsp;
                        <input type="number" id="txtprecio" name="txtprecio" value="" class="form-control"
                               placeholder="Precio" readonly="true"/> &nbsp; &nbsp;
                        <input type="number" id="txtexistencia" name="txtexistencia" value="" class="form-control"
                               placeholder="Existencia" readonly="true"/> &nbsp; &nbsp;
                    </div>
                </div>
                <br/>
                <div class="form-group">
                    <input type="submit" name="Guardar" id="btnGuardar" value="Agregar y Guardar" class="btn btn-primary"/>
                    <div class="float-right">
                        <input type="button" id="BtnCancelar" value="Realizar Facturación"
                               onclick="location.href = 'CancelarFactura?txtnumFactura=' +<%=EntidadFactura.getIdFactura()%>"
                               class="btn btn-success"/>&nbsp;&nbsp;
                        <a href="Frm_ListarFacturas.jsp" class="btn btn-secondary">Regresar</a>
                    </div>
                </div>
            </form>
            <h5>Detalle Factura</h5>
            <table id="DetalleFactura" class="table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descripción</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (DatosDetalles != null) {
                            for (DetalleFactura registroDetalle : DatosDetalles) {

                    %>
                    <tr>
                        <%  int numfactura = registroDetalle.getIdFactura();
                            int codigop = registroDetalle.getIdProducto();
                            String descripcion = new String(registroDetalle.getNombreProducto().getBytes("ISO-8859-1"), "UTF-8");
                            int cantidad = registroDetalle.getCantidad();
                            double precioV = registroDetalle.getPrecio();
                            total += (cantidad * precioV);
                        %>
                        <td><%=codigop%></td>
                        <td><%=descripcion%></td>
                        <td><%=cantidad%></td>
                        <td><%=precioV%></td>
                        <td><%=cantidad * precioV%></td>
                        <td>
                            <a href="EliminarDetalle?idproducto=<%=codigop%>&idfactura=<%=numfactura%>">
                                <i class="fas fa-trash-alt"></i>
                            </a>
                        </td>
                    </tr>
                    <%}
                        }%>
                </tbody>
            </table>
            <div class="mb-5">
                <div class="float-right">

                    <%double descuento = 0;
                        double iva = total / 100 * 13;
                    %>
                    <p class="txt-danger h5">SubTotal = <%= (total)%></p>
                    <%
                        if (total >= 10000) {
                            descuento = (total / 100 * 5);
                            iva = (total - descuento) / 100 * 13;
                    %>
                    <p class="txt-danger h5">Descuento 5% = <%=descuento%></p> 
                    <%}%>
                    <p class="txt-danger h5">IVA 13% = <%= iva%></p>

                    <p class="txt-danger h5">Total = <%= (total - descuento) + iva%></p>
                    
                </div>
            </div>
            <br><br>
            <%
                if (request.getParameter("msgFac") != null) {
                    out.print("<p class='text-danger'>" + new String(request.getParameter("msgFac").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                }
            %>

        </div>



        <br> 
        <footer class="border-top"> 
            <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white  box-shadow mb-3">
                <div class="container"> 
                    <div class="navbar-collapse collapse d-sm-inline-flex flex-sm-row-reverse">
                        <ul class="navbar-nav flex-grow-1">
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="index.html">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarProductos.jsp">Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarClientes.jsp">Clientes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarProveedores.jsp">Proveedores</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmFacturarVentas.jsp">Facturación</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmFacturarCompras.jsp">Comprar Productos</a>
                            </li>
                        </ul>
                    </div>
                    <div class="">
                        &copy; Proyecto Web Alejandro Quintero
                    </div>
                </div> 
            </nav>
        </footer>
        <br>



        <!<!-- modal -->
        <div class="modal fade" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="tituloVentana">Buscar Cliente</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden='true'
                                onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tablaClientes">
                            <thead>
                                <tr>
                                    <td>Código</td>
                                    <td>Nombre</td>
                                    <td>Seleccionar</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNCliente logicaClientes = new LNCliente();
                                    List<Cliente> datosClientes;
                                    datosClientes = logicaClientes.ListarRegistro("");
                                    for (Cliente registroC : datosClientes) {
                                %>
                                <tr>
                                    <% int codigoCliente = registroC.getIdCliente();
                                        String nombreCliente = registroC.getNombre();%>
                                    <td><%=codigoCliente%></td>
                                    <td><%=nombreCliente%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarCliente('<%=codigoCliente%>', '<%=nombreCliente%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <!-- fin body modal-->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>    

        <!-- fin modal Clientes -->
        <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Producto</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden='true'
                                onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tablaProductos">
                            <thead>
                                <tr>
                                    <td>Código</td>
                                    <td>Descripción</td>
                                    <td>Precio</td>
                                    <td>Seleccionar</td>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    LNProducto logicaProducto = new LNProducto();
                                    List<Producto> datosProductos;
                                    datosProductos = logicaProducto.ListarRegistro("");
                                    for (Producto registroP : datosProductos) {
                                %>
                                <tr>
                                    <% int codigoProducto = registroP.getIdProducto();
                                        double precio = registroP.getPrecioVenta();
                                        double existencia = registroP.getCantidadProducto();
                                        String descripcionProducto = registroP.getNombre();%>
                                    <td><%=codigoProducto%></td>
                                    <td><%=descripcionProducto%></td>
                                    <td><%=precio%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarProducto('<%=codigoProducto%>', '<%=descripcionProducto%>',
                                                           '<%=precio%>', '<%=existencia%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarProducto()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- fin modal Productos -->




        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script>
                            $(document).ready(function () {

                                $('.datepicker').datepicker({

                                    format: 'yyyy-mm-dd',
                                    autoclose: true,
                                    language: 'es'
                                });

                                $("#tablaClientes").DataTable({
                                    "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "language": {
                                        "info": "Página_PAGE_de_PAGES",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecords": "No se encuentran Registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lengthMenu": "Mostrar_MENU_Registros",
                                        "paginate": {

                                            "first": "Primero",
                                            "last": "Último",
                                            "next": "Siguiente",
                                            "previous": "Anterior"
                                        }
                                    }
                                });
                                $('#tablaProductos').DataTable({
                                    "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "language": {
                                        "info": "Página_PAGE_de_PAGES",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecords": "No se encuentran Registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lengthMenu": "Mostrar_MENU_Registros",
                                        "paginate": {

                                            "first": "Primero",
                                            "last": "Último",
                                            "next": "Siguiente",
                                            "previous": "Anterior"
                                        }
                                    }
                                });
                            });
                            function SeleccionarCliente(idCliente, nombreCliente) {

                                $("#txtIdCliente").val(idCliente);
                                $("#txtNombreCliente").val(nombreCliente);
                            }

                            function SeleccionarProducto(idProducto, Descripcion, Precio, Existencia) {
                                $("#txtIdProducto").val(idProducto);
                                $("#txtdescripcion").val(Descripcion);
                                $("#txtprecio").val(Precio);
                                $("#txtexistencia").val(Existencia);
                                $("#txtcantidad").focus();
                            }

                            function Limpiar() {
                                $("#txtIdCliente").val("");
                                $("#txtNombreCliente").val("");
                            }

                            function LimpiarProducto() {
                                $("#txtIdProducto").val("");
                                $("#txtdescripcion").val("");
                                $("#txtprecio").val("");
                                $("#txtexistencia").val("");
                            }
        </script>
    </body>
</html>
