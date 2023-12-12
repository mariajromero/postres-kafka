package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoExterno {
    private Integer serial;
    private String nombre;
    private String fecha;
   private  Float precioConDescuento;

    @Override
    public String toString() {
        return "PedidoExterno{" +
                "serial=" + serial +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precioConDescuento=" + precioConDescuento +
                '}';
    }
}
