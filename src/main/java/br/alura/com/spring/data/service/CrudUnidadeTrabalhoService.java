package br.alura.com.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.alura.com.spring.data.orm.UnidadeTrabalho;
import br.alura.com.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private Boolean system = true;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual comando deseja executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Visualizar");
			System.out.println("3 - Atualizar");
			System.out.println("4 - Deletar");
			int i = scanner.nextInt();
			
			switch (i) {
			case 1: {
				salvar(scanner);
			}
			case 2: {
				visualizar();
			}
			case 3: {
				atualizar(scanner);
			}
			case 4: {
				deletar(scanner);
			}
			default:
				system = false;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Digite a unidade");
		String descricao = scanner.next();
		System.out.println("Digite o endereco");
		String endereco = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo com sucesso");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findAll();
		unidade.forEach(uni -> System.out.println(uni));
	}
	
	private void atualizar(Scanner scanner) {
		visualizar();
		System.out.println("Digite o ID que deseja alterar");
		int id = scanner.nextInt();
		System.out.println("Digite a descrição: ");
		String descricao = scanner.next();
		System.out.println("Digite o endereço: ");
		String endereco = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
	}
	
	private void deletar(Scanner scanner) {
		visualizar();
		System.out.println("Digite o ID do item que deseja deletar: ");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado com sucesso!");
	}
	
	
	
	
}
