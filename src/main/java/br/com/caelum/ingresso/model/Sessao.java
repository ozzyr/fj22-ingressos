package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;

@Entity
public class Sessao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalTime horario;
    @OneToMany
    private Sala sala;
    @OneToMany
    private Filme filme; 

    public Sessao(LocalTime horario, Filme filme, Sala sala){
        this.horario = horario;
        this.filme = filme;
        this.sala = sala;
    }
    /**
     * @deprecated hibernate only
     */
    public Sessao(){

    }

    public Integer getId() {
        return this.id;
    }
    public Filme getFilme() {
        return this.filme;
    }

    public Sala getSala() {
        return this.sala;
    }
     
    public LocalTime getHorario() {
        return horario;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
   
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}