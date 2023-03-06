package br.alura.com.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.alura.com.spring.data.orm.Cargo;
import br.alura.com.spring.data.repository.CargoRepository;
import br.alura.com.spring.data.service.CrudCargoService;
import br.alura.com.spring.data.service.CrudFuncionarioService;
import br.alura.com.spring.data.service.CrudUnidadeTrabalhoService;
import br.alura.com.spring.data.service.RelatoriosService;

//VIEW

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner
{
	
	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatoriosService relatoriosService;
	
	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, CrudUnidadeTrabalhoService unidadeTrabalhoService, 
			RelatoriosService relatoriosService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		while(system) {
			Scanner scanner = new Scanner(System.in);
//			CrudCargoService crudCargoService = new CrudCargoService(cargoRepository);
//			crudCargoService.inicial(scanner);
			System.out.println("Qual ação deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade de Trabalho");
			System.out.println("3 - Funcionário");
			System.out.println("4 - Relatórios");
			int i = scanner.nextInt();
			if(i == 1) {
				cargoService.inicial(scanner);
			} else if (i == 2){
				unidadeTrabalhoService.inicial(scanner);
			} else if (i == 3){
				funcionarioService.inicial(scanner);
			} else if (i == 4){
				relatoriosService.inicial(scanner);
			} else{
				system = false;
			}
		}
	}

}
