<%@page import="Entidades.Compra"%>
<%@page import="Entidades.DatosPlanilla"%>
<%@page import="Entidades.Empleado"%>
<%@page import="LogicaNegocio.LNEmpleado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidades.Cliente"%>
<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>  
<%@page import="Entidades.*"%>
<%@page import="LogicaNegocio.*"%>
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
                <div  class="col-10"><h1><h1>Facturacion Compras</h1></div>
            </div>

            <%

                ArrayList<DatosPlanilla> listaDatosPlanillas;
                DatosPlanilla datosCarga;
                listaDatosPlanillas = new ArrayList();
                int numFactura = -1;
                Compra entidad;
                LNCompra logica = new LNCompra();

                Proveedor proveedor;
                LNProveedor logicaProveedor = new LNProveedor();

                if (request.getParameter("txtidProveedor") != null && request.getParameter("txtidProveedor").equals("")) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    entidad = logica.ObtenerRegistro("ID_Compra=" + numFactura);

                } else {
                    entidad = new Compra();
                    entidad.setIdCompra(-1);
                    Date fecha = new Date();
                    java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
                    entidad.setFechaCompra(fechasql);

                }

                proveedor = logicaProveedor.ObtenerRegistro("ID_Proveedor=" + entidad.getIdVendedor());


            %>
            <br/>

            <form action="CrearPDFCompra" method="post" id="form_AgregarModificar">

                <div class="form-group float-right">

                    <label><h2>Seleccione una Facturacion</h2></label>

                    <div class="input-group">

                        <label for="txtFechaFactura" class="form-control">Fecha</label>

                        <input type="text" name="txtFechaFactura" id="txtFechaFactura" class="datepicker form-control"
                               value="<%= entidad.getFechaCompra()%>" readonly/>
                    </div>  
                </div>
                <br/>

                <div class="form-group">

                    <div class="input-group"> 

                        <input type="hidden" name="txtidProveedor" id="txtidProveedor" class="form-control"
                               value="<%= entidad.getIdVendedor()%>" readonly/>

                        <input type="text" name="txtDescripcion" id="txtDescripcion" class="form-control" value="" readonly placeholder="Producto"/>

                        <br> 
                        &nbsp;&nbsp;&nbsp;
                        <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarCliente"><i class="fas fa-search"></i></a>
                        <br> 

                    </div>   
                </div>  

                <div class="form-group">
                    <div class="input-group">

                        <input type="hidden" name="txtidProducto" id="txtidProducto" class="form-control" value="" readonly/>
                        <div>
                            <label>&nbsp;Nombre Proveedor</label> 
                            <input type="text" name="txtNombreProveedor" id="txtNombreProveedor" class="form-control"
                                   value="<%= proveedor.getNombreProveedor()%>" readonly placeholder="Proveedor"/> 
                        </div> 
                        &nbsp;&nbsp;&nbsp;

                        <div>
                            <label>&nbsp;Cantidad Comprada</label> 
                            <input type="number" min="1" name="txtCantidad" id="txtCantidad" class="form-control" readonly value="" placeholder="Cantidad"/>
                        </div>
                        &nbsp;&nbsp;&nbsp;
                        <div>
                            &nbsp;&nbsp;&nbsp;
                            <label>Precio</label> 
                            <input type="number" name="txtPrecio" id="txtPrecio" class="form-control" value="" readonly  placeholder="Precio"/>
                        </div>
                        <input type="hidden" name="txtNombreEmpleado" id="txtNombreEmpleado" class="form-control" value="" readonly/>
                        <input type="hidden" name="txtMarca" id="txtMarca" class="form-control" value="" readonly/>
                        &nbsp;&nbsp;&nbsp;


                    </div>  
                </div>




                <div class="float-right form-group"> 
                    <button class="btn btn-primary" type="submit">Crear Facturacion</button>
                    <%if (request.getParameter("create") != null && !request.getParameter("create").equals("")) {
                            String ruta = new String(request.getParameter("create").getBytes("ISO-8859-1"), "UTF-8");
                    %>
                    <a class="btn btn-secondary" href="<%=ruta%>" target = "_blank">Abrir</a> 
                    <%}%>
                </div>  

            </form>
            <%
                if (request.getParameter("create") != null && !request.getParameter("create").equals("")) {

            %>
            <span class="error">Se a creado el archivo, se encuentra en la ubicacion: "<%=new String(request.getParameter("create").getBytes("ISO-8859-1"), "UTF-8")%>"</span>
            <%
                }//fin del if

            %>
            <br><!-- comment -->



            <br> 





            <a href="FrmFacturarCompras.jsp" class="btn btn-secondary">Regresar</a>
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
        

        <!-- ModalCliente -->
        <div class="modal fade" id="buscarCliente" tabindex="-1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title"  id="tituloVentana">Buscar Compra</h5>
                        <button class="close" data-dismiss="modal" arial-label="Cerrar" aria-hidden="true" onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">


                        <table class="tablaCompras">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre Producto</th> 
                                    <th>Nombre Proveedor</th>
                                    <th>Cantidad Comprada</th>
                                    <th>Precio total</th>
                                    <th>Fecha Compra</th>
                                    <th>Seleccionar</th> 
                                </tr>
                            </thead>

                            <tbody>

                                <%                                    
                                    LNCompra logicaCompra = new LNCompra();
                                    ArrayList<Compra> datosCompras = null;
                                    datosCompras = logicaCompra.ListarRegistro("");

                                    LNProveedor logicaProveedor1= new LNProveedor();
                                    proveedor = new Proveedor();

                                    LNProducto logicaProducto = new LNProducto();
                                    Producto producto = new Producto();

                                    LNEmpleado logicaEmpleado = new LNEmpleado();
                                    Empleado empleado = new Empleado();

                                    for (Compra registro : datosCompras) {

                                        empleado = logicaEmpleado.ObtenerRegistro("ID_Empleado=" + registro.getIdEmpleado());
                                        producto = logicaProducto.ObtenerRegistro("ID_Producto=" + registro.getIdProducto());
                                        proveedor = logicaProveedor1.ObtenerRegistro("ID_PROVEEDOR=" + registro.getIdVendedor());

                                %>

                                <tr>
                                    <%                                       
                                        int codigoVenta = registro.getIdVendedor();
                                        String nombreProveedor = proveedor.getNombreProveedor();
                                        String nombreProducto = producto.getNombre();
                                        String nombreEmpleado = empleado.getNombre();
                                        int cantidadCompra = registro.getCantidadCompra();
                                        double Precio = producto.getPrecioCompra()*registro.getCantidadCompra();
                                        String marca = producto.getMarca();
                                        String fechaCompra =  String.valueOf(registro.getFechaCompra()) ;
                                    %>  

                                    <td><%= codigoVenta%></td> <!-- estos no terminan con ; porque son Expresiones -->
                                    <td><%= nombreProducto%></td>   
                                    <td><%= nombreProveedor%></td> 
                                    <td><%= cantidadCompra%></td> 
                                    <td><%= Precio%></td>  
                                    <td><%= fechaCompra%></td>  
                                    <!-- Columna adicional con botones de opciones: -->
                                    <td>
                                        <a href="#" data-dismiss="modal" onclick="SeleccionarCompra('<%=marca%>', '<%=nombreEmpleado%>', '<%=nombreProducto%>', '<%=nombreProveedor%>'
                                                        , '<%=cantidadCompra%>', '<%=Precio%>')">Seleccionar</a>

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
                             
                            
                            $('.tablaCompras').dataTable({
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
                            $("#form_AgregarModificar").validate({
                                // Reglas que deseamos personalizar:
                                rules: {
                                    // Si no definimos estas reglas, solamente se aplicarán las reglas que estén definidas
                                    // dentro de cada input (por ejemplo el input se definió como requiered)

                                    txtNombreProveedor: {required: true, maxlength: 50},
                                    txtDescripcion: {required: true, maxlength: 50},
                                    // el tamaño anterior podría ser cualquier otro, entre 8 y 11 es sólo un ejemplo

                                    txtCantidad: {required: true, maxlength: 50}
                                    // Nota: Para determinar estos tamaños debemos verificar las restricciones de nuestra BD


                                },
                                // Mensajes que deseamos personalizar: 
                                messages: {
                                    txtNombreProveedor: "El campo de Nombre Proveedor es obligatorio",
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

                        function AbrirArchivo(ubicacion) {

                        }

                        function SeleccionarCompra(marca, nombreEmpleado, nombreProducto, nombreProveedor, cantidadCompra, Precio) {
                            $("#txtDescripcion").val(nombreProducto);
                            $("#txtNombreProveedor").val(nombreProveedor);
                            $("#txtCantidad").val(cantidadCompra);
                            $("#txtPrecio").val(Precio);
                            $("#txtMarca").val(marca);
                            $("#txtNombreEmpleado").val(nombreEmpleado);
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
