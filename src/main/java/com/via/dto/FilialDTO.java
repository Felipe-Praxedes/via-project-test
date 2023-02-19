package com.via.dto;

import java.time.LocalDateTime;

import com.via.model.Filial;

import lombok.Data;

@Data
public class FilialDTO {

	private Long id;
	private String nome;
	private Long cnpj;
	private String cnpjFormatado; // Formatted CNPJ
	private String cidade;
	private String uf;
	private Filial.TipoFilial tipo;
	private boolean ativo;
	private LocalDateTime dataCadastro;
	private LocalDateTime ultimaAtualizacao;

	public FilialDTO(Filial filial) {
		this.id = filial.getId();
		this.nome = filial.getNome();
		this.cnpj = filial.getCnpj();
		this.cnpjFormatado = filial.getCnpjFormatado();
		this.cidade = filial.getCidade();
		this.uf = filial.getUf();
		this.tipo = filial.getTipo();
		this.ativo = filial.isAtivo();
		this.dataCadastro = filial.getDataCadastro();
		this.ultimaAtualizacao = filial.getUltimaAtualizacao();
	}
}
