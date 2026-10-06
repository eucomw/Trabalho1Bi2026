package br.unipar.backend.trabalho1bi2026.repository;

import br.unipar.backend.trabalho1bi2026.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaIgnoreCase(String categoria);

    List<Produto> findByCategoriaIgnoreCaseAndNomeIgnoreCaseAndValorLessThanEqual(
            String categoria,
            String nome,
            BigDecimal valor
    );
}