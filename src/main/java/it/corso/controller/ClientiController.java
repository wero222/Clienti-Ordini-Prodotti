package it.corso.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.model.Cliente;
import it.corso.service.ClienteService;

@Controller
@RequestMapping("/clienti")
public class ClientiController
{
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String getPage(Model model)
	{
		model.addAttribute("clienti", clienteService.getClienti());
		model.addAttribute("cliente", new Cliente());
		return "clienti";
	}
	
	@PostMapping
	public String registraCliente(@ModelAttribute("cliente") Cliente cliente)
	{
		clienteService.registraCliente(cliente);
		return "redirect:/clienti";
	}
	
	@GetMapping("/cancellacliente")
	public String cancellaCliente(@RequestParam("id") int id)
	{
		clienteService.cancellaCliente(id);
		return "redirect:/clienti";
	}
}