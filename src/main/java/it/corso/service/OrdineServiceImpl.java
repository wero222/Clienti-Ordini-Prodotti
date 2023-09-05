package it.corso.service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.OrdineDao;
import it.corso.model.Cliente;
import it.corso.model.Ordine;
import it.corso.model.Prodotto;

@Service
public class OrdineServiceImpl implements OrdineService
{
	@Autowired
	private OrdineDao ordineDao;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdottoService prodottoService;
	
	@Override
	public void registraOrdine(Ordine ordine, Object... dati)
	{
		// impostazione della data
		String data = (String) dati[0];
		try
		{
			LocalDate dataOrdine = LocalDate.parse(data);
			ordine.setData(dataOrdine);
		} catch (Exception e)
		{
			ordine.setData(LocalDate.now());
		}
		// impostazione cliente
		int idCliente = (int) dati[1];
		Cliente cliente = clienteService.getClienteById(idCliente);
		ordine.setCliente(cliente);
		// popolamento lista prodotti ordine
		ordine.getProdotti().clear();
		int[] idProdotti = (int[]) dati[2];
		for(int idProdotto : idProdotti)
		{
			Prodotto prodotto = prodottoService.getProdottoById(idProdotto);
			ordine.getProdotti().add(prodotto);
		}
		// calcolo totale ordine
		double importo = 0;
		for(Prodotto p : ordine.getProdotti())
			importo += p.getPrezzo();
		ordine.setImporto(importo);
		// effettiva registrazione dell'ordine
		ordineDao.save(ordine);
	}

	@Override
	public Ordine getOrdineById(int id)
	{
		return ordineDao.findById(id).get();
	}

	@Override
	public List<Ordine> getOrdini()
	{
		return (List<Ordine>) ordineDao.findAll();
	}

	@Override
	public void cancellaOrdine(int id)
	{
		Ordine ordine = ordineDao.findById(id).get();
		ordineDao.delete(ordine);
	}
}