package restapiclientes.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
public class ClienteControllerIntegrationTests {
	
	
	@Autowired
	public WebApplicationContext context;
	
	private MockMvc mvc;
	
	@BeforeEach
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	
	@Test
	public void testaCadastraCliente() throws Exception{
		
		String url = "/clientes";		
		
		this.mvc.perform(post(url)
				.content("{\"idCliente\":\"\", \"nome\":\"fulano\", \"idade\":\"35\", \"email\":\"fulano@teste.com.br\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	
	
	@Test
	public void testaAtualizaCliente() throws Exception{
		
		String url = "/clientes";
		
		this.mvc.perform(put(url)
				.content("{\"idCliente\":\"12\", \"nome\":\"fulano de tal\", \"idade\":\"40\", \"email\":\"fulanodetal@teste.com.br\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void testaAtualizaNome() throws Exception{
		
		String url = "/cliente/nome?idCliente=12&nome=ciclano";		
		
		this.mvc.perform(patch(url)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testaAtualizaIdade() throws Exception{
		
		String url = "/cliente/idade?idCliente=12&idade=42";		
		
		this.mvc.perform(patch(url)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testaAtualizaEmail() throws Exception{
		
		String url = "/cliente/email?idCliente=12&email=fulanodasilva@teste.com.br";		
		
		this.mvc.perform(patch(url)).andExpect(status().isOk());
	}
	
	
	
	@Test
	public void testaListaClientes() throws Exception{
		
		String url = "/clientes?pageNumber=0&pageSize=3";		
	
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	@Test
	public void testaListaClientesPorNome() throws Exception{
		
		String url = "/clientes/nome?nome=fulano&pageNumber=0&pageSize=3";		
	
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testaListaClientesPorIdade() throws Exception{
		
		String url = "/clientes/idade?idade=35&pageNumber=0&pageSize=3";		
	
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testaListaClientesPorEmail() throws Exception{
		
		String url = "/clientes/email?email=fulano@teste.com.br&pageNumber=0&pageSize=3";		
	
		this.mvc.perform(get(url)).andExpect(status().isOk());
	}
	

}