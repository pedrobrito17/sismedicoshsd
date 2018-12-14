package com.sismed.sismedhsd.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sismed.sismedhsd.model.Feriado;
import com.sismed.sismedhsd.service.FeriadoService;

@Controller
@RequestMapping("/hsdmedicos")
public class FeriadoController {
	
	@Autowired
	private FeriadoService feriadoService;
	
	@RequestMapping("/feriado")
	public ModelAndView getPageFeriado(){
		ModelAndView mv = new ModelAndView("/feriado/feriado");
		mv.addObject("feriado", new Feriado());
		mv.addObject("todosFeriados", feriadoService.getTodosFeriados());
		return mv;
	}
	
	@GetMapping("/salvarferiado")
	public ModelAndView salvarFeriado(@Valid Feriado feriado, BindingResult result, RedirectAttributes attributes){
		
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("feriado/feriado");
			mv.addObject("feriado", feriado );
			mv.addObject("todosFeriados", feriadoService.getTodosFeriados());
			return mv;
		}
		feriadoService.salvarFeriado(feriado);
		ModelAndView mv = new ModelAndView("redirect:/hsdmedicos/feriado");
		mv.addObject("feriado", new Feriado() );
		mv.addObject("todosFeriado", feriadoService.getTodosFeriados());
		attributes.addFlashAttribute("mensagem", "O feriado foi salvo com sucesso!");
		return mv;
	}
	
	@GetMapping("/excluirferiado/{id}")
	public ModelAndView excluirFeriado(@PathVariable Integer id){
		feriadoService.excluirFeriado(id);
		
		ModelAndView mv = new ModelAndView("feriado/feriado");
		mv.addObject("feriado", new Feriado() );
		mv.addObject("todosFeriados", feriadoService.getTodosFeriados());
		mv.addObject("mensagem", "Feriado deletado com sucesso!");
		return mv;
	}

}
