package br.edu.ifrn.ifstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.ifstudy.dominio.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}

