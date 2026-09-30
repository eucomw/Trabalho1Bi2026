package br.unipar.backend.trabalho1bi2026.model;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Produto {
    public String nome;
    public String categoria;
    public int quantidade;
    public BigDecimal valor;
}
