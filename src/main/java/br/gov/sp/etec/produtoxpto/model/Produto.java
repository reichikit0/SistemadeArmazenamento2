package br.gov.sp.etec.produtoxpto.model;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long v) {
        id = v;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String v) {
        nome = v;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal v) {
        preco = v;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer v) {
        quantidade = v;
    }

    /** Valor total em estoque deste produto (preço x quantidade). */
    public BigDecimal getValorTotal() {
        if (preco == null || quantidade == null) {
            return BigDecimal.ZERO;
        }
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
}
