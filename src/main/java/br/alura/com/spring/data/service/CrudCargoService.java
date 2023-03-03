package br.alura.com.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.alura.com.spring.data.orm.Cargo;
import br.alura.com.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService 
{
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual ação deseja executar? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Visualizar");
			System.out.println("3 - Atualizar");
			System.out.println("4 - Deletar");
			int i = scanner.nextInt();
			
			switch (i) {
			case 1:{
				salvar(scanner);
				break;
			}
			case 2: {
				visualizar();
				break;
			}
			case 3: {
				atualizar(scanner);
				break;
			}
			case 4: {
				deletar(scanner);
				break;
			}
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);	
		System.out.println("Salvo com sucesso!");
	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	public void atualizar(Scanner scanner) {
		System.out.println("Digite o ID: ");
		int id = scanner.nextInt();
		System.out.println("Digite a descrição do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualizado com sucesso!");
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Digite o ID: ");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado com sucesso!");
	}
	
	
}
