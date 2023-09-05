package it.corso.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.model.Prodotto;
import it.corso.service.ProdottoService;

@Controller
@RequestMapping("/prodotti")
public class ProdottiController
{
	@Autowired
	private ProdottoService prodottoService;
	
	@GetMapping
	public String getPage(Model model)
	{
		model.addAttribute("prodotti", prodottoService.getProdotti());
		model.addAttribute("prodotto", new Prodotto());
		return "prodotti";
	}
	
	@PostMapping
	public String registraProdotto(@ModelAttribute("prodotto") Prodotto prodotto)
	{
		prodottoService.registraProdotto(prodotto);
		return "redirect:/prodotti";
	}
	
	@GetMapping("/cancellaprodotto")
	public String cancellaProdotto(@RequestParam("id") int id)
	{
		prodottoService.cancellaProdotto(id);
		return "redirect:/prodotti";
	}
}