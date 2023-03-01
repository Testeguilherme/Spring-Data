package br.alura.com.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.alura.com.spring.data.orm.Cargo;

@Repository										      //<Objeto, tipo do ID>	
public interface CargoRepository extends CrudRepository<Cargo, Integer> 
{
	
	

}
