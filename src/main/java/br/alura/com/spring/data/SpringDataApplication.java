package br.alura.com.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.alura.com.spring.data.orm.Cargo;
import br.alura.com.spring.data.repository.CargoRepository;
import br.alura.com.spring.data.service.CrudCargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner
{
	
	private Boolean system = true;
	private final CrudCargoService cargoService;
	
	
	public SpringDataApplication(CrudCargoService cargoService) {
		this.cargoService = cargoService;
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
			int i = scanner.nextInt();
			if(i == 1) {
				cargoService.inicial(scanner);
			} system = false;
		}
		
	}

}
