package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.*;

import br.com.caelum.ingresso.model.*;


public class SessaoForm{

    @NotNull
    private Integer salaId;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime horario;

    @NotNull
    private Integer filmeId;

    public void setFilmeId(Integer filmeId) {
        this.filmeId = filmeId;
    }
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
    public void setSalaId(Integer salaId) {
        this.salaId = salaId;
    }
   
    public Integer getFilmeId() {
        return this.filmeId;
    }
  
    public LocalTime getHorario() {
        return this.horario;
    }
   
    public Integer getSalaId() {
        return this.salaId;
    }

    public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao){
        Filme filme = filmeDao.findOne(filmeId);
        Sala sala = salaDao.findOne(salaId);

        Sessao sessao = new Sessao(horario, filme, sala);
                
        return sessao; 
    }
}