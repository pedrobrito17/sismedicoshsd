package com.sismed.sismedhsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeralController {
	
	@RequestMapping("/")
	public String home() {		
		return "home";
	}
	
	@RequestMapping("/hsdmedicos/novaescala")
	public ModelAndView getNovaEscala(){
		ModelAndView mv = new ModelAndView("escalas/gerar-escala");
		return mv;
	}
	
}
