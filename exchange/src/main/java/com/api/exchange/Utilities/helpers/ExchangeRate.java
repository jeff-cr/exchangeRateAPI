package com.api.exchange.Utilities.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.api.exchange.API.config.ExchangeConfig;

public class ExchangeRate {
    private int indicador = 0; // 317: Compra, 318: Venta
    private String tcFechaInicio;
    private String tcFechaFinal;
    private final String tcNombre = "TEC";
    private final String tnSubNiveles = "N";
    private final String HOST = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicosXML";
    private String url;
    private final String VALUE_TAG = "NUM_VALOR";
    private final ExchangeConfig.Bccr bccrConfig;
  

    public ExchangeRate(ExchangeConfig harbestConfig, Date requestDate) {
        this.bccrConfig = harbestConfig.getBccr();
        setFecha(requestDate);
    }

    /**
     * Devuelve el valor de <strong>COMPRA</strong> USD del BCCR
     * 
     * @return <code>Double</code> con el valor del precio de compra.
     */
    public double getCompra() {
        setCompra();
        double valor = Double.parseDouble(getValue());
        if (valor == 0) {
            throw new RuntimeException("Error al obtener tipo de cambio de compra.");
        }
        return valor;
    }

    /**
     * Devuelve el valor de <strong>VENTA</strong> USD del BCCR
     * 
     * @return <code>Double</code> con el valor del precio de venta.
     */
    public double getVenta() {
        setVenta();
        double valor = Double.parseDouble(getValue());
        if (valor == 0) {
            throw new RuntimeException("Error al obtener tipo de cambio de venta.");
        }
        return valor;
    }

    /**
     * Obtiene el XML del WebService del BCCR y parsea el documento para obtener el
     * valor (dado por VALUE_TAG)
     * 
     * @return String
     */
    private String getValue() {
        try {
            setUrl(); // Set del Url con los Parámetros

            // Obtiene y Parsea el XML
            String data = GetMethod.getHTML(url);
            XmlParser xml = new XmlParser(data);

            // Retorna el valor del
            String xmlString = xml.getValue(VALUE_TAG);
            return xmlString;
        } catch (Exception e) {
            System.out.println("Error al obtener el valor del BCCR.");
            return "0";
        }
    }

    private void setUrl() {
        String params = "Indicador=" + indicador +
                "&FechaInicio=" + tcFechaInicio +
                "&FechaFinal=" + tcFechaFinal +
                "&Nombre=" + tcNombre +
                "&SubNiveles=" + tnSubNiveles +
                "&CorreoElectronico=" + bccrConfig.getEmail() +
                "&Token=" + bccrConfig.getToken();
        this.url = HOST + "?" + params;
        System.out.println("URL " + this.url);
    }

    private void setFecha(Date requestDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    if (requestDate != null) {
    this.tcFechaInicio = sdf.format(requestDate);
    } else {
    this.tcFechaInicio = sdf.format(new Date()); // solo si es null
    }
    this.tcFechaFinal = tcFechaInicio;
    }

    private void setCompra() {
        this.indicador = 317;
    }

    private void setVenta() {
        this.indicador = 318;
    }

}
