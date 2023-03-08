package com.polotech.t924.grupo1.projetofinal.dto;

import com.polotech.t924.grupo1.projetofinal.model.CategoriaProduto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequest {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;

}
