package com.gabrielgrs.aulaspring.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielgrs.aulaspring.domain.enuns.EstadoPagamento;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
