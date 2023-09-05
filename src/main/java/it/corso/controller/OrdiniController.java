package it.corso.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.corso.model.Cliente;
import it.corso.model.Ordine;
import it.corso.model.Prodotto;
import it.corso.service.ClienteService;
import it.corso.service.OrdineService;
import it.corso.service.ProdottoService;

@Controller
@RequestMapping("/ordini")
public class OrdiniController
{
	@Autowired
	private OrdineService ordineService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	private Ordine ordine;
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name = "id", required = false) Integer id)
	{
		ordine = id == null ? new Ordine() : ordineService.getOrdineById(id);
		List<Cliente> clienti = clienteService.getClienti();
		List<Prodotto> prodotti = prodottoService.getProdotti();
		if(id != null)
			for(Prodotto p : prodotti)
				for(Prodotto pInOrdine : ordine.getProdotti())
					if(p.getId() == pInOrdine.getId())
						p.setIncluso(true);
		model.addAttribute("ordini", ordineService.getOrdini());
		model.addAttribute("clienti", clienti);
		model.addAttribute("prodotti", prodotti);
		model.addAttribute("ordine", ordine);
		return "ordini";
	}
	
	@PostMapping("/registraordine")
	public String registraOrdine(
			@RequestParam("data") String data,
			@RequestParam("cliente") int idCliente,
			@RequestParam(name = "prodotti", required = false) int[] idProdotti)
	{
		if(idProdotti == null)
			return "redirect:/ordini";
		ordineService.registraOrdine(ordine, data, idCliente, idProdotti);
		return "redirect:/ordini";
	}
	
	@GetMapping("/cancellaordine")
	public String cancellaOrdine(@RequestParam("id") int id)
	{
		ordineService.cancellaOrdine(id);
		return "redirect:/ordini";
	}
}