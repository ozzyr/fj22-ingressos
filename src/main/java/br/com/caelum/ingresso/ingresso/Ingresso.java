package br.com.caelum.ingresso.ingresso;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.desconto.Desconto;

public class Ingresso {
	
	private Sessao sessao;
	private BigDecimal preco;

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}


	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Ingresso(Sessao sessao, Desconto desconto) {
		this.sessao = sessao;
		this.preco = desconto.aplicarDescontoSobre(sessao.getPreco());
	}
}
