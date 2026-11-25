package br.gov.sp.etec.produtoxpto.service;

import br.gov.sp.etec.produtoxpto.entity.ProdutoEntity;
import br.gov.sp.etec.produtoxpto.model.Produto;
import br.gov.sp.etec.produtoxpto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean cadastrarProduto(Produto p) {
        String nome = p.getNome().trim();
        BigDecimal preco = p.getPreco().setScale(2, RoundingMode.HALF_UP);

        Optional<ProdutoEntity> existente = repository.findFirstByNomeIgnoreCase(nome);
        if (existente.isPresent()) {
            ProdutoEntity e = existente.get();
            e.setQuantidade(e.getQuantidade() + p.getQuantidade());
            e.setPreco(preco);
            repository.save(e);
            return false;
        }

        ProdutoEntity e = new ProdutoEntity();
        e.setNome(nome);
        e.setPreco(preco);
        e.setQuantidade(p.getQuantidade());
        repository.save(e);
        return true;
    }

    @Override
    public List<Produto> listarProdutos() {
        return repository.findAllByOrderByNomeAsc().stream().map(this::toProduto).toList();
    }

    @Override
    public BigDecimal valorTotalEstoque(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int quantidadeTotalEstoque(List<Produto> produtos) {
        return produtos.stream().mapToInt(Produto::getQuantidade).sum();
    }

    private Produto toProduto(ProdutoEntity e) {
        Produto p = new Produto();
        p.setId(e.getId());
        p.setNome(e.getNome());
        p.setPreco(e.getPreco());
        p.setQuantidade(e.getQuantidade());
        return p;
    }
}
