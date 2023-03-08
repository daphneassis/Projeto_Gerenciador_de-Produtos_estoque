package com.polotech.t924.grupo1.projetofinal.service;


import com.polotech.t924.grupo1.projetofinal.model.CategoriaProduto;
import com.polotech.t924.grupo1.projetofinal.model.Produto;
import com.polotech.t924.grupo1.projetofinal.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto create(Produto produto){
    return produtoRepository.save(produto);
}

    public Produto update(Produto produto){
   produtoRepository.findById(produto.getId())
           .orElseThrow(() -> new IllegalArgumentException("Produto com Id: "+ produto.getId() +" não encontrado"));
     return produtoRepository.save(produto);
}
    public List<Produto> categoria(String categoria){
    CategoriaProduto categoriaProduto = CategoriaProduto.valueOf(categoria.toUpperCase());
    List<Produto> produtos = produtoRepository.findByCategoria(categoriaProduto);
    return produtos;
}
    public Produto buscarPorId (String id){
    return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> buscarPorNome(String nome) {
    List<Produto> produtosEncontrados = produtoRepository.findByNomeContainingIgnoreCase(nome);
    if(produtosEncontrados.isEmpty()) {
    Collections.emptyList();
        }
    return produtosEncontrados;
    }

    public List<Produto> buscarTodos(){
    List<Produto> produtos = produtoRepository.findAll();
    return produtos;
    }

    public Produto delete(String id){
    Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    produtoRepository.delete(produto);
    return produto;
    }
}
