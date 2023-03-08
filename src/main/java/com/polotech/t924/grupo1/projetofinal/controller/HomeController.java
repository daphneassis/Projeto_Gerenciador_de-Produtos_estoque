package com.polotech.t924.grupo1.projetofinal.controller;

import com.polotech.t924.grupo1.projetofinal.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = {"/","/home"})
    public String home() {
        return "home";
    }
}
