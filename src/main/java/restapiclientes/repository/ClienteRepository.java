package restapiclientes.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import restapiclientes.model.Cliente;

@Repository
@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Long>, 
PagingAndSortingRepository<Cliente, Long>{
	
	Page<Cliente> findAll(Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.nome =?1")
	Page<Cliente> consultaClientesPorNome(String nome, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.idade =?1")
	Page<Cliente> consultaClientesPorIdade(int idade, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.email =?1")
	Page<Cliente> consultaClientesPorEmail(String email, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.idCliente =?1")
	public Cliente consultaCliente(Long idCliente);
	
	@Modifying(clearAutomatically = true, flushAutomatically=true)
	@Query("UPDATE Cliente c set c.nome = ?2 where c.idCliente = ?1")
	public int atualizaNomeCliente(Long idCliente, String nome);
	
	@Modifying(clearAutomatically = true, flushAutomatically=true)
	@Query("UPDATE Cliente c set c.idade = ?2 where c.idCliente = ?1")
	public int atualizaIdadeCliente(Long idCliente, int idade);
	
	@Modifying(clearAutomatically = true, flushAutomatically=true)
	@Query("UPDATE Cliente c set c.email = ?2 where c.idCliente = ?1")
	public int atualizaEmailCliente(Long idCliente, String email);
	
}
