package com.johny.demo;

import com.johny.demo.negocio.Cliente;
import com.johny.demo.negocio.ContaCorrente;
import com.johny.demo.negocio.GerenciadoraClientes;
import com.johny.demo.negocio.GerenciadoraContas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Component
class MyCommandLineRunner implements CommandLineRunner {

	private GerenciadoraClientes gerClientes;
	private GerenciadoraContas gerContas;

	public MyCommandLineRunner(GerenciadoraClientes gerClientes, GerenciadoraContas gerContas) {
		this.gerClientes = gerClientes;
		this.gerContas = gerContas;
	}

	@Override
	public void run(String... args) throws Exception {
		inicializaSistemaBancario();

		Scanner sc = new Scanner(System.in);
		boolean continua = true;

		while (continua) {
			printMenu();

			int opcao = sc.nextInt();

			switch (opcao) {
				case 1:
					// Consultar por um cliente
					System.out.print("Digite o ID do cliente: ");
					int idCliente = sc.nextInt();
					Cliente cliente = gerClientes.pesquisaCliente(idCliente);

					if (cliente != null)
						System.out.println(cliente.toString());
					else
						System.out.println("Cliente não encontrado!");

					pulalinha();
					break;

				case 2:
					// Consultar por uma conta corrente
					System.out.print("Digite o ID da conta: ");
					int idConta = sc.nextInt();
					ContaCorrente conta = gerContas.pesquisaConta(idConta);

					if (conta != null)
						System.out.println(conta.toString());
					else
						System.out.println("Conta não encontrada!");

					pulalinha();
					break;

				case 3:
					// Ativar um cliente
					System.out.print("Digite o ID do cliente: ");
					int idCliente2 = sc.nextInt();
					Cliente cliente2 = gerClientes.pesquisaCliente(idCliente2);

					if (cliente2 != null) {
						cliente2.setAtivo(true);
						System.out.println("Cliente ativado com sucesso!");
					} else
						System.out.println("Cliente não encontrado!");

					pulalinha();
					break;

				case 4:
					// Desativar um cliente
					System.out.print("Digite o ID do cliente: ");
					int idCliente3 = sc.nextInt();
					Cliente cliente3 = gerClientes.pesquisaCliente(idCliente3);

					if (cliente3 != null) {
						cliente3.setAtivo(false);
						System.out.println("Cliente desativado com sucesso!");
					} else
						System.out.println("Cliente não encontrado!");

					pulalinha();
					break;

				case 5:
					continua = false;
					System.out.println("################# Sistema encerrado #################");
					break;

				default:
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					break;
			}
		}
	}

	private void pulalinha() {
		System.out.println("\n");
	}

	private void printMenu() {
		System.out.println("O que você deseja fazer? \n");
		System.out.println("1) Consultar por um cliente");
		System.out.println("2) Consultar por uma conta corrente");
		System.out.println("3) Ativar um cliente");
		System.out.println("4) Desativar um cliente");
		System.out.println("5) Sair");
		System.out.println();
	}

	private void inicializaSistemaBancario() {
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		List<Cliente> clientesDoBanco = new ArrayList<>();

		ContaCorrente conta01 = new ContaCorrente(1, 0, true);
		ContaCorrente conta02 = new ContaCorrente(2, 0, true);
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);

		Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", conta01.getId(), true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", conta02.getId(), true);
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		gerContas = new GerenciadoraContas(contasDoBanco);
	}

}
