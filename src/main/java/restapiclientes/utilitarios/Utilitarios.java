package restapiclientes.utilitarios;

import restapiclientes.model.Cliente;

public class Utilitarios {
	
	public boolean validaCliente(Cliente cliente) {
		
		boolean clienteValido = true;		
		
		if (cliente.getNome() == null || cliente.getNome() == "")
		{clienteValido = false;}
		
		if (cliente.getIdade() == 0 )
		{clienteValido = false;}
		
		if (cliente.getEmail() == null || cliente.getEmail() == "")
		{clienteValido = false;}
		else if (cliente.getEmail().contains("@") == false)
		{clienteValido = false;}
		
		return clienteValido;
		
	}
	
	
	public boolean validaNome(String nome) {
		
		boolean nomeValido = true;		
		
		if (nome == null || nome == "")
		{nomeValido = false;}
		
		return nomeValido;
		
	}
	
	public boolean validaIdade(int idade) {
		
		boolean idadeValida = true;	
		
		if (idade == 0 )
		{idadeValida = false;}
		
		return idadeValida;
		
	}

	
	public boolean validaEmail(String email) {
		
		boolean emailValido = true;				
		
		if (email == null || email == "")
		{emailValido = false;}
		else if (email.contains("@") == false)
		{emailValido = false;}
		
		return emailValido;
		
	}

}
