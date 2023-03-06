package br.alura.com.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.alura.com.spring.data.orm.Cargo;
import br.alura.com.spring.data.orm.Funcionario;
import br.alura.com.spring.data.orm.UnidadeTrabalho;
import br.alura.com.spring.data.repository.CargoRepository;
import br.alura.com.spring.data.repository.FuncionarioRepository;
import br.alura.com.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private FuncionarioRepository funcionarioRepository;
	private CargoRepository cargoRepository;
	private UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {
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
				break;
			}
			case 2: {
				visualizar(scanner);
				break;
			}
//			case 3: {
//				atualizar(scanner);
//			}
//			case 4: {
//				deletar(scanner);
//			}
			default:
				system = false;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome: ");
		String nome = scanner.next();
		System.out.println("Digite o cpf: ");
		String cpf = scanner.next();
		System.out.println("Digite o salario: ");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a data de contratação: ");
		String dataContratacao = scanner.next();
		System.out.println("Digite a cargoId: ");
		Integer cargoId = scanner.nextInt();
		
		List<UnidadeTrabalho> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);
		funcionarioRepository.save(funcionario);
		System.out.println("Salvo com sucesso!");
	}
	

	private List<UnidadeTrabalho> unidade(Scanner scanner) {
		Boolean isTrue = true;
		List<UnidadeTrabalho> unidades = new ArrayList<>();
		
		while(isTrue) {
			System.out.println("Digite o unidadeId (Digite 0 para sair)");
			int unidadeId = scanner.nextInt();
			if(unidadeId != 0) {
				Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
				unidades.add(unidade.get());
			} else {
				isTrue = false;
			}
		}
		return unidades;
	}

	
	private void visualizar(Scanner scanner) {
		System.out.println("Qual a página que deseja ver?");
		Integer pagina = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(pagina, 5, Sort.unsorted()); 
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

		System.out.println(funcionarios);
		System.out.println("Página atual: " + funcionarios.getNumber());
		System.out.println("A aplicação possui um total de: " + funcionarios.getTotalPages() + " página(as)");
		System.out.println("Total de elementos: " + funcionarios.getNumberOfElements());
		funcionarios.forEach(list -> System.out.println(list));
	}
	
	
	
	
	

	
}
