package com.microservicio.microservicio.service.impl;

import com.microservicio.microservicio.service.CalculeTipoCambioExterno;

public class CalculaTipoCambio {

    CalculeTipoCambioExterno calculeTipoCambioExterno;

    public int nuestroTipoCambio(int precioA, int precioB ){

        return calculeTipoCambioExterno.calcularTipoCambio(precioA,precioB);

    }

}
