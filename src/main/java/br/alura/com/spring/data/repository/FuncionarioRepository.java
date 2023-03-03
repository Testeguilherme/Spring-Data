package br.alura.com.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.alura.com.spring.data.orm.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{

}
