package br.gov.sp.etec.produtoxpto.service;

import br.gov.sp.etec.produtoxpto.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    /**
     * Cadastra o produto. Se já existir um produto com o mesmo nome,
     * soma a quantidade ao estoque e atualiza o preço.
     *
     * @return true se um produto novo foi criado, false se o estoque de um existente foi atualizado
     */
    boolean cadastrarProduto(Produto p);

    List<Produto> listarProdutos();

    BigDecimal valorTotalEstoque(List<Produto> produtos);

    int quantidadeTotalEstoque(List<Produto> produtos);
}
