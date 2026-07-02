package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Produto {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) Tirei o controle automático do banco e fiz o Java calcular o ID antes de salvar.
    private Long id;

    private String modelo;
    private String marca;
    private Integer ano;
    private String cor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
}
