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

import com.sismed.sismedhsd.model.MedicoSession;
import com.sismed.sismedhsd.model.Pedido;
import com.sismed.sismedhsd.service.MedicoService;
import com.sismed.sismedhsd.service.PedidoService;

@Controller
@RequestMapping("/hsdmedicos")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class PedidosController {
	
	@Autowired
	private MedicoSession session;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private MedicoService medicoService;

	@RequestMapping("/pedidos/{crm}")
	public ModelAndView getPagePedicos(@PathVariable("crm") Integer crm){
		session.setMedico( medicoService.getMedico(crm) );
		
		ModelAndView mv = new ModelAndView("/medicos/pedido-medico");
		mv.addObject("med", session.getMedico());
		mv.addObject("pedido", new Pedido() );
		mv.addObject("todosPedidos", pedidoService.getTodosPedidos( session.getMedico() ));
		return mv;
	}
	
	@GetMapping("/salvarpedido")
	public ModelAndView salvarFerias(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes){
		
		pedido.setMedico( session.getMedico() );
		
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView("medicos/pedido-medico");
			mv.addObject("med", session.getMedico());
			mv.addObject("pedido", pedido );
			if(pedido.getData()==null){
				mv.addObject("erro", "Escolha uma data");
			}
			mv.addObject("todosPedidos", pedidoService.getTodosPedidos( session.getMedico() ));
			return mv;
		}
		pedidoService.salvarPedido(pedido);
		ModelAndView mv = new ModelAndView("redirect:/hsdmedicos/pedidos/"+session.getMedico().getCrm() );
		mv.addObject("medico", pedido.getMedico());
		mv.addObject("pedido", new Pedido() );
		mv.addObject("todosPedidos", pedidoService.getTodosPedidos( session.getMedico() ));
		attributes.addFlashAttribute("mensagem", "O pedido do médico foi salvo com sucesso!");
		return mv;
	}
	
	@GetMapping("/excluirpedido/{id}")
	public ModelAndView excluirFeriasMedico(@PathVariable Integer id){
		pedidoService.excluirPedido(id);
		
		ModelAndView mv = new ModelAndView("medicos/pedido-medico");
		mv.addObject("med", session.getMedico());
		mv.addObject("pedido", new Pedido() );
		mv.addObject("todosPedidos", pedidoService.getTodosPedidos( session.getMedico() ));
		mv.addObject("mensagem", "Pedido deletado com sucesso!");
		return mv;
	}
}
