<%-- 
    Document   : FrmProducto
    Created on : may 8, 2022, 8:38:48 p.m.
    Author     : alequ
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.LNProducto"%>
<%@page import="Entidades.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/2.0.1/css/buttons.dataTables.min.css" />
        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/>

        <title>Mantenimiento de Producto</title>
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
                <div class="col-md-8 mx-auto">
                    <div class="card-header">
                        <h1>Mantenimiento de Productos</h1>
                    </div>
                    <!-- Si es un cliente nuevo enviamos el código con -1
                    Si el cliente existe le enviamos el id del cliente
                    Para ello creamos una entidad Cliente para colocar en
                    el VALUE de los INPUTS los datos de ese cliente -->

                    <%
                        String id = request.getParameter("idCrearModificar");
                        int codigo = Integer.parseInt(id);
                        Producto producto;
                        LNProducto logica = new LNProducto();

                        if (codigo > 0) { // El cliente existe
                            producto = logica.ObtenerRegistro("id_Producto=" + id);
                        } else { // Sino, crea uno nuevo
                            producto = new Producto();
                        }
                    %>

                    <form action="CrearModificarProducto" method="post" id="form_AgregarModificar">
                        <!-- * la ACCIÓN del formulario es llamar al Servlet CrearModificarProducto,
                        el método es POST porque es un formulario.
                        * El formulario necesita un ID para poder hacerle validación con el jqueryvalidate
                        * Cada form-group es una fila, para que se coloquen horizontalmente
                        
                         form-group para los controles de ID -->
                        <div class="form-group">
                            <%if (codigo > 0) {%>
                            <!-- Si el cliente existe, mostrará la etiqueta y el ID, deshabilitado para que no se pueda editar -->
                            <label for="txtCodigo" class="control-label">Código</label>
                            <input type="number" id="txtCodigo" name="txtCodigo" value="<%=producto.getIdProducto()%>"
                                   readonly class="form-control"/><br>
                            <%} else {%>
                            <!-- Sino, el campo ID se le asigna -1 y no se muestra en pantalla -->
                            <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/><br>
                            <%}%>
                        </div>

                        <!-- form-group para los controles de Marcas -->
                        <div class="form-group">
                            <label for="txtMarca" class="control-label">Marca</label>
                            <input type="text" id="txtMarca" required name="txtMarca" value="<%=producto.getMarca()%>" class="form-control" autocomplete="off"/><br>
                        </div>

                        <!-- form-group para los controles de Nombre -->
                        <div class="form-group">
                            <label for="txtNombre" class="control-label">Nombre</label>
                            <input type="text" id="txtNombre" required name="txtNombre" value="<%=producto.getNombre()%>" class="form-control" autocomplete="off"/><br>
                        </div>

                        <!-- form-group para los controles de Cantidad Medida -->
                        <div class="form-group">
                            <label for="txtCantidadMedida" class="control-label">Cantidad Medida</label>
                            <input type="number" min="1" id="txtCantidadMedida" name="txtCantidadMedida" value="<%=producto.getCantidadMedida()%>"
                                   class="form-control" placeholder="0" autocomplete="off"/><br>
                        </div>

                        <!--form group para controlar Tipo Medida-->
                        <div class="form-group">
                            <label for="seleccionarMedida" class="control-label">Seleccionar Medida</label>
                            <select name="seleccionarMedida" class="form-control"> 
                                <option value="1">GRAMOS</option>
                                <option value="2">KILOGRAMOS</option>
                                <option value="3">LITROS</option>
                                <option value="4">MILILITROS</option>
                            </select>
                        </div>


                        <!-- form-group para los controles de PrecioCompra -->
                        <div class="form-group">
                            <label for="txtPrecioCompra" class="control-label">Precio Compra</label>
                            <input type="number" id="txtPrecioCompra" name="txtPrecioCompra" value="<%=producto.getPrecioCompra()%>"
                                   class="form-control" min="1" placeholder="0" autocomplete="off"/><br>
                        </div>

                        <!-- form-group para los controles de PrecioVenta -->
                        <div class="form-group">
                            <label for="txtPrecioVenta" class="control-label">Precio Venta</label>
                            <input type="number" id="txtPrecioVenta" name="txtPrecioVenta" value="<%=producto.getPrecioVenta()%>"
                                   class="form-control" min="1" placeholder="0" autocomplete="off"/><br>
                        </div>

                        <!-- form-group para los controles de TipoMoneda -->
                        <div class="form-group">
                            <label for="seleccionarMoneda" class="control-label">Seleccionar Moneda</label>
                            <select name="seleccionarMoneda" class="form-control"> 
                                <option value="1">COLONES</option>
                                <option disabled="true" value="2">DOLARES</option> 
                            </select>
                        </div>
                        <!-- form-group para los controles de Cantidad de producto actual -->
                        <div class="form-group">

                            <%if (codigo > 0) {%>
                            <!-- Si el cliente existe, mostrará la etiqueta y el ID, deshabilitado para que no se pueda editar -->
                            <label for="txtCantidadProducto" class="control-label">Cantidad de producto actual</label>
                            <input type="number" id="txtCantidadProducto" name="txtCantidadProducto" value="<%=producto.getCantidadProducto()%>"
                                   placeholder="0"  class="form-control" readonly autocomplete="off"/><br>
                            <%} else {%>
                            <!-- Sino, el campo ID se le asigna -1 y no se muestra en pantalla -->
                            <input type="hidden" id="txtCantidadProducto" name="txtCantidadProducto" value="0"/><br>
                            <%}%>

                        </div>



                        <!-- form-group para los BOTONES de guardar y regresar -->
                        <div class="form-group">
                            <div class="input-group">
                                <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                <input type="button" id="btnRegresar" value="Regresar"
                                       onclick="location.href = 'FrmListarProductos.jsp'" class="btn btn-secondary"/>
                                <!-- El botón Regresar lleva a la página FrmListarClientes.jsp, no estamos haciendo un RESPONSE
                                porque no está respondiento a ninguna petición, entonces el Regresar lo estamos haciendo por medio de
                                Javascript con un atributo ONCLICk a ese botón, y en el ONCLICK estamos usando un LOCATION.HREF
                                y poder redireccionar a otra página. -->
                            </div>
                        </div>

                        <br><br>


                        <!-- Modal -->
                        <div class="modal " id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Buscar Producto</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <table id="tblProducto" class="display">
                                            <thead>
                                                <tr id="titulos">
                                                    <th>Código</th>
                                                    <th>Descripcion</th>
                                                    <th>PrecioCompra</th>
                                                    <th>PrecioVenta</th>
                                                    <th>Opciones</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    String nombre = "";
                                                    String condicion = "";

                                                    if (request.getParameter("txtnombre") != null) {

                                                        nombre = request.getParameter("txtnombre");
                                                        condicion = "nombre like '%" + nombre + "%'";
                                                    }

                                                    logica = new LNProducto();
                                                    ArrayList<Producto> datos;
                                                    datos = logica.ListarRegistro(condicion);

                                                    for (Producto registro : datos) {

                                                %>

                                                <tr>
                                                    <%codigo = registro.getIdProducto();%>

                                                    <td><%= codigo%></td>
                                                    <td><%= registro.getNombre()%></td>
                                                    <td><%= registro.getPrecioCompra()%></td>
                                                    <td><%= registro.getPrecioVenta()%></td>
                                                    <td>
                                                        <a href="#" data-dismiss="modal" onclick="SeleccionarProducto('<%=codigo%>', '<%=registro.getNombre()%>', '<%=registro.getPrecioCompra()%>', '<%=registro.getPrecioVenta()%>')">Seleccionar</a>

                                                    </td>
                                                </tr>
                                                <%}%>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>




                    </form>
                </div> <!-- clase que crea las 6 columnas -->
            </div> <!-- class row, div de la fila -->
        </div> <!-- class container -->

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

        <!-- Agregamos las referencias a Bootstrap, jquery y jquery-validation -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
        
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
 

        <script>

                                                            $(document).ready(function () {
                                                                $("#tblProducto").DataTable();

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

                                                                $("#form_AgregarModificar").validate({
                                                                    // Reglas que deseamos personalizar:
                                                                    rules: {
                                                                        // Si no definimos estas reglas, solamente se aplicarán las reglas que estén definidas
                                                                        // dentro de cada input (por ejemplo el input se definió como requiered)

                                                                        txtMarca: {required: true, maxlength: 50},
                                                                        txtNombre: {required: true, maxlength: 50},
                                                                        // el tamaño anterior podría ser cualquier otro, entre 8 y 11 es sólo un ejemplo

                                                                        txtPrecioCompra: {required: true, maxlength: 50},
                                                                        txtPrecioVenta: {required: true, maxlength: 50}

                                                                        // Nota: Para determinar estos tamaños debemos verificar las restricciones de nuestra BD


                                                                    },
                                                                    // Mensajes que deseamos personalizar: 
                                                                    messages: {
                                                                        txtMarca: "El campo de Marca es obligatorio (max 50 caracteres)",
                                                                        txtNombre: "El campo de Nombre es obligatorio (max 50 caracteres)",
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
                                                                /* Si hubiera estado este código dentro de un archivo JS, funcionaría igual. 
                                                                 * Pero siempre debemos tener cuidado de primero agregar Jquery y Jquery-validate
                                                                 * Primero se debe agregar el Jquery (el core) y después del validate, porque el validate
                                                                 * utiliza el core. Por ello el orden en que se inserten si es importante. 
                                                                 */




                                                            });//final del function

                                                            function SeleccionarCliente(idCliente, nombreCliente) {
                                                                $("#txtidCliente").val(idCliente);
                                                                $("#txtNombreCliente").val(nombreCliente);
                                                            }

                                                            function SeleccionarProducto(idProducto, Descripcion, Precio, Existencia) {
                                                                $("#txtNombre").val(Descripcion);


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
