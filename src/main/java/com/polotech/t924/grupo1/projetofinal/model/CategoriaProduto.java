package com.polotech.t924.grupo1.projetofinal.model;

public enum CategoriaProduto {
    ELETRONICO("eletronico"), ELETRODOMESTICO("eletrodomestico"),INFORMATICA("informatica"),SMARTPHONE("smartphone");
    private String value;
    private CategoriaProduto(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
