package br.gov.sp.etec.produtoxpto.controller;

import br.gov.sp.etec.produtoxpto.model.Produto;
import br.gov.sp.etec.produtoxpto.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // TELA 1: cadastro (nome, preço e quantidade)
    @GetMapping({"/", "/produtos/cadastrar"})
    public String formulario(Model m) {
        if (!m.containsAttribute("produto")) {
            m.addAttribute("produto", new Produto());
        }
        return "cadastro-produto";
    }

    @PostMapping("/produtos/cadastrar")
    public String cadastrar(Produto produto, Model m, RedirectAttributes ra) {
        String erro = validar(produto);
        if (erro != null) {
            m.addAttribute("erro", erro);
            return "cadastro-produto";
        }

        boolean novo = service.cadastrarProduto(produto);
        ra.addFlashAttribute("mensagem", novo
                ? "Produto cadastrado com sucesso!"
                : "Produto já existia: a quantidade foi somada ao estoque e o preço atualizado.");
        return "redirect:/produtos";
    }

    // TELA 2: estoque (nome, quantidade, valor unitário e valor total)
    @GetMapping("/produtos")
    public String estoque(Model m) {
        List<Produto> produtos = service.listarProdutos();
        m.addAttribute("produtos", produtos);
        m.addAttribute("valorTotalEstoque", service.valorTotalEstoque(produtos));
        m.addAttribute("quantidadeTotal", service.quantidadeTotalEstoque(produtos));
        return "estoque";
    }

    private String validar(Produto p) {
        if (p.getNome() == null || p.getNome().isBlank()) {
            return "Informe o nome do produto.";
        }
        if (p.getPreco() == null || p.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            return "Informe um preço maior que zero.";
        }
        if (p.getQuantidade() == null || p.getQuantidade() < 0) {
            return "Informe uma quantidade válida (0 ou mais).";
        }
        return null;
    }
}
