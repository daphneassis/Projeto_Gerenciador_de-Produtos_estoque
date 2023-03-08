package com.polotech.t924.grupo1.projetofinal.controller;

import com.polotech.t924.grupo1.projetofinal.dto.ProdutoRequest;
import com.polotech.t924.grupo1.projetofinal.dto.ProdutoResponse;
import com.polotech.t924.grupo1.projetofinal.model.Produto;
import com.polotech.t924.grupo1.projetofinal.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    // Acessa o formulario
    @GetMapping("/form")
    public String produtosForm(Produto produto) {
        return "addProdutoForm";
    }

    // Adiciona novo produto
    @PostMapping // alterado e ok
    public String novo(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoRequest, produto);
        produto = produtoService.create(produto);
        return produto.getId();
    }

    @GetMapping  //alterado e ok
    public List<ProdutoResponse> readAll() {
        return produtoService.buscarTodos().stream().map(produto -> {
            ProdutoResponse produtoResponse = new ProdutoResponse();
            BeanUtils.copyProperties(produto, produtoResponse);
            return produtoResponse;
        }).collect(Collectors.toList());
    }

    // Acessa o formulario de edição
    @GetMapping("form/{id}")//alterado e ok
    public String updateForm(Model model, @PathVariable(name = "id") String id) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);
        return "atualizaForm";
    }

    // Busca por categoria
    @GetMapping("/categoria")
    public String categoria(@RequestParam String categoria, Model model) {
        model.addAttribute("produtos", produtoService.categoria(categoria));
        return "categoria";
    }


    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome(String nome){ModelAndView mv = new ModelAndView("/buscarPorNome");
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        if (produtos.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: ");
        } else {
            mv.addObject("produtos", produtos);
        }
        return mv;
    }

    /*
    Código tentado para o Get buscaPorNome:
     @GetMapping// alterado e dando erro
    public List<ProdutoResponse>buscaPorNome(@PathVariable(name = "nome") String nome) {
        return produtoService.buscarPorNome(nome).stream().map(produto -> {
            ProdutoResponse produtoResponse = new ProdutoResponse();
            BeanUtils.copyProperties(produto, produtoResponse);
            return produtoResponse;
        }).collect(Collectors.toList());
    }

     */

    @PutMapping// alterado e ok
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

    @DeleteMapping // alterado e ok
    public void delete(@PathVariable("id") String id) {
        produtoService.delete(id);
    }

}
