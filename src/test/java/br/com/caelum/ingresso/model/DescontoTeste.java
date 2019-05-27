package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.caelum.ingresso.model.desconto.DescontoParaBancos;
import br.com.caelum.ingresso.model.desconto.SemDesconto;
import br.com.caelum.ingresso.model.desconto.descontoEstudante;

import org.junit.Assert;
import org.junit.Before;

public class DescontoTeste {

	private Sala sala; 
	private Filme filme;
	private Sessao sessao;
	
	
	@Before
	public void CenarioIngresso() {
		this.sala = new Sala("Eldorado - Imax", new BigDecimal("20.5"));
		this.filme = new Filme("Rougue one", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		this.sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}	
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {

		Ingresso ingresso = new Ingresso(sessao, new SemDesconto());

		BigDecimal precoEsperado = new BigDecimal("32.50");
		System.out.println(ingresso.getPreco());
		Assert.assertEquals(precoEsperado, ingresso.getPreco());

	}
	
	@Test
	public void DeveConcederDescontode30PorCentoParaIngressosDeClientesBanco() {

		Ingresso ingresso = new Ingresso(sessao, new DescontoParaBancos());

		BigDecimal precoEsperado = new BigDecimal("22.75");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());

	}
	
	@Test
	public void DeveConcederDescontode50PorCentoParaIngressosDeEstudante() {

		Ingresso ingresso = new Ingresso(sessao, new descontoEstudante());

		BigDecimal precoEsperado = new BigDecimal("16.25");

		Assert.assertEquals(precoEsperado, ingresso.getPreco());

	}

}