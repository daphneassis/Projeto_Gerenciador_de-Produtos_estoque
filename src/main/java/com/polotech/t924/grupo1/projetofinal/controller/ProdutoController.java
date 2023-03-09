package com.polotech.t924.grupo1.projetofinal.controller;

import com.polotech.t924.grupo1.projetofinal.dto.ProdutoRequest;
import com.polotech.t924.grupo1.projetofinal.dto.ProdutoResponse;
import com.polotech.t924.grupo1.projetofinal.model.Produto;
import com.polotech.t924.grupo1.projetofinal.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public String novo(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoRequest, produto);
        produto = produtoService.create(produto);
        return produto.getId();
    }
 private ProdutoResponse produtoToProdutoResponse(Produto produto){
     ProdutoResponse produtoResponse = new ProdutoResponse();
     BeanUtils.copyProperties(produto, produtoResponse);
     return produtoResponse;
 }

    @GetMapping
    public List<ProdutoResponse> readAll() {
        return produtoService.buscarTodos().stream().map(this::produtoToProdutoResponse).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public ProdutoResponse buscarPorId(@PathVariable(name = "id") String id) {
        Produto produto = produtoService.buscarPorId(id);
        return produtoToProdutoResponse(produto);
    }
    @GetMapping("/cat/{categoria}")
    public List <ProdutoResponse> buscarPorCategoria(@RequestParam (name = "categoria") String categoria) {
        List <Produto> listaProdutos = produtoService.buscarPorCategoria(categoria);
        return listaProdutos.stream().map(this::produtoToProdutoResponse).collect(Collectors.toList());
    }
    @GetMapping("/nome/{nome}")
    public List <ProdutoResponse> buscarPorNome(@RequestParam String nome) {
        List<Produto> listaProdutos = produtoService.buscarPorNome(nome);
        return listaProdutos.stream().map(this::produtoToProdutoResponse).collect(Collectors.toList());
    }

    @PutMapping
    public ProdutoResponse alterarProduto(@PathVariable(name = "id") String id, @RequestBody ProdutoRequest produtoRequest){
       Produto produto= produtoService.buscarPorId(id);
       produto.setNome(produtoRequest.getNome());
       produto.setDescricao(produtoRequest.getDescricao());
       produto.setPreco(produtoRequest.getPreco());
       produto.setCategoria(produtoRequest.getCategoria());
       produto = produtoService.update(produto);
       ProdutoResponse produtoResponse = new ProdutoResponse();
       BeanUtils.copyProperties(produto, produtoResponse);
       return produtoResponse;
    }

    @DeleteMapping
    public void delete(@PathVariable("id") String id) {
        produtoService.delete(id);
    }

}
