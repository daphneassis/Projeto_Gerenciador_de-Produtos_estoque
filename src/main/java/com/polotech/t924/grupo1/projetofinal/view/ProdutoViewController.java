package com.polotech.t924.grupo1.projetofinal.view;

import com.polotech.t924.grupo1.projetofinal.controller.ProdutoController;
import com.polotech.t924.grupo1.projetofinal.dto.ProdutoRequest;
import com.polotech.t924.grupo1.projetofinal.dto.ProdutoResponse;

import com.polotech.t924.grupo1.projetofinal.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProdutoViewController {
    private final ProdutoController produtoController;

    @PostMapping("/add")
    public String novo(ProdutoResponse produtoResponse, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }
        ProdutoRequest produtoRequest = new ProdutoRequest();
        BeanUtils.copyProperties(produtoResponse, produtoRequest);
        produtoController.novo(produtoRequest);
        return "redirect:/listar";
    }

    @GetMapping("deletar/{id}")
    public String deletar(@PathVariable(name = "id") String id, Model model) {
        produtoController.delete(id);
        return "redirect:/listar";
    }

    @PostMapping("update/{id}")
    public String alterarProduto(@PathVariable(name = "id") String id, ProdutoResponse produtoResponse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/form";
        }
        ProdutoRequest produtoRequest = new ProdutoRequest();
        BeanUtils.copyProperties(produtoResponse, produtoRequest);
        produtoController.alterarProduto(produtoResponse.getId(), produtoRequest);
        return "redirect:/listar";
    }

    @RequestMapping(value = {"/listar"})
    public String listar(Model model) {
        model.addAttribute("produtos", produtoController.readAll());
        return "todosprodutos";
    }
}

/*
 Códigos tentados:
 Para o Get read:
     @GetMapping("/todosprodutos")
    public ModelAndView readByName(@PathVariable(name = "nome") String nome) {
        List<ProdutoResponse> produtosResponse = new ArrayList<>();
        produtosResponse= produtoController.buscaPorNome(nome);
        ModelAndView mv = new ModelAndView("/buscarPorNome");
        if (produtosResponse.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: ");
        } else {
            mv.addObject("produtos", produtosResponse);
        }
        return mv;
    }
}

    @GetMapping("buscarPorNome/{nome}")
    public ModelAndView readByName(@PathVariable(name = "nome") String nome) {
        ModelAndView mv = new ModelAndView("/buscarPorNome");
        List<ProdutoResponse> listaProdutosPorNome = new ArrayList<>();
        produtoController.buscaPorNome(nome).stream().forEach(produtoResponse -> listaProdutosPorNome.add(produtoResponse));
        if (listaProdutosPorNome.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: ");
        } else {
            mv.addObject("produtos", listaProdutosPorNome);
        }
        return mv;
    }

  @GetMapping("/produto-new")
    public String visualizarNovoProduto(Model model, ProdutoResponse produtoResponse) {
        model.addAttribute("produto", produtoResponse);
        return "produto-create";
    }

    @GetMapping // alterado
    public ProdutoResponse buscaPorNome(@PathVariable String nome){
        List<Produto> produto= produtoService.buscarPorNome(nome);
        ProdutoResponse produtoResponse= new ProdutoResponse();
        BeanUtils.copyProperties(produto, produtoResponse);
        return produtoResponse;
    }

      <div th:switch="${#lists.size(produtos)}">
        <h2 th:case="0">Não foi registrado nenhum produto!</h2>
        <div th:case="*">
          <h2>Produtos</h2>


        @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome(String nome) {
        ModelAndView mv = new ModelAndView("/buscarPorNome");
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        if (produtos.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: ");
        } else {
            mv.addObject("produtos", produtos);
        }
        return mv;
    }

     @GetMapping({"id"})
        @CacheEvict(value = "produtos", allEntries = true)
    public String delete(@PathVariable(name = "id") String id, Model model) {
        produtoService.delete(id);
        return "redirect:/listar";*/



