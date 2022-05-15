<%-- 
    Document   : FrmProveedor
    Created on : may 12, 2022, 4:31:21 p.m.
    Author     : alequ
--%>

<%@page import="LogicaNegocio.LNCliente"%>
<%@page import="Entidades.Proveedor"%>
<%@page import="LogicaNegocio.LNProveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/>

        <title>Mantenimiento de Clientes</title>
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
                        <h1>Mantenimiento de Proveedores</h1>
                    </div>
                    <!-- Si es un cliente nuevo enviamos el código con -1
                    Si el cliente existe le enviamos el id del cliente
                    Para ello creamos una entidad Cliente para colocar en
                    el VALUE de los INPUTS los datos de ese cliente -->

                    <%
                        String id = request.getParameter("idCrearModificar");
                        int codigo = Integer.parseInt(id);
                        Proveedor proveedor;
                        LNProveedor logica = new LNProveedor();

                        if (codigo > 0) { // El cliente existe
                            proveedor = logica.ObtenerRegistro("ID_PROVEEDOR=" + id);
                        } else { // Sino, crea uno nuevo
                            proveedor = new Proveedor();
                        }
                    %>

                    <form action="CrearModificarProveedor" method="post" id="form_AgregarModificar">
                        <!-- * la ACCIÓN del formulario es llamar al Servlet CrearModificarCliente,
                        el método es POST porque es un formulario.
                        * El formulario necesita un ID para poder hacerle validación con el jqueryvalidate
                        * Cada form-group es una fila, para que se coloquen horizontalmente
                        
                         form-group para los controles de ID -->
                        <div class="form-group">
                            <%if (codigo > 0) {%>
                            <!-- Si el cliente existe, mostrará la etiqueta y el ID, deshabilitado para que no se pueda editar -->
                            <label for="txtCodigo" class="control-label">Código</label>
                            <input type="number" id="txtCodigo" name="txtCodigo" value="<%=proveedor.getIdProveedor()%>"
                                   readonly class="form-control"/><br>
                            <%} else {%>
                            <!-- Sino, el campo ID se le asigna -1 y no se muestra en pantalla -->
                            <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/><br>
                            <%}%>
                        </div>

                        <!-- form-group para los controles de Dirección -->
                        <div class="form-group">
                            <label for="txtMarca" class="control-label">Marca</label>
                            <input type="text" id="txtMarca" autocomplete="off" name="txtMarca" value="<%=proveedor.getMarca()%>"
                                   class="form-control"/><br>
                        </div>

                        <!-- form-group para los controles de Nombre -->
                        <div class="form-group">
                            <label for="txtNombre" class="control-label">Nombre</label>
                            <input type="text" id="txtNombre" name="txtNombre"  autocomplete="off" value="<%=proveedor.getNombreProveedor()%>" class="form-control"/><br>
                        </div>

                        <div class="form-group">
                            <label for="txtApellido" class="control-label">Apellido</label>
                            <input type="text" id="txtApellido" name="txtApellido"  autocomplete="off" value="<%=proveedor.getApellidoProveedor()%>"
                                   class="form-control"/><br>
                        </div>



                        <!-- form-group para los controles de Teléfono -->
                        <div class="form-group">
                            <label for="txtTelefono" class="control-label">Teléfono</label>
                            <input type="number" id="txtTelefono" name="txtTelefono" value="<%=proveedor.getTelefono()%>" autocomplete="off" class="form-control" placeholder="00-00-00-00"/><br>
                        </div>

                        <!-- form-group para los BOTONES de guardar y regresar -->
                        <div class="form-group">
                            <div class="input-group">
                                <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                <input type="button" id="btnRegresar" value="Regresar"
                                       onclick="location.href = 'FrmListarProveedores.jsp'" class="btn btn-secondary"/>
                                <!-- El botón Regresar lleva a la página FrmListarClientes.jsp, no estamos haciendo un RESPONSE
                                porque no está respondiento a ninguna petición, entonces el Regresar lo estamos haciendo por medio de
                                Javascript con un atributo ONCLICk a ese botón, y en el ONCLICK estamos usando un LOCATION.HREF
                                y poder redireccionar a otra página. -->
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


        <script src="lib/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>



        <!-- Código de Javascript para realizar la VALIDACIÓN del formulario:
        En este ejemplo este código se incluyó dentro de la misma página JSP, si uno quisiera
        puede crear un archivo aparte para manejar el código JS (al igual que lo hicimos con CSS),
        y en la página JSP hacer el enlace al archivo JS. -->

        <script>
                                           // Cuando el documento está listo
                                           $(document).ready(function () {

                                               /* Hacemos una FUNCIÓN ANÓNIMA (que no tiene nombre)
                                                El $ es propio de JQuery, seleccionar el formulario (por medio de su ID) y le aplica
                                                el método .validate
                                                Con sólo el .validate ya es suficiente para que el formulario valide, pero recordemos que 
                                                los mensajes predetermiandos están en inglés. Por ello entre paréntes y entre llaves le agregamos
                                                las REGLAS y los MENSAJES que deseamos personalizar. 
                                                
                                                Nota: estos nombres (por ejemplo txtNombre) son los nombres (atributo name) de los INPUTs.
                                                Los atributos name de los inputos diferencias mayúsuclas de minúsuclas. 
                                                */

                                               $("#form_AgregarModificar").validate({
                                                   // Reglas que deseamos personalizar:
                                                   rules: {
                                                       // Si no definimos estas reglas, solamente se aplicarán las reglas que estén definidas
                                                       // dentro de cada input (por ejemplo el input se definió como requiered)

                                                       txtNombre: {required: true, maxlength: 50},
                                                       txtApellido: {required: true, maxlength: 50},
                                                       txtTelefono: {required: true, minlength: 8, maxlength: 11},
                                                       // el tamaño anterior podría ser cualquier otro, entre 8 y 11 es sólo un ejemplo

                                                       txtMarca: {required: true, maxlength: 50}

                                                       // Nota: Para determinar estos tamaños debemos verificar las restricciones de nuestra BD


                                                   },
                                                   // Mensajes que deseamos personalizar: 
                                                   messages: {
                                                       txtNombre: "El campo de Nombre es obligatorio (max 50 caracteres)",
                                                       txtApellido: "El campo de Apellido es obligatorio (max 50 caracteres)",
                                                       txtTelefono: "El campo Teléfono es obligatorio (mínimo 8 caracteres, máximo 11)",
                                                       txtMarca: "El campo Marca es obligatorio"
                                                   },
                                                   errorElement: 'span'
                                                           // Indicamos que si muestar mensajes de error, los muestre dentro de un span, con esto 
                                                           // de forma automática si se produce un error se va a generar un SPAN, y por eso fue 
                                                           // que creamos un ESTILO para indicar que el color de ese SPAN fuera rojo en una letra
                                                           // un poco más pequeña. 
                                               });
                                           });
                                           /* Si hubiera estado este código dentro de un archivo JS, funcionaría igual. 
                                            * Pero siempre debemos tener cuidado de primero agregar Jquery y Jquery-validate
                                            * Primero se debe agregar el Jquery (el core) y después del validate, porque el validate
                                            * utiliza el core. Por ello el orden en que se inserten si es importante. 
                                            */
        </script>

    </body>
</html>
