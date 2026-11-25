package br.gov.sp.etec.produtoxpto.repository;

import br.gov.sp.etec.produtoxpto.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findFirstByNomeIgnoreCase(String nome);

    List<ProdutoEntity> findAllByOrderByNomeAsc();
}
