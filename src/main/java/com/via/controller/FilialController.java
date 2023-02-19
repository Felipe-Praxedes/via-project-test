package com.via.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.via.model.Filial;
import com.via.model.Filial.TipoFilial;
import com.via.repository.FilialRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/filiais")
@AllArgsConstructor
public class FilialController {

	private static final Logger log = LoggerFactory.getLogger(FilialController.class);
	
	@Autowired
	private FilialRepository filialRepository;
	
	@GetMapping
	public List<Filial> listarFiliais(){
		log.info("Listar todas as filiais");
		try {
			return filialRepository.findAll();
		} catch (Exception e) {
			log.error("Erro ao listar as filiais: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao listar as filiais", e);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Filial> adicionarFilial(@RequestBody Filial filial) {
	    log.info("Adicionar a filial {}", filial.getNome());
	    try {
	        List<Filial> filialExistente = filialRepository.findByCnpj(filial.getCnpj());
	        if (!filialExistente.isEmpty()) {
	            throw new Exception("CNPJ já existe!");
	        }
	        Filial filialCriada = filialRepository.save(filial);
	        return ResponseEntity.ok(filialCriada);
	    } catch (Exception e) {
	        log.error("Erro ao adicionar a filial: {}", e.getMessage());
	        throw new ResponseStatusException(HttpStatus.OK, "Erro ao adicionar a filial", e);
	    }
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarFilial(@PathVariable Long id) {
		log.info("Deletar a filial com id {}", id);
		try {
			filialRepository.deleteById(id);
		} catch (Exception e) {
			log.error("Erro ao deletar a filial: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar a filial", e);
		}
	}
	
	@PutMapping
	public Filial atualizarFilial(@RequestBody Filial filial) {
		log.info("Atualizar a filial {}", filial);
		try {
			filialRepository.save(filial);
			return filial;
		} catch (Exception e) {
			log.error("Erro ao atualizar a filial: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar filial", e);
		}
	}
	
	@GetMapping("/{id}")
	public Filial filialByIdorName(@PathVariable Long id) {
		log.info("Buscar a filial pelo ID {}", id);
		try {
			return filialRepository.findById(id).get();
		} catch (Exception e) {
			log.error("Erro ao buscar a filial pelo ID: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao deletar a filial", e);
		}
	}
	
	@GetMapping("/nome/{nome}")
	public List<Filial> buscarPorNome(@PathVariable String nome) {
		log.info("Buscar filiais por nome: {}", nome);
		try {
			return filialRepository.findByNomeContaining(nome);
		} catch (Exception e) {
			log.error("Erro ao buscar filiais por nome: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar a filial", e);
		}
    }
	
	@GetMapping("/cidade/{cidade}")
    public List<Filial> buscarPorCidade(@PathVariable String cidade) {
    	log.info("Buscar filiais por cidade: {}", cidade);
		try {
			return filialRepository.findByCidadeContaining(cidade);
		} catch (Exception e) {
			log.error("Erro ao buscar Cidades por nome: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar a cidade", e);
		}
	}
	
	@GetMapping("/uf/{uf}")
	public List<Filial> buscarPorUf(@PathVariable String uf) {
		log.info("Buscar filiais por UF: {}", uf);
		try {
			return filialRepository.findByCidadeContaining(uf);
		} catch (Exception e) {
			log.error("Erro ao buscar UF por nome: {}", e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar a UF", e);
		}
	}

	@GetMapping("/tipo/{tipo}")
	public List<Filial> buscarPorTipo(@PathVariable String tipo) {
	    try {
	        TipoFilial tipoEnum = TipoFilial.valueOf(tipo.toUpperCase());
	        return filialRepository.findByTipo(tipoEnum);
	    } catch (IllegalArgumentException e) {
	        log.warn("Tipo inválido: {}", tipo);
	        return new ArrayList<>(); 
	    } catch (Exception e) {
	        log.error("Erro ao buscar as filiais pelo tipo: ", e);
	        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar o tipo", e);
	    }
	}
	
	@GetMapping("/cnpj/{cnpj}")
	public List<Filial> buscarPorCnpj(@PathVariable Long cnpj) {
		try {
			return filialRepository.findByCnpj(cnpj);
		} catch (Exception e) {
			log.error("Erro ao buscar as filiais pelo tipo: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao buscar o CNPJ", e);
		}
	}
}
