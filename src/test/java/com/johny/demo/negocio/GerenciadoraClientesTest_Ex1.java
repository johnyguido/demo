package com.johny.demo.negocio;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraClientesTest_Ex1 {

	@Test
	public void testPesquisaCliente() {

		// criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Fulano Detal", 31, "fulano@fakemail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Ciclano das Couves", 34, "ciclano@fakemail.com", 2, true);
		
		// inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);
		
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("fulano@fakemail.com"));
		
	}

	@Test
	public void testVerificaUsuarioAtivo() {

		// Criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Fulano Detal", 31, "fulano@fakemail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Ciclano das Couves", 34, "ciclano@fakemail.com", 2, true);

		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		var gerClientes = new GerenciadoraClientes(clientesDoBanco);

		boolean usuarioAtivo = gerClientes.clienteAtivo(1);
		assertThat(usuarioAtivo, is(Boolean.TRUE));
	}

	@Test
	public void testVerificaUsuarioInativo() {

		// Criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Fulano Detal", 31, "fulano@fakemail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Ciclano das Couves", 34, "ciclano@fakemail.com", 2, false);

		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		var gerClientes = new GerenciadoraClientes(clientesDoBanco);

		boolean usuarioAtivo = gerClientes.clienteAtivo(2);
		assertThat(usuarioAtivo, is(Boolean.FALSE));
	}

	@Test
	public void testListarUsuarioDeveRetornarUsuarios() {

		// Criando alguns clientes
		Cliente cliente01 = new Cliente(1, "Fulano Detal", 31, "fulano@fakemail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Ciclano das Couves", 34, "ciclano@fakemail.com", 2, false);

		// Inserindo os clientes criados na lista de clientes do banco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		var gerClientes = new GerenciadoraClientes(clientesDoBanco);

		List<Cliente> listaClientes = gerClientes.getClientesDoBanco();

		assertThat(listaClientes, is(notNullValue()));
	}

}
