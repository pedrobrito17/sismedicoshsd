package com.sismed.sismedhsd.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sismed.sismedhsd.model.Medico;
import com.sismed.sismedhsd.service.FuncaoService;
import com.sismed.sismedhsd.service.MedicoService;
import com.sismed.sismedhsd.service.RestricaoService;


@Controller
@RequestMapping("/hsdmedicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private FuncaoService funcaoService;
	
	@Autowired
	private RestricaoService restricaoService;

	@RequestMapping("/cadastromedico")
	public ModelAndView cadastroMedico(Medico medico) {
		ModelAndView mv = new ModelAndView("medicos/cadastro-medico");
		System.out.println(funcaoService.todasFuncoes());
		mv.addObject("funcoes", funcaoService.todasFuncoes());
		mv.addObject("restricoes", restricaoService.todasRestricoes());
		return mv;
	}
	
	@RequestMapping("/medicosassociados")
	public ModelAndView medicosAssociados() {
		ModelAndView mv = new ModelAndView("medicos/medicos-associados");
		mv.addObject("medicos", medicoService.medicosPorCategoria("socio",true));
		return mv;
	}
	
	@RequestMapping("/medicoscontratados")
	public ModelAndView medicosContratados() {
		ModelAndView mv = new ModelAndView("medicos/medicos-contratados");
		mv.addObject("medicos", medicoService.medicosPorCategoria("contratado",true));
		return mv;
	}
	
	@PostMapping("/cadastrarmedico")
	public ModelAndView cadastrarMedico(@Valid Medico medico, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("medicos/cadastro-medico");
			mv.addObject("funcoes", funcaoService.todasFuncoes());
			mv.addObject("restricoes", restricaoService.todasRestricoes());
			return mv;
		}
		if(medicoService.salvar(medico)){
			attributes.addFlashAttribute("mensagem", "Médico cadastrado com sucesso!");
			ModelAndView mv = new ModelAndView("redirect:/hsdmedicos/cadastromedico");
			return mv;
		}
		ModelAndView mv = new ModelAndView("error/500"); //crm já está cadastrado
		return mv;	
	}
	
	@RequestMapping("/editarmedico/{crm}")
	public ModelAndView editarMedico(@PathVariable Integer crm){
		ModelAndView mv = new ModelAndView("medicos/edicao-medico");		
		mv.addObject("medico", medicoService.getMedico(crm));
		mv.addObject("funcoes", funcaoService.todasFuncoes());
		mv.addObject("restricoes", restricaoService.todasRestricoes());
		return mv;
	}
	
	@GetMapping("/excluirmedico")
	public ModelAndView excluirMedico(Integer crm){
		medicoService.excluirMedico(crm);
		ModelAndView mv = new ModelAndView("redirect:/"); //home
		return mv;
	}
	
	@PutMapping("/atualizarmedico")
	public ModelAndView atualizarMedico(@Valid Medico medico, BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("medicos/edicao-medico/"+medico.getCrm());
			return mv;
		}		
		medicoService.update(medico);
		attributes.addFlashAttribute("mensagem", "Alterações realizadas com sucesso!");
		ModelAndView mv = new ModelAndView("redirect:/hsdmedicos/editarmedico/"+medico.getCrm());
		return mv;
	}
	
	
	
}







