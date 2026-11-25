# produtoxpto — Cadastro e controle de estoque

Sistema web simples para cadastrar produtos (energéticos) e acompanhar o estoque.

**Tecnologias:** Java 25, Spring Boot 4.1, Spring MVC, Thymeleaf, Spring Data JPA e banco H2 (arquivo local).

## Telas

| Tela | Rota | O que mostra |
|------|------|--------------|
| 1 — Cadastro | `/` ou `/produtos/cadastrar` | Formulário com nome, preço e quantidade |
| 2 — Estoque | `/produtos` | Nome, quantidade, valor unitário e valor total de cada produto, com o total geral |

Se o nome do produto já existir (sem diferenciar maiúsculas/minúsculas), a quantidade informada é **somada** ao estoque e o preço é atualizado.

## Como executar

```bash
./mvnw spring-boot:run      # Linux/macOS
mvnw.cmd spring-boot:run    # Windows
```

Acesse <http://localhost:8080>.

O console do H2 fica em <http://localhost:8080/h2-console> com a URL `jdbc:h2:file:./database/produtoxpto`, usuário `sa` e senha vazia.

## Estrutura

```
src/main/java/br/gov/sp/etec/produtoxpto
├── controller   # ProdutoController (telas 1 e 2)
├── service      # regras de cadastro e cálculo dos totais
├── repository   # ProdutoRepository (JPA)
├── entity       # ProdutoEntity (tabela tb_produto)
└── model        # Produto (dados do formulário e da listagem)
```
