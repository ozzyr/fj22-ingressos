package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.desconto.*;

public enum TipoDeIngresso {

	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoParaBancos());
	
	private final Desconto desconto;
	
	TipoDeIngresso(Desconto desconto){
		this.desconto = desconto;
	}
	
	public BigDecimal aplicaDescontoSobre(BigDecimal valor) {
		return desconto.aplicarDescontoSobre(valor);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}

}

