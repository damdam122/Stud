package br.edu.ifrn.ifstudy.controller;

/**
 * 
 * #####################################
 * 
 * Objetivo:    Esta classe serve como um controller que será
 * o responsável por preparar um modelo de Mapa com os dados
 * a serem exibidos pela visualização do html.
 * 
 * @author Ana Beatriz (beatriz.monteiro@escolar.ifrn.edu.br)
 * Dâmarys Nascimento (damarys.n@escolar.ifrn.edu.br)
 * Klyfithon Paulo (klyfithon.p@escolar.ifrn.edu.br)
 * 
 * Data de Criação:    05/01/2022
 * 
 * #####################################
 * 
 * Última alteração:    
 * 
 * @author 
 * Data:    
 * Alteração:
 * 
 * #####################################
 * 
 * @version 1.0
 * 
 * #####################################
 * 
 */

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifrn.ifstudy.dominio.Relatar;
import br.edu.ifrn.ifstudy.dominio.Usuario;
import br.edu.ifrn.ifstudy.repository.RelatarRepository;

@Controller
@RequestMapping("/professor")
public class EstatisticaProfessorController {

	@Autowired
	private RelatarRepository relatarRepository;
	// @Autowired
	// private UsuarioRepository usuarioRepository;

	@GetMapping("/estatisticaProfessor/{id}")
	public String estatisticaProfessor(ModelMap model, @PathVariable("id") Integer idUsuario, 
			@RequestParam(name="nome", required=false) String nome) {
		model.addAttribute("id", idUsuario);
		
		
		model.addAttribute("nome", nome);
		model.addAttribute("qtdQuestao", relatarRepository.findQtdQuestoesByUsuario(idUsuario));
		model.addAttribute("qtdConteudo", relatarRepository.findQtdConteudoByUsuario(idUsuario));
		model.addAttribute("qtdData", relatarRepository.findQtdDataByUsuario(idUsuario));
		model.addAttribute("qtdAcerto", relatarRepository.findQtdAcertoByUsuario(idUsuario));
		model.addAttribute("qtdErro", relatarRepository.findQtdErroByUsuario(idUsuario));
		model.addAttribute("qtdTempo", relatarRepository.findQtdtempoByUsuario(idUsuario));
		return "estatisticaProfessor";

	}

	@GetMapping("/buscarRelatarAluno/{id}")
	public String buscar(@RequestParam(name = "materia", required = false) String materia, HttpSession sessao,
			ModelMap model, @PathVariable("id") Integer idUsuario) {

		List<Relatar> dadosEncontrados = relatarRepository.findByMateria(materia, idUsuario);
		model.addAttribute("id", idUsuario);
		model.addAttribute("dadosEncontrados", dadosEncontrados);
		model.addAttribute("qtdQuestao", relatarRepository.findQtdQuestoesByUsuario(idUsuario));
		model.addAttribute("qtdConteudo", relatarRepository.findQtdConteudoByUsuario(idUsuario));
		model.addAttribute("qtdData", relatarRepository.findQtdDataByUsuario(idUsuario));
		model.addAttribute("qtdAcerto", relatarRepository.findQtdAcertoByUsuario(idUsuario));
		model.addAttribute("qtdErro", relatarRepository.findQtdErroByUsuario(idUsuario));
		model.addAttribute("qtdTempo", relatarRepository.findQtdtempoByUsuario(idUsuario));

		return "estatisticaProfessor";
	}
}
