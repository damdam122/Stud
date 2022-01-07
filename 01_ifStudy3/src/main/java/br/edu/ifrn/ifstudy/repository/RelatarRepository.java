package br.edu.ifrn.ifstudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifrn.ifstudy.dominio.Relatar;

public interface RelatarRepository extends JpaRepository<Relatar, Integer> {

	@Query("select m from Relatar m where m.materia like %:materia% " + "and m.usuario.id=:idUsuario")
	List<Relatar> findByMateria(@Param("materia") String materia, @Param("idUsuario") int id);

	@Query("select sum(m.numeroQuestao) from Relatar m where m.usuario.id=:idUsuario")
	Integer findQtdQuestoesByUsuario(@Param("idUsuario") int id);
	
	@Query("select sum(m.numeroAcerto) from Relatar m where m.usuario.id=:idUsuario")
	Integer findQtdAcertoByUsuario(@Param("idUsuario") int id);
	
	@Query("select sum(m.numeroErro) from Relatar m where m.usuario.id=:idUsuario")
	Integer findQtdErroByUsuario(@Param("idUsuario") int id);
	
	@Query("select sum(m.duracao) from Relatar m where m.usuario.id=:idUsuario")
	Integer findQtdtempoByUsuario(@Param("idUsuario") int id);
	
	@Query("select count(m.idRelatar) from Relatar m where m.usuario.id=:idUsuario")
	Integer findQtdConteudoByUsuario(@Param("idUsuario") int id);
	
	@Query("select count(distinct m.data) from Relatar m where m.usuario.id=:idUsuario")
	Integer findQtdDataByUsuario(@Param("idUsuario") int id);

}
