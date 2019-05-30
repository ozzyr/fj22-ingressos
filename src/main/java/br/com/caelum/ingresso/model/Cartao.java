package br.com.caelum.ingresso.model;

import java.time.YearMonth;

public class Cartao {

	private String numero;
	private Integer cvv;
	private YearMonth vencimento; 
	
	
	public Boolean isValido() {
		return vencimento.isAfter(YearMonth.now());
	}
	
}
