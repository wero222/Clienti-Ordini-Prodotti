package it.corso.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.corso.dao.ClienteDao;
import it.corso.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService
{
	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public void registraCliente(Cliente cliente)
	{
		clienteDao.save(cliente);
	}

	@Override
	public Cliente getClienteById(int id)
	{
		return clienteDao.findById(id).get();
	}

	@Override
	public List<Cliente> getClienti()
	{
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public void cancellaCliente(int id)
	{
		Cliente cliente = clienteDao.findById(id).get();
		clienteDao.delete(cliente);
	}
}