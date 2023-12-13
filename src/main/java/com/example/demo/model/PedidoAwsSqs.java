package com.example.demo.model;

import com.amazonaws.services.sqs.model.MessageAttributeValue;

import java.util.Map;

public record PedidoAwsSqs (Map<String, MessageAttributeValue> atributosMensaje)implements PedidoInterno{

    @Override
    public Integer getSerial() {
        return Integer.valueOf(this.atributosMensaje.get("serial").getStringValue());
    }

    @Override
    public String getNombre() {
        return this.atributosMensaje.get("nombre").getStringValue();
    }

    @Override
    public String getFecha() {
        return this.atributosMensaje.get("fecha").getStringValue();
    }

    @Override
    public Float getPrecioConDescuento() {
        return Float.valueOf(this.atributosMensaje.get("precioConDescuento").getStringValue());
    }
}
