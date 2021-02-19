package restapiclientes.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import restapiclientes.model.Cliente;
import restapiclientes.repository.ClienteRepository;

public class ClienteControllerUnitTests {
	
	private ClienteRepository mockedClienteRepository;
	private ClienteController clienteController;
	private Cliente cliente;
	private Page<Cliente> listaClientes;
	private Optional<Cliente> clienteExiste;
	Pageable clientesPageable;
	
	@BeforeEach
	void setup() {
		
		//cenário		
		cliente = new Cliente();
		cliente.setIdCliente(1L);
		cliente.setNome("Ricardo");
		cliente.setIdade(35);
		cliente.setEmail("ricardo@teste.com.br");
		
		List<Cliente> listaClientesTest = new ArrayList<Cliente>();
		listaClientesTest.add(cliente);	
		
		listaClientes = new PageImpl<>(listaClientesTest);
		
		clienteExiste = Optional.of(cliente);
				
		mockedClienteRepository = mock(ClienteRepository.class);
				
		clienteController = new ClienteController();
		clienteController.setClienteRepositoryMock(mockedClienteRepository);
		
	}
	
	@Test
	void testCadastraCliente() {
		
		//cenário		
		when(mockedClienteRepository.save(any(Cliente.class))).thenReturn(cliente);		
		
		//ação
		ResponseEntity<Cliente> respCliente = clienteController.cadastraCliente(cliente);
		
		//verificação		
		assertTrue(respCliente.getBody().getNome() != null);
		
	}
	
	
	@Test
	void testAtualizaCliente() {
		
		//cenário		
		when(mockedClienteRepository.save(any(Cliente.class))).thenReturn(cliente);
		when(mockedClienteRepository.findById(any(Long.class))).thenReturn(clienteExiste);	
		
		//ação		
		ResponseEntity<Cliente> respCliente = clienteController.atualizaCliente(cliente);
		
		//verificação		
		assertTrue(respCliente.getBody().getNome() != null);
		
	}
	
	
	@Test
	void testAtualizaNome() {
		
		//cenário		
		when(mockedClienteRepository.consultaCliente(any(Long.class))).thenReturn(cliente);	
		when(mockedClienteRepository.findById(any(Long.class))).thenReturn(clienteExiste);	
		
		//ação		
		ResponseEntity<Cliente> respCliente = clienteController.atualizaNome(1L, "Ricardo");
		
		//verificação		
		assertTrue(respCliente.getBody().getNome() != null);
		
	}
	
	
	@Test
	void testAtualizaIdade() {
		
		//cenário		
		when(mockedClienteRepository.consultaCliente(any(Long.class))).thenReturn(cliente);	
		when(mockedClienteRepository.findById(any(Long.class))).thenReturn(clienteExiste);	
		
		//ação
		ResponseEntity<Cliente> respCliente = clienteController.atualizaIdade(1L, 35);
		
		//verificação		
		assertTrue(respCliente.getBody().getIdade() != 0);
		
	}
	
	@Test
	void testAtualizaEmail() {
		
		//cenário		
		when(mockedClienteRepository.consultaCliente(any(Long.class))).thenReturn(cliente);	
		when(mockedClienteRepository.findById(any(Long.class))).thenReturn(clienteExiste);	
		
		//ação
		ResponseEntity<Cliente> respCliente = clienteController.atualizaEmail(1L, "ricardo@teste.com.br");
		
		//verificação		
		assertTrue(respCliente.getBody().getEmail() != null);
		
	}
	
	@Test
	void testListaClientes() {
		
		//cenário		
		when(mockedClienteRepository.findAll(any(Pageable.class))).thenReturn(listaClientes);	
		
		//ação
		ResponseEntity<Page<Cliente>> respPageClientes = clienteController.listaClientes(0, 3);
		
		//verificação		
		assertTrue(respPageClientes.getBody() != null);
		
	}
	
	
	@Test
	void testListaClientesPorNome() {
		
		//cenário
		when(mockedClienteRepository.consultaClientesPorNome(any(String.class), any(Pageable.class))).thenReturn(listaClientes);
		
		
		//ação
		ResponseEntity<Page<Cliente>> respPageClientes = 
				clienteController.listaClientesPorNome("Ricardo", 0, 3);
		
		//verificação		
		assertTrue(respPageClientes.getBody() != null);
		
	}
	
	
	@Test
	void testListaClientesPorIdade() {
		
		//cenário
		when(mockedClienteRepository.consultaClientesPorIdade(any(int.class), any(Pageable.class))).thenReturn(listaClientes);
		
		
		//ação
		ResponseEntity<Page<Cliente>> respPageClientes = 
				clienteController.listaClientesPorIdade(35, 0, 3);
		
		//verificação		
		assertTrue(respPageClientes.getBody() != null);
		
	}
	
	
	@Test
	void testListaClientesPorEmail() {
		
		//cenário
		when(mockedClienteRepository.consultaClientesPorEmail(any(String.class), any(Pageable.class))).thenReturn(listaClientes);
		
		
		//ação
		ResponseEntity<Page<Cliente>> respPageClientes = 
				clienteController.listaClientesPorEmail("ricardo@teste.com.br", 0, 3);
		
		//verificação		
		assertTrue(respPageClientes.getBody() != null);
		
	}
	

}
