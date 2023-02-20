package com.via.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.via.model.Filial;
import com.via.model.Filial.TipoFilial;

//@Repository
public interface FilialRepository extends JpaRepository<Filial, Long>{
	
	List<Filial> findByNomeContaining(String nome);
    List<Filial> findByCidadeContaining(String cidade);
    List<Filial> findByCnpj(Long cnpj);
    List<Filial> findByUf(String uf);
    List<Filial> findByTipo(TipoFilial tipoEnum);

}
