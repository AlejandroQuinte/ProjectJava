<%-- 
    Document   : ReporteVentas
    Created on : may 13, 2022, 5:56:14 p.m.
    Author     : alequ
--%>

<%@page import="LogicaNegocio.LNPlantilla"%>
<%@page import="Entidades.VentaMesP"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Entidades.*"%>
<%@page import="Entidades.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.LNVenta"%>
<%@page import="Entidades.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="LogicaNegocio.LNEmpleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>


        <link href="lib/boostrap-datapicker/css/boostrap-datapicker3.standalone.min.css" rel="stylesheet" type="text/css"/>

        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>



        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/> 
        


        <title>Reporte de Ventas</title>
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
            <h2>Ventas Empleados</h2>

            <form action="Cargar" method="post" id="form_AgregarModificar">

                <%
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String dateToStr = dateFormat.format(date);

                    String nombreEmpleado = "";
                    String condicion = "";
                    String fecha = dateToStr;

                    if (request.getParameter("fecha") != null && !request.getParameter("fecha").equals("")) {

                        nombreEmpleado = request.getParameter("NombreEmpleado");
                        fecha = request.getParameter("fecha");
                    }

                    LNVenta logica = new LNVenta();
                    ArrayList<VentasMesE> datos;
                    datos = logica.ListarRegistroEmpleado(condicion, fecha, nombreEmpleado);

                %>
                <div class="form-group flex">
                    <div class="form-group ">
                        <div class="input-group"><!<!-- formulario para venta empleado -->

                            <input  width="300px" type="text" name="txtNombreEmpleado" id="txtNombreEmpleado" class="form-control"
                                    value="<%=nombreEmpleado%>" readonly placeholder="Seleccione un Empleado"/>
                            <br> 
                            <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscar"><i class="fas fa-search"></i></a>

                            <input type="text" value="<%=fecha%>" name="txtFecha" id="txtFecha" class="datepicker form-control" />
                        </div>   
                    </div>
                </div> 


                <div class="input-group"> 
                    <div class="input-group w-25">
                        <input class="btn btn-primary" type="submit" class="form-control"
                               value="Buscar" readonly  />
                    </div>   


                </div> 


            </form>

            <%
                if (!datos.isEmpty()) {
            %>

            <form action="ReporteEmpleado" method="post">
                <div class="form-group flex">
                    <div class="form-group ">
                        <div class="input-group">

                            <input  width="300px" type="hidden" name="txtNombreEmpleado" id="txtNombreEmpleado" class="form-control"
                                    value="<%=nombreEmpleado%>" readonly placeholder="Seleccione un Empleado"/> 

                            <input type="hidden" value="<%=fecha%>" name="txtFecha" id="txtFecha" class="datepicker form-control" />
                        </div>   
                    </div>
                </div> 

                <div class="float-right">
                    <div class="input-group"> 
                        <div class="input-group w-25">
                            <input class="btn btn-secondary" type="submit" class="form-control"
                                   value="Crear Reporte" readonly  />

                        </div>   
                    </div> 

                </div>
            </form> 

            <% }%>
            
            <%if (request.getParameter("create") != null && !request.getParameter("create").equals("")) {
                    String ruta = new String(request.getParameter("create").getBytes("ISO-8859-1"), "UTF-8");
            %>
            <div class="float-left"> 
                <span class="error">Se a creado el archivo, se encuentra en la ubicacion: "<%=new String(request.getParameter("create").getBytes("ISO-8859-1"), "UTF-8")%>"</span>
            </div>
            <div class="float-right">
                <div class="input-group"> 

                    <div class="float-right">
                        <a class="btn btn-secondary" href="<%=ruta%>" target = "_blank">Abrir</a>  
                    </div>
                </div> 
            </div>  
            <%}%>
            <br><br>




            <table class="table">
                <thead>
                    <tr id="titulos">
                        <th>Código</th>
                        <th>Descripcion</th>
                        <th>Nombre Empleado</th>
                        <th>Cantidad Vendida</th> 
                        <th>Fecha</th> 
                    </tr>
                </thead>
                <tbody>
                    <%

                        for (VentasMesE registro : datos) {

                    %>

                    <tr>
                        <%int codigo = registro.getIdVenta();%>

                        <td><%= codigo%></td>
                        <td><%= registro.getNombre()%></td>
                        <td><%= registro.getNombreEmpleado()%></td>
                        <td><%= registro.getCantidadVendida()%></td> 
                        <td><%= registro.getFechaVenta()%></td> 
                    </tr>
                    <%}%>

                </tbody>
            </table>



            <!-- Modal  -->
            <div class="modal" id="buscar" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
                <div class="modal-dialog" role="document" >
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 id="tituloVentana">Buscar Cliente</h5>
                            <button class="close" data-dismiss="modal" arial-label="Cerrar" aria-hidden="true" onclick="Limpiar()">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">


                            <table class="tablaEmpleados">
                                <thead>
                                    <tr>
                                        <th>Código</th>
                                        <th>Nombre</th>
                                        <th>Seleccionar</th> 
                                    </tr>
                                </thead>

                                <tbody>

                                    <%
                                        LNEmpleado logicaE = new LNEmpleado();
                                        List<Empleado> datosE;
                                        datosE = logicaE.ListarRegistro("");

                                        for (Empleado registro : datosE) {

                                    %>

                                    <tr>
                                        <%int codigo = registro.getIdEmpleado();
                                            String nombreE = registro.getNombre();
                                        %>  

                                        <td><%= codigo%></td> <!-- estos no terminan con ; porque son Expresiones -->
                                        <td><%= nombreE%></td> 

                                        <!-- Columna adicional con botones de opciones: -->
                                        <td>
                                            <a href="#" data-dismiss="modal" onclick="SeleccionarEmpleado('<%=codigo%>', '<%=nombreE%>')">Seleccionar</a>

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

            <br>



            <h2>Venta Productos mes</h2>                                


            <form action="Cargar" method="post">
                <%

                %>
                <div class="form-group ">
                    <label>Selecciona una fecha</label>
                    <div class="input-group">

                        <input type="text" value="<%=fecha%>" name="txtFecha" id="txtFecha" class="datepicker form-control" />
                    </div>   
                </div>


                <div class="input-group"> 
                    <div class="input-group w-25">
                        <input class="btn btn-primary" type="submit" name="btnBuscar" id="btnBuscar" class="form-control"
                               value="Buscar" readonly  />
                    </div>   
                </div> 
            </form>

            <%
                //Arreglo de ventas productos
                ArrayList<VentaMesP> datosP = new ArrayList();
                //Se obtienen aqui para hacer comprobacion, abajo se hace un uso mas completo
                datosP = logica.ListarRegistroProducto(condicion, fecha);

                if (!datosP.isEmpty()) {
            %>

            <form action="ReporteProducto" method="post">
                <div class="form-group flex">
                    <div class="form-group ">
                        <div class="input-group"> 
                            <input type="hidden" value="<%=fecha%>" name="txtFecha" id="txtFecha" class="datepicker form-control" />
                        </div>   
                    </div>
                </div> 

                <div class="float-right">
                    <div class="input-group"> 
                        <div class="input-group w-25">
                            <input class="btn btn-secondary" type="submit" class="form-control"
                                   value="Crear Reporte" readonly  />

                        </div>   
                    </div> 

                </div> 
            </form> 

            <% }%><!--<!-- Productos -->

            <%
                if (request.getParameter("createP") != null && !request.getParameter("createP").equals("")) {
                    String ruta = new String(request.getParameter("createP").getBytes("ISO-8859-1"), "UTF-8");
            %>
            <div class="float-left"> 
                <span class="error">Se a creado el archivo, se encuentra en la ubicacion: "<%=new String(request.getParameter("createP").getBytes("ISO-8859-1"), "UTF-8")%>"</span>
            </div>
            <div class="float-right">
                <div class="input-group"> 

                    <div class="float-right mr-3">
                        <a class="btn btn-secondary" href="<%=ruta%>" target = "_blank">Abrir</a>  
                    </div>
                </div> 
            </div>  
            <%}%>

            <br><br>


            <!---- comment -->
            <table class="table">
                <thead>
                    <tr id="titulos">
                        <th>Código</th>
                        <th>Marca</th>
                        <th>Nombre</th>
                        <th>Cantidad Vendida</th> 
                        <th>Fecha</th> 
                    </tr>
                </thead>
                <tbody>
                    <%

                        nombreEmpleado = "";
                        condicion = "";
                        fecha = dateToStr;

                        if (request.getParameter(
                                "fecha") != null && !request.getParameter("fecha").equals("")) {

                            fecha = request.getParameter("fecha");
                        }

                        logica = new LNVenta();

                        for (VentaMesP registro : datosP) {

                    %>

                    <tr>
                        <%int codigo = registro.getIdVenta();%>

                        <td><%= codigo%></td>
                        <td><%= registro.getMarca()%></td>
                        <td><%= registro.getNombre()%></td>
                        <td><%= registro.getCantidadVendida()%></td> 
                        <td><%= fecha%></td> 
                    </tr>
                    <%}%>

                </tbody>
            </table>



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
                                <a class="nav-link text-dark" href="Frm_ListarFacturas.jsp">Facturación</a>
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

                                $('.tablaEmpleados').dataTable({
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
                                            "previous": "Anterior"
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

                                        txtNombreEmpleado: {required: true, maxlength: 50},
                                        txtFecha: {required: true, maxlength: 50},
                                        // el tamaño anterior podría ser cualquier otro, entre 8 y 11 es sólo un ejemplo

                                        txtPrecioCompra: {required: true, maxlength: 50},
                                        txtPrecioVenta: {required: true, maxlength: 50}

                                        // Nota: Para determinar estos tamaños debemos verificar las restricciones de nuestra BD


                                    },
                                    // Mensajes que deseamos personalizar: 
                                    messages: {
                                        txtNombreEmpleado: "El campo de Nombre es obligatorio (max 50 caracteres)",
                                        txtFecha: "El campo de Apellido es obligatorio (max 50 caracteres)",
                                        txtCantidadMedida: "El campo de Cantidad Medida es obligatorio",
                                        txtPrecioCompra: "El campo Precio Compra es obligatorio",
                                        txtPrecioVenta: "El campo Precio Venta es obligatorio"
                                    },
                                    errorElement: 'span'
                                            // Indicamos que si muestar mensajes de error, los muestre dentro de un span, con esto 
                                            // de forma automática si se produce un error se va a generar un SPAN, y por eso fue 
                                            // que creamos un ESTILO para indicar que el color de ese SPAN fuera rojo en una letra
                                            // un poco más pequeña. 
                                });



                            }); //final del function

                            function SeleccionarEmpleado(id, nombre) {
                                $("#txtidCliente").val(id);
                                $("#txtNombreEmpleado").val(nombre);
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
