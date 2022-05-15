/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author alequ
 */
public class VentaMesP {
    private int idVenta;
    private String nombre;
    private String Marca;
    private int cantidadVendida;

    public VentaMesP(int idVenta, String nombre, String Marca, int cantidadVendida) {
        this.idVenta = idVenta;
        this.nombre = nombre;
        this.Marca = Marca;
        this.cantidadVendida = cantidadVendida;
    }
    public VentaMesP( ) {
        this.idVenta = 0;
        this.nombre = "";
        this.Marca = "";
        this.cantidadVendida = 0;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    
    
    
}
