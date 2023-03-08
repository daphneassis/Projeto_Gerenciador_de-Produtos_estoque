package com.polotech.t924.grupo1.projetofinal.repository;

import com.polotech.t924.grupo1.projetofinal.model.CategoriaProduto;
import com.polotech.t924.grupo1.projetofinal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, String> {
    List<Produto> findByCategoria(CategoriaProduto categoriaProduto);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
