package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor

public record PedidoExterno( Integer serial,
        String nombre,
        String fecha,
        Float precioConDescuento) implements PedidoInterno{



    @Override
    public String toString() {
        return "PedidoExterno{" +
                "serial=" + serial +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precioConDescuento=" + precioConDescuento +
                '}';
    }




    @Override
    public Integer getSerial() {
        return null;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getFecha() {
        return this.fecha;
    }

    @Override
    public Float getPrecioConDescuento() {
        return this.precioConDescuento;
    }
}
