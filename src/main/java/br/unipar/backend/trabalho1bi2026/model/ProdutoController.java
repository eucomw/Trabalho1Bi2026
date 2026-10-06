package br.unipar.backend.trabalho1bi2026.model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.unipar.backend.trabalho1bi2026.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @GetMapping("/listar-todos")
    public ResponseEntity<List<Produto>> listarProdutos() {

        List<Produto> produtos = produtoRepository.findAll();

        return ResponseEntity.ok(produtos);
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {

        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/criar-produto")
    public ResponseEntity<Produto> criarProduto(
            @RequestBody(required = false) Produto produto) {

        if (produto == null) {
            return ResponseEntity.badRequest().build();
        }

        Produto produtoSalvo = produtoRepository.save(produto);

        return ResponseEntity.ok(produtoSalvo);
    }


    @PutMapping("/editar-produto/{id}")
    public ResponseEntity<Produto> editarProduto(
            @PathVariable Long id,
            @RequestBody Produto produto) {

        return produtoRepository.findById(id)
                .map(p -> {

                    p.setNome(produto.getNome());
                    p.setCategoria(produto.getCategoria());
                    p.setValor(produto.getValor());
                    p.setQuantidade(produto.getQuantidade());

                    Produto produtoAtualizado = produtoRepository.save(p);

                    return ResponseEntity.ok(produtoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/deletar-produto/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {

        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscar(
            @RequestParam("categoria") String categoria,
            @RequestParam("nome") String nome,
            @RequestParam("valorMax") BigDecimal valorMax) {

        List<Produto> resultado = produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .filter(p -> p.getValor().compareTo(valorMax) <= 0)
                .toList();

        return ResponseEntity.ok(resultado);
    }

     */

    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscar(
            @RequestParam("categoria") String categoria,
            @RequestParam("nome") String nome,
            @RequestParam("valorMax") BigDecimal valorMax) {

        List<Produto> resultado =
                produtoRepository
                        .findByCategoriaIgnoreCaseAndNomeIgnoreCaseAndValorLessThanEqual(
                                categoria,
                                nome,
                                valorMax
                        );

        return ResponseEntity.ok(resultado);
    }

}


