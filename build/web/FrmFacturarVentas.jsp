<%@page import="Entidades.Empleado"%>
<%@page import="LogicaNegocio.LNEmpleado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidades.Cliente"%>
<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>  
<%@page import="Entidades.Venta"%>
<%@page import="LogicaNegocio.LNVenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidades.Producto" %>
<%@page import="LogicaNegocio.LNProducto" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Agregar productos</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/boostrap-datapicker/css/boostrap-datapicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/>
        <title>Facturacion</title>
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
                <div  class="col-10"><h1><h1>Facturacion Ventas</h1></div>
            </div>

            <%
                int numFactura = -1;
                double total = 0;
                Venta entidad;
                LNVenta logica = new LNVenta();
                List<Venta> datos = null;

                Cliente cliente;
                LNCliente logicaCliente = new LNCliente();

                if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    entidad = logica.ObtenerRegistro("ID_Venta=" + numFactura);
                } else {
                    entidad = new Venta();
                    entidad.setIdVenta(-1);
                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    entidad.setFechaVenta(fechasql);
                }

                cliente = logicaCliente.ObtenerRegistro("id_Cliente=" + entidad.getIdCliente());


            %>
            <br/>

            <form action="Facturar" method="post" id="form_AgregarModificar">

                <div class="form-group float-right">

                    <div class="input-group">
                        <label for="txtnumFactura" class="form-control">NUM.Factura</label>
                        <input type="text" name="txtnumFactura" id="txtnumFactura" class="form-control"
                               value="<%= entidad.getIdVenta()%>" readonly/>
                    </div>  

                    <div class="input-group">
                        <label for="txtFechaFactura" class="form-control">Fecha</label>

                        <input type="text" name="txtFechaFactura" id="txtFechaFactura" class="datepicker form-control"
                               value="<%= entidad.getFechaVenta()%>" readonly/>
                    </div>  
                </div>
                <br/>

                <div class="form-group">

                    <div class="input-group"> 
                        &nbsp; &nbsp;
                        <input type="hidden" name="txtidCliente" id="txtidCliente" class="form-control"
                               value="<%= entidad.getIdCliente()%>" readonly/>

                        <input type="text" name="txtNombreCliente" id="txtNombreCliente" class="form-control"
                               value="<%= cliente.getNombre()%>" readonly placeholder="Seleccione un cliente"/>
                        <br> 
                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarCliente"><i class="fas fa-search"></i></a>
                        <br> 

                    </div>   
                </div>  
                <hr/>
                <div class="form-group">
                    <div class="input-group">

                        <input type="hidden" name="txtidProducto" id="txtidProducto" class="form-control" value="" readonly/>
                        <div>
                            <label>Nombre Producto</label>
                            <input type="text" name="txtDescripcion" id="txtDescripcion" class="form-control" value="" readonly placeholder="Seleccione un producto"/>

                        </div> 
                        <div>
                            <label>Buscar</label>
                            <br/>
                            <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto"><i class="fas fa-search"></i></a> 
                        </div>
                        &nbsp;&nbsp;&nbsp;
                        <div>
                            <label>Cantidad a comprar</label> 
                            <input type="number" min="1" name="txtCantidad" id="txtCantidad" class="form-control" value="" placeholder="Cantidad"/>
                        </div> 
                        &nbsp;&nbsp;&nbsp;
                        <div>
                            <label>Precio</label> 
                            <input type="number" name="txtPrecio" id="txtPrecio" class="form-control" value="" readonly="true" placeholder="Precio"/>
                        </div>
                        &nbsp;&nbsp;&nbsp;
                        <div>

                            <label>Cantidad Inventario</label> 
                            <input type="number" name="txtExistencia" id="txtExistencia" class="form-control" value="" readonly="Existencia" placeholder="Existencia"/>
                        </div>

                    </div>  
                </div>




                <div class="form-group">

                    <input type="submit" name="Guardar" id="btnGuardar" value="Agregar y Guardar" class="btn btn-primary"/> 
                    <div class="float-right">
                        <a href="FrmFacturarPDFVenta.jsp" class="btn btn-success">Realizar Facturacion</a> 
                        <a href="index.html" class="btn btn-secondary">Regresar</a>
                    </div>
                </div>  

            </form>
            <%
                if (request.getParameter("msgFac") != null && !request.getParameter("msgFac").equals("")) {

            %>
            <span class="error"><%=new String(request.getParameter("msgFac").getBytes("ISO-8859-1"), "UTF-8")%></span>
            <%
                }//fin del if

            %>
            <br><!-- comment -->


            <h5>Detalle Factura</h5>
            <table class="tablaVentas">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre Producto</th>
                        <th>Cantidad Comprada</th>
                        <th >Nombre Cliente</th>
                        <th >Nombre Empleado</th> 
                        <th >Precio Total</th> 
                    </tr>
                </thead>
                <tbody>
                    <%                        LNVenta logica1 = new LNVenta();
                        List<Venta> datosVentas = null;

                        datosVentas = logica1.ListarRegistro("");

                        for (Venta registro : datosVentas) {
                    %>

                    <tr>
                        <%
                            LNProducto logicaProducto = new LNProducto();
                            Producto producto = new Producto();

                            LNEmpleado logicaEmpleado = new LNEmpleado();
                            Empleado empleado = new Empleado();

                            producto = logicaProducto.ObtenerRegistro("ID_Producto=" + registro.getIdProducto());

                            cliente = logicaCliente.ObtenerRegistro("ID_Cliente=" + registro.getIdCliente());

                            empleado = logicaEmpleado.ObtenerRegistro("ID_Empleado=" + registro.getIdEmpleado());

                            String NombreProducto = new String(producto.getNombre().getBytes("ISO-8859-1"), "UTF-8");
                            String NombreCliente = cliente.getNombre();
                            String NombreEmpleado = empleado.getNombre();

                            double cantidadTotal = registro.getCantidadVentidad() * producto.getPrecioVenta();

                        %>


                        <td><%=registro.getIdVenta()%></td>
                        <td><%= NombreProducto%></td> 
                        <td><%= registro.getCantidadVentidad()%></td> 
                        <td><%=NombreCliente%></td>
                        <td><%=NombreEmpleado%></td>
                        <td><%=cantidadTotal%></td>

                    </tr>
                    <% } // fin del for 

                    %>
                </tbody>


            </table>

            <br> 




            <br><br>

        </div><!-- Container principal -->
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

        <!-- ModalCliente -->
        <div class="modal" id="buscarCliente" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Cliente</h5>
                        <button class="close" data-dismiss="modal" arial-label="Cerrar" aria-hidden="true" onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">


                        <table class="tablaClientes">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th> 
                                </tr>
                            </thead>

                            <tbody>

                                <%                                    LNCliente logicaC = new LNCliente();
                                    List<Cliente> datosC;
                                    datosC = logicaC.ListarRegistro("");

                                    for (Cliente registro : datosC) {

                                %>

                                <tr>
                                    <%int codigo = registro.getIdCliente();
                                        String nombreCliente = registro.getNombre();
                                    %>  

                                    <td><%= codigo%></td> <!-- estos no terminan con ; porque son Expresiones -->
                                    <td><%= nombreCliente%></td> 

                                    <!-- Columna adicional con botones de opciones: -->
                                    <td>
                                        <a href="#" data-dismiss="modal" onclick="SeleccionarCliente('<%=codigo%>', '<%=nombreCliente%>')">Seleccionar</a>

                                    </td>
                                </tr>

                                <%}%> <!--Cerrar la llave del FOR de JAVA
                                            La llave se cierra donde se termina la fila, la table row tr-->
                            </tbody>


                        </table>
                    </div>
                    <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                        Cancelar
                    </button>

                </div>

            </div>

        </div>
        <!-- ModalProducto -->
        <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document" >
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Producto</h5>
                        <button class="close" data-dismiss="modal" arial-label="Cerrar" aria-hidden="true" onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="tablaProductos">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Descripcion</th>
                                    <th>Precio</th>
                                    <th>Seleccionar</th> 
                                </tr>
                            </thead>

                            <tbody>

                                <%

                                    LNProducto logicaP = new LNProducto();
                                    List<Producto> datos1;
                                    datos1 = logicaP.ListarRegistro("");

                                    for (Producto registro : datos1) {

                                %>

                                <tr>
                                    <%int codigop = registro.getIdProducto();
                                        String nombreProducto = registro.getNombre();
                                        double precio = registro.getPrecioVenta();
                                        int existencia = registro.getCantidadProducto();
                                    %>  

                                    <td><%= codigop%></td> <!-- estos no terminan con ; porque son Expresiones -->
                                    <td><%= nombreProducto%></td> 
                                    <td><%= precio%></td> 

                                    <!-- Columna adicional con botones de opciones: -->
                                    <td>
                                        <a href="#" data-dismiss="modal" onclick="SeleccionarProducto('<%=codigop%>', '<%=nombreProducto%>', '<%=precio%>', '<%=existencia%>')">Seleccionar</a>

                                    </td>
                                </tr>

                                <%}%> <!--Cerrar la llave del FOR de JAVA
                                            La llave se cierra donde se termina la fila, la table row tr-->
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

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

 


        <script>
                            $(document).ready(function () {

                                $('.datepicker').datepicker({
                                    format: 'yyyy-mm-dd',
                                    autoclose: true,
                                    lenguage: 'es'
                                });
                                $('.tablaClientes').dataTable({
                                    "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "lenguage": {
                                        "info": "Pagina _PAGE_ de _PAGES_",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecord": "No se encuentran registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lenghtMenu": "Mostrar _MENU_ Registro",
                                        "paginate": {
                                            "first": "Primero",
                                            "last": "Ultimo",
                                            "next": "Siguiente",
                                            "previous": "Anterior",
                                        }
                                    }
                                });
                                $('.tablaProductos').dataTable({
                                    "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "lenguage": {
                                        "info": "Pagina _PAGE_ de _PAGES_",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecord": "No se encuentran registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lenghtMenu": "Mostrar _MENU_ Registro",
                                        "paginate": {
                                            "first": "Primero",
                                            "last": "Ultimo",
                                            "next": "Siguiente",
                                            "previous": "Anterior",
                                        }
                                    }
                                });
                                $('.tablaVentas').dataTable({
                                    "lenghtMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "lenguage": {
                                        "info": "Pagina _PAGE_ de _PAGES_",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecord": "No se encuentran registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lenghtMenu": "Mostrar _MENU_ Registro",
                                        "paginate": {
                                            "first": "Primero",
                                            "last": "Ultimo",
                                            "next": "Siguiente",
                                            "previous": "Anterior",
                                        }
                                    }
                                });
                                $("#form_AgregarModificar").validate({
                                    // Reglas que deseamos personalizar:
                                    rules: {
                                        // Si no definimos estas reglas, solamente se aplicarán las reglas que estén definidas
                                        // dentro de cada input (por ejemplo el input se definió como requiered)

                                        txtNombreCliente: {required: true, maxlength: 50},
                                        txtDescripcion: {required: true, maxlength: 50},
                                        // el tamaño anterior podría ser cualquier otro, entre 8 y 11 es sólo un ejemplo

                                        txtCantidad: {required: true, maxlength: 50},
                                        // Nota: Para determinar estos tamaños debemos verificar las restricciones de nuestra BD


                                    },
                                    // Mensajes que deseamos personalizar: 
                                    messages: {
                                        txtNombreCliente: "El campo de Nombre Cliente es obligatorio",
                                        txtDescripcion: "El campo de Descripcion es obligatorio",
                                        txtCantidad: "El campo de Cantidad es obligatorio"
                                    },
                                    errorElement: 'span'
                                            // Indicamos que si muestar mensajes de error, los muestre dentro de un span, con esto 
                                            // de forma automática si se produce un error se va a generar un SPAN, y por eso fue 
                                            // que creamos un ESTILO para indicar que el color de ese SPAN fuera rojo en una letra
                                            // un poco más pequeña. 
                                });
                            }); //final del function

                            //Metodo para guardar los datos en un arreglo y mostrarlo en la tabla detalle factura
                            function GuardarArreglo() {

                            }

                            function SeleccionarCliente(idCliente, nombreCliente) {
                                $("#txtidCliente").val(idCliente);
                                $("#txtNombreCliente").val(nombreCliente);
                            }

                            function SeleccionarProducto(idProducto, Descripcion, Precio, Existencia) {
                                $("#txtidProducto").val(idProducto);
                                $("#txtDescripcion").val(Descripcion);
                                $("#txtPrecio").val(Precio);
                                $("#txtExistencia").val(Existencia);
                                $("#txtCantidad").focus();
                            }

                            function Limpiar() {
                                $("#txtidCliente").val("");
                                $("#txtNombreCliente").val("");
                            }
                            function LimpiarProducto() {
                                $("#txtidProducto").val("");
                                $("#txtDescripcion").val("");
                                $("#txtPrecio").val("");
                                $("#txtExistencia").val("");
                            }

        </script>

    </body>
</html>
