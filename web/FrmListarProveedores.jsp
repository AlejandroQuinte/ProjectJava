<%-- 
    Document   : FrmListarProveedores
    Created on : may 12, 2022, 4:17:44 p.m.
    Author     : alequ
--%>

<%@page import="java.util.List"%>
<%@page import="Entidades.Proveedor"%>
<%@page import="LogicaNegocio.LNProveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Clientes</title>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/Styles.css" rel="stylesheet" type="text/css"/>
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
                <h1>Listado de Proveedores</h1>
            </div>
            <br/>
            <form action="FrmListarProveedores.jsp" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtnombre" autocomplete="off" name="txtnombre" value="" placeholder="Buscar por nombre..." class="form-control"/>&nbsp; &nbsp;
                        <input type="submit" id="btnBuscar" name="btnBuscar" value="buscar" class="btn btn-primary"/><br><br>
                    </div>
                </div>
            </form>
            <br> 
            <div>
                <a href="FrmProveedor.jsp?idCrearModificar=-1" class="btn btn-primary">Agregar Nuevo Proveedor</a>        
                <a href="FrmListarProveedores.jsp" class="btn btn-primary">Actualizar</a>        

                <a href="index.html" class="btn btn-secondary">Regresar al Index</a> 
            </div>

            <hr/>
            <table class="table">
                <thead>
                    <tr id="titulos">
                        <th>Código</th>
                        <th>Marca</th>
                        <th>Nombre</th>
                        <th>Teléfono</th> 
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        String nombre = "";
                        String condicion = "";

                        if (request.getParameter("txtnombre") != null) {

                            nombre = request.getParameter("txtnombre");
                            condicion = "NombreProveedor like '%" + nombre + "%'"+" or Marca like '%" + nombre + "%'";
                        }

                        LNProveedor logica = new LNProveedor();
                        List<Proveedor> datos;
                        datos = logica.ListarRegistro(condicion);

                        for (Proveedor registro : datos) {

                    %>

                    <tr>
                        <%int codigo = registro.getIdProveedor();%>

                        <td><%= codigo%></td>
                         <td><%= registro.getMarca()%></td>
                        <td><%= registro.getNombreProveedor()%></td>
                        <td><%= registro.getTelefono()%></td>  
                        <td> 
                            <a href="FrmProveedor.jsp?idCrearModificar=<%=codigo%>"><i class="fas fa-user-edit"></i></a>
                            <a href="EliminarProveedor?idEliminar=<%=codigo%>"><i class="fas fa-trash-alt"></i></a>
                        </td>
                    </tr>
                    <%}%>

                </tbody>
            </table>
            <br><br><!-- comment --> 

<br>
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
        
        </div>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>


    </body>
</html>
