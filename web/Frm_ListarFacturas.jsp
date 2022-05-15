<%-- 
    Document   : Frm_ListarFacturas
    Created on : 9/05/2022, 10:02:49 AM
    Author     : Steven
--%>
<%@page import="java.util.List"%>
<%@page import="Entidades.Factura"%>
<%@page import="LogicaNegocio.BL_Factura"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Facturas</title>
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
            <div class="card-header">
                <h1>Listado de Factura Pendientes</h1>
            </div>
            <br/>
            <form action="Frm_ListarFacturas.jsp" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtfecha" name="txtfecha" value=""
                               placeholder="Seleccione la fecha" class="datepicker"/>&nbsp; &nbsp;
                        <input type="submit" id="btnbuscar" name="btnbuscar" value="buscar"
                               class="btn btn-success"><br><br>
                    </div>
                </div>

                <div class="float-right">
                    <a href="FrmFacturarPDFVenta.jsp" class="btn btn-success">Realizar Facturacion</a>  
                </div>

            </form>

            <br><br> 

            <table class="table">
                <thead>
                    <tr>
                        <th>Num. Factura</th>
                        <th>Cliente</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String fecha = "";
                        String condicion = "";
                        if (request.getParameter("txtfecha") != null
                                && !request.getParameter("txtfecha").equals("")) {
                            fecha = request.getParameter("txtfecha");
                            condicion = condicion + "FECHA = '" + fecha + "'";
                        }
                        BL_Factura logica = new BL_Factura();
                        List<Factura> datos = null;
                        datos = logica.ListarRegistros(condicion);
                        for (Factura registro : datos) {
                            String estado = registro.getEstado();
                    %>
                    <tr>
                        <%int num = registro.getIdFactura();%>
                        <td><%= num%></td>
                        <td><%= registro.getNombreCliente()%></td>
                        <td><%= registro.getFecha()%></td>
                        <td><%= estado%></td>

                        <td>

                            <%if (!estado.equals("Cancelada")) {%>

                            <a href="Frm_facturar.jsp?txtnumFactura=<%=num%>"><i class="fas fa-cart-plus"></i></a>

                            <%}//fin del if%>

                        </td>

                    </tr>
                    <%}%>
                </tbody>
            </table>
            <br/>
            <a href="Frm_facturar.jsp?txtnumFactura=-1" class="btn btn-primary">Nueva Factura</a>
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
        <script>
            $(".datepicker").datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                language: 'es'
            });
        </script>
    </body>
</html>
