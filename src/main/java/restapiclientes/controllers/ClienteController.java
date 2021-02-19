package restapiclientes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restapiclientes.error.ClienteAPIBadRequestException;
import restapiclientes.error.ClienteAPIException;
import restapiclientes.error.ClienteAPINotFoundException;
import restapiclientes.model.Cliente;
import restapiclientes.repository.ClienteRepository;
import restapiclientes.utilitarios.Utilitarios;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	//Mock para Unit Tests
	public void setClienteRepositoryMock(ClienteRepository mockedClienteRepository) {
		this.clienteRepository = mockedClienteRepository;
	}
	
	//Endpoint para cadastrar clientes
	@PostMapping(value="/clientes", produces = "application/json")
	public ResponseEntity<Cliente> cadastraCliente(@RequestBody Cliente cliente){
		
		Utilitarios utilitarios = new Utilitarios();
		boolean clienteValido = utilitarios.validaCliente(cliente);
		
		if(clienteValido == false) {
			throw new ClienteAPIBadRequestException("Dados inválidos");
		}
		
		
		try {
			Cliente clienteNovo = clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(clienteNovo, HttpStatus.CREATED) ;
		}
		catch(Exception ex) {	
			
			//Erro customizado
			throw new ClienteAPIException("Erro na inserção do cliente.");
			
		}		
	}
	
	//Endpoint para atualizar clientes passando todos os campos	
	@PutMapping(value="/clientes", produces = "application/json")
	public ResponseEntity<Cliente> atualizaCliente(@RequestBody Cliente cliente){
		
		Utilitarios utilitarios = new Utilitarios();
		boolean clienteValido = utilitarios.validaCliente(cliente);
		
		if(clienteValido == false) {
			throw new ClienteAPIBadRequestException("Dados inválidos");
		}
		
		Optional<Cliente> clienteExiste;
		
		try {
			clienteExiste = clienteRepository.findById(cliente.getIdCliente());
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na pesquisa ao cliente.");
		}
		
		
		if(clienteExiste.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Id cliente inválido : " + cliente.getIdCliente());
		}
		
		try {		
			
			Cliente clienteAtualizado = clienteRepository.save(cliente);		
			return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.OK);			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na atualização do cliente.");
		}
		
	}	
	
	//Endpoint para atualizar nome do cliente
	@PatchMapping(value="/cliente/nome", produces = "application/json")
	public ResponseEntity<Cliente> atualizaNome(
			@RequestParam(value="idCliente") Long idCliente,
			@RequestParam(value="nome") String nome){		
		
		Utilitarios utilitarios = new Utilitarios();
		boolean nomeValido = utilitarios.validaNome(nome);
		
		if(nomeValido == false) {
			throw new ClienteAPIBadRequestException("Nome inválido.");
		}
		
		Optional<Cliente> clienteExiste;
		
		try {
			clienteExiste = clienteRepository.findById(idCliente);
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na pesquisa ao cliente.");
		}
		
		
		if(clienteExiste.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Id cliente inválido : " + idCliente);
		}
		
		try {		
			
			clienteRepository.atualizaNomeCliente(idCliente, nome);				
			Cliente clienteAtualizado = clienteRepository.consultaCliente(idCliente);			
			return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.OK);			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na atualização do cliente.");
		}
		
		
	}
	
	
	//Enpoint para atualizar idade do cliente
	@PatchMapping(value="/cliente/idade", produces = "application/json")
	public ResponseEntity<Cliente> atualizaIdade(
			@RequestParam(value="idCliente") Long idCliente,
			@RequestParam(value="idade") int idade){		
		
		
		Utilitarios utilitarios = new Utilitarios();
		boolean idadeValida = utilitarios.validaIdade(idade);
		
		if(idadeValida == false) {
			throw new ClienteAPIBadRequestException("Idade inválida.");
		}
		
		
		Optional<Cliente> clienteExiste;
		
		try {
			clienteExiste = clienteRepository.findById(idCliente);
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na pesquisa ao cliente.");
		}
		
		
		if(clienteExiste.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Id cliente inválido : " + idCliente);
		}
		
		try {		
			
			clienteRepository.atualizaIdadeCliente(idCliente, idade);			
			Cliente clienteAtualizado = clienteRepository.consultaCliente(idCliente);		
			return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.OK);			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na atualização do cliente.");
		}
		
	}
	
	//Enpoint para atualizar email do cliente
	@PatchMapping(value="/cliente/email", produces = "application/json")
	public ResponseEntity<Cliente> atualizaEmail(
			@RequestParam(value="idCliente") Long idCliente,
			@RequestParam(value="email") String email){
		
		Utilitarios utilitarios = new Utilitarios();
		boolean emailValido = utilitarios.validaEmail(email);
		
		if(emailValido == false) {
			throw new ClienteAPIBadRequestException("E-mail inválido.");
		}
		
		Optional<Cliente> clienteExiste;
		
		try {
			clienteExiste = clienteRepository.findById(idCliente);
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na pesquisa ao cliente.");
		}
		
		
		if(clienteExiste.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Id cliente inválido : " + idCliente);
		}
		
		try {		
			
			clienteRepository.atualizaEmailCliente(idCliente, email);			
			Cliente clienteAtualizado = clienteRepository.consultaCliente(idCliente);			
			return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.OK);	
			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na atualização do cliente.");
		}
		
		
	}
	
	
	//Endpoint para retornar lista de clientes (paginada)
	@GetMapping(value="/clientes", produces = "application/json")
	public ResponseEntity<Page<Cliente>> listaClientes(
			@RequestParam(value="pageNumber") int pageNumber,
			@RequestParam(value="pageSize") int pageSize){
		
		Page<Cliente> listaClientes;
		
		try {
			
			Pageable clientesPageable = PageRequest.of(pageNumber, pageSize);
			
			listaClientes = clienteRepository.findAll(clientesPageable);
			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na consulta à clientes.");
		}
		
		
		if(listaClientes.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Nenhum cliente encontrado.");
		}
		else {
			return new ResponseEntity<Page<Cliente>>(listaClientes, HttpStatus.OK);
		}
		
		
	}
	
	//Endpoint para retornar lista de clientes (paginada) filtrando por nome
	@GetMapping(value="/clientes/nome", produces = "application/json")
	public ResponseEntity<Page<Cliente>> listaClientesPorNome(
			@RequestParam(value="nome") String nome,
			@RequestParam(value="pageNumber") int pageNumber,
			@RequestParam(value="pageSize") int pageSize){
		
		Utilitarios utilitarios = new Utilitarios();
		boolean nomeValido = utilitarios.validaNome(nome);
		
		if(nomeValido == false) {
			throw new ClienteAPIBadRequestException("Nome inválido.");
		}
		
		Page<Cliente> listaClientesPorNome;
		
		try {
			
			Pageable clientesPageable = PageRequest.of(pageNumber, pageSize);
			
			listaClientesPorNome = clienteRepository.consultaClientesPorNome(nome, clientesPageable);
			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na consulta à clientes.");
		}
		
		
		if(listaClientesPorNome.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Nenhum cliente encontrado.");
		}
		else {
			return new ResponseEntity<Page<Cliente>>(listaClientesPorNome, HttpStatus.OK);
		}
		
	}
	
	//Endpoint para retornar lista de clientes (paginada) filtrando por idade
	@GetMapping(value="/clientes/idade", produces = "application/json")
	public ResponseEntity<Page<Cliente>> listaClientesPorIdade(
			@RequestParam(value="idade") int idade,
			@RequestParam(value="pageNumber") int pageNumber,
			@RequestParam(value="pageSize") int pageSize){
		
		Utilitarios utilitarios = new Utilitarios();
		boolean idadeValida = utilitarios.validaIdade(idade);
		
		if(idadeValida == false) {
			throw new ClienteAPIBadRequestException("Idade inválida.");
		}
		
		Page<Cliente> listaClientesPorIdade;
		
		try {
			
			Pageable clientesPageable = PageRequest.of(pageNumber, pageSize);
			
			listaClientesPorIdade = clienteRepository.consultaClientesPorIdade(idade, clientesPageable);
			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na consulta à clientes.");
		}
		
		
		if(listaClientesPorIdade.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Nenhum cliente encontrado.");
		}
		else {
			return new ResponseEntity<Page<Cliente>>(listaClientesPorIdade, HttpStatus.OK);
		}
		
		
		
	}
	
	//Endpoint para retornar lista de clientes (paginada) filtrando por email
	@GetMapping(value="/clientes/email", produces = "application/json")
	public ResponseEntity<Page<Cliente>> listaClientesPorEmail(
			@RequestParam(value="email") String email,
			@RequestParam(value="pageNumber") int pageNumber,
			@RequestParam(value="pageSize") int pageSize){
		
		Utilitarios utilitarios = new Utilitarios();
		boolean emailValido = utilitarios.validaEmail(email);
		
		if(emailValido == false) {
			throw new ClienteAPIBadRequestException("E-mail inválido.");
		}
		
		Page<Cliente> listaClientesPorEmail;
		
		try {
			
			Pageable clientesPageable = PageRequest.of(pageNumber, pageSize);
			
			listaClientesPorEmail = clienteRepository.consultaClientesPorEmail(email, clientesPageable);
			
		}
		catch(Exception ex) {
			throw new ClienteAPIException("Erro na consulta à clientes.");
		}
		
		
		if(listaClientesPorEmail.isEmpty() == true) {
			throw new ClienteAPINotFoundException("Nenhum cliente encontrado.");
		}
		else {
			return new ResponseEntity<Page<Cliente>>(listaClientesPorEmail, HttpStatus.OK);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
