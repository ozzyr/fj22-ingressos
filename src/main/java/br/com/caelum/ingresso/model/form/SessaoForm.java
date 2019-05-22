package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.*;

import br.com.caelum.ingresso.model.*;


public class SessaoForm{
	@Autowired
    private SalaDao salaDao;

    @Autowired
    private FilmeDao filmeDao;

    @Autowired
    private SessaoDao sessaoDao;
	
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
    
    
    public SessaoForm(Integer salaId, SessaoForm form) {
		super();
		this.salaId = salaId;
		
	}
    
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao){
        Filme filme = filmeDao.findOne(filmeId);
        Sala sala = salaDao.findOne(salaId);

        Sessao sessao = new Sessao(horario, filme, sala);
                
        return sessao; 
    }
    
    @PostMapping(value="/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result) {
    	if(result.hasErrors()) return form(form.getSalaId(),form);
    	Sessao sessao = form.toSessao(salaDao, filmeDao);
    	sessaoDao.save(sessao);
    	
    	return new ModelAndView("redirect:/admin/sala"+form.getSalaId()+"/sessoes");   	
    	
    }
    
    @GetMapping("admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
    	
    	ModelAndView modelAndView = new ModelAndView("sessao/sessao");
    	
    	modelAndView.addObject("sala", salaDao.findOne(salaId));
    	modelAndView.addObject("filme", filmeDao.findAll());
    	modelAndView.addObject("form", form);
    	
    	return modelAndView;
    }

}