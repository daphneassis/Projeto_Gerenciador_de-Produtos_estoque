package com.polotech.t924.grupo1.projetofinal.model;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    public void setCategoria(CategoriaProduto categoria){
        this.categoria = categoria;
    }

}
