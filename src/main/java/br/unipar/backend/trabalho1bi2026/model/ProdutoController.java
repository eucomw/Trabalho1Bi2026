package br.unipar.backend.trabalho1bi2026.model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    List<Produto> produtos = new ArrayList<>();

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar-posicao/{posicao}")
    public ResponseEntity<Produto>buscarPosicao(@PathVariable("posicao") int posicao){
        return ResponseEntity.ok(produtos.get(posicao));
    }

    @PostMapping("/criar-produto")
    public ResponseEntity<Produto> criarProduto(@RequestBody(required = false) Produto produto){
    if (produto == null) {
        return ResponseEntity.badRequest().build();
    }
    produtos.add(produto);
    return ResponseEntity.ok(produto);
    }

    @PutMapping("/editar-produto/{posicao}")
    public ResponseEntity<Produto>editarProduto(@PathVariable int posicao,
                                                @RequestBody Produto produto){
        Produto p = produtos.get(posicao);

        p.setNome(produto.getNome());
        p.setCategoria(produto.getCategoria());
        p.setValor(produto.getValor());
        p.setQuantidade(produto.getQuantidade());

        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/deletar-produto/{posicao}")
    public ResponseEntity<Produto>deletarProduto(@PathVariable("posicao") int posicao){
        return ResponseEntity.ok(produtos.remove(posicao));
    }

    @GetMapping("/buscar-categoria")
    public ResponseEntity<List<Produto>>buscarPorCategoria(
            @RequestParam("categoria") String categoria){
    List<Produto> resultado = produtos.stream()
            .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
            .toList();
        return ResponseEntity.ok(resultado);
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

        List<Produto> resultado = produtos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .filter(p -> p.getValor().compareTo(valorMax) <= 0)
                .toList();

        return ResponseEntity.ok(resultado);
    }

}


