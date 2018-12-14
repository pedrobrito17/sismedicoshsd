package com.sismed.sismedhsd.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sismed.sismedhsd.model.FeriasMedico;
import com.sismed.sismedhsd.model.MedicoSession;
import com.sismed.sismedhsd.service.FeriasMedService;
import com.sismed.sismedhsd.service.MedicoService;

@Controller
@RequestMapping("/hsdmedicos")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class FeriasController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private FeriasMedService feriasMedService;
	
	@Autowired
	private MedicoSession session;
	
	@RequestMapping("/ferias/{crm}")
	public ModelAndView feriasMedico(@PathVariable Integer crm){
		session.setMedico( medicoService.getMedico(crm) );
		
		ModelAndView mv = new ModelAndView("medicos/ferias-medico");		
		mv.addObject("med", session.getMedico());
		mv.addObject("ferias", new FeriasMedico() );
		mv.addObject("todasFerias", feriasMedService.getTodasFerias( session.getMedico() ));
		return mv;
	}
	
	@GetMapping("/salvarferias")
	public ModelAndView salvarFerias(@Valid FeriasMedico ferias, BindingResult result, RedirectAttributes attributes){
		
		ferias.setMedico( session.getMedico() );
		
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("medicos/ferias-medico");
			mv.addObject("med", session.getMedico());
			mv.addObject("ferias", ferias );
			mv.addObject("erro", "Escolha uma data de início e fim das férias.");
			mv.addObject("todasFerias", feriasMedService.getTodasFerias( session.getMedico() ));
			return mv;
		}
		feriasMedService.salvarFerias(ferias);
		ModelAndView mv = new ModelAndView("redirect:/hsdmedicos/ferias/"+session.getMedico().getCrm() );
		mv.addObject("medico", ferias.getMedico());
		mv.addObject("ferias", new FeriasMedico() );
		mv.addObject("todasFerias", feriasMedService.getTodasFerias( session.getMedico() ));
		attributes.addFlashAttribute("mensagem", "As férias do médico foram salvas com sucesso!");
		return mv;
	}
	
	@GetMapping("/excluirferias/{id}")
	public ModelAndView excluirFeriasMedico(@PathVariable Integer id){
		feriasMedService.excluirFerias(id);
		
		ModelAndView mv = new ModelAndView("medicos/ferias-medico");
		mv.addObject("med", session.getMedico());
		mv.addObject("ferias", new FeriasMedico() );
		mv.addObject("todasFerias", feriasMedService.getTodasFerias( session.getMedico() ));
		mv.addObject("mensagem", "Férias deletada com sucesso!");
		return mv;
	}

}
