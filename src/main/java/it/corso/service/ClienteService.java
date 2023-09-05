package it.corso.service;
import java.util.List;
import it.corso.model.Cliente;

public interface ClienteService
{
	void registraCliente(Cliente cliente);
	Cliente getClienteById(int id);
	List<Cliente> getClienti();
	void cancellaCliente(int id);
}