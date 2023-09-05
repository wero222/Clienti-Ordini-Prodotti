package it.corso.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordini")
public class Ordine
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "importo")
	private double importo;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	private Cliente cliente;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable
	(
			name = "ordini_prodotti",
			joinColumns = @JoinColumn(name = "id_ordine", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_prodotto", referencedColumnName = "id")
	)
	private List<Prodotto> prodotti = new ArrayList<>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public LocalDate getData()
	{
		return data;
	}

	public void setData(LocalDate data)
	{
		this.data = data;
	}

	public double getImporto()
	{
		return importo;
	}

	public void setImporto(double importo)
	{
		this.importo = importo;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public List<Prodotto> getProdotti()
	{
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti)
	{
		this.prodotti = prodotti;
	}
}