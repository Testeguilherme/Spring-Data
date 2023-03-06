package br.alura.com.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.alura.com.spring.data.orm.Funcionario;
import br.alura.com.spring.data.orm.FuncionarioProjecao;
import br.alura.com.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService{

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {

		while (system) {
			System.out.println("Qual comando deseja executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionário nome");
			System.out.println("2 - Busca nome/salario/data");
			System.out.println("3 - Busca funcionário por data");
			System.out.println("4 - Busca funcionário salário");
			int i = scanner.nextInt();

			switch (i) {
			case 1: {
				buscaFuncionarioNome(scanner);
				break;
			}
			case 2: {
				buscaFuncionarioNomeSalarioMaiorDataContratacao(scanner);
				break;
			}
			case 3: {
				buscaFuncionarioDataContratacao(scanner);
				break;
			}
			case 4: {
				buscaFuncionarioSalario();
				break;
			}
			default:
				system = false;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Digite o nome do funcionário:");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(lista -> System.out.println(lista));
	}
	
	private void buscaFuncionarioNomeSalarioMaiorDataContratacao(Scanner scanner) {
		System.out.println("Digite o nome do funcionário:");
		String nome = scanner.next();
		System.out.println("Digite o salario do funcionário:");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a data de contratação do funcionário:");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(lista -> System.out.println(lista));
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Digite a data de contratação desejada:");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(lista -> System.out.println(lista));
	}
	
	
	private void buscaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(lista -> System.out.println("Id: " + lista.getId() + " Nome: " + lista.getNome() + " Salario: " + lista.getSalario()));
	}
	
	
	
	
	
	
}
