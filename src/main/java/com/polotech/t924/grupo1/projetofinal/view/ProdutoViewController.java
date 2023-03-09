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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/form")
    public String produtosForm(Produto produto) {
        return "addProdutoForm";
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

    @GetMapping("/categoria")
    public String categoria(@RequestParam String categoria, Model model) {
        model.addAttribute("produtos", produtoController.buscarPorCategoria(categoria));
        return "categoria";
    }

    @GetMapping("form/{id}")//alterado e ok
    public String updateForm(Model model, @PathVariable(name = "id") String id) {
        ProdutoResponse produtoResponse = produtoController.buscarPorId(id);
        model.addAttribute("produto", produtoResponse);
        return "atualizaForm";
    }

    @GetMapping("/buscarPorNome") // a ver
    public ModelAndView buscarPorNome(String nome){
        ModelAndView mv = new ModelAndView("/buscarPorNome");
        List<ProdutoResponse> produtoResponses = produtoController.buscarPorNome(nome);
        if (produtoResponses.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: ");
        } else {
            mv.addObject("produtos", produtoResponses);
        }
        return mv;
    }

    @RequestMapping(value = {"/listar"})
    public String listar(Model model) {
        model.addAttribute("produtos", produtoController.readAll());
        return "todosprodutos";
    }
}

