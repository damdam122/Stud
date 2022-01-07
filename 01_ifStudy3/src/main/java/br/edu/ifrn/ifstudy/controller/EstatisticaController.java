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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifrn.ifstudy.dominio.Usuario;
import br.edu.ifrn.ifstudy.repository.RelatarRepository;
import br.edu.ifrn.ifstudy.repository.UsuarioRepository;

@Controller
public class EstatisticaController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RelatarRepository relatarRepository;
	
	@GetMapping("/estatistica")
	public String estatistica (ModelMap model) {
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String email;    

		if (principal instanceof UserDetails) {
			
		    email = ((UserDetails)principal).getUsername();
		} else {
		    email = principal.toString();
		}
		Usuario u= usuarioRepository.findByEmail(email).get();
		
		model.addAttribute("qtdQuestao", relatarRepository.findQtdQuestoesByUsuario(u.getId()));
		model.addAttribute("qtdConteudo", relatarRepository.findQtdConteudoByUsuario(u.getId()));
		model.addAttribute("qtdData", relatarRepository.findQtdDataByUsuario(u.getId()));
		model.addAttribute("qtdAcerto", relatarRepository.findQtdAcertoByUsuario(u.getId()));
		model.addAttribute("qtdErro", relatarRepository.findQtdErroByUsuario(u.getId()));
		model.addAttribute("qtdTempo", relatarRepository.findQtdtempoByUsuario(u.getId()));
		
		if(u.getPerfil().equals(Usuario.ALUNO)){
			return "estatistica";
			} return "buscaAluno";
	}
	@GetMapping("/estatisticaProfessor/{id}")
	public String estatisticaProfessor (ModelMap model, @PathVariable("id") Integer idUsuario) {
		
		
		model.addAttribute("qtdQuestao", relatarRepository.findQtdQuestoesByUsuario(idUsuario));
		model.addAttribute("qtdConteudo", relatarRepository.findQtdConteudoByUsuario(idUsuario));
		model.addAttribute("qtdData", relatarRepository.findQtdDataByUsuario(idUsuario));
		model.addAttribute("qtdAcerto", relatarRepository.findQtdAcertoByUsuario(idUsuario));
		model.addAttribute("qtdErro", relatarRepository.findQtdErroByUsuario(idUsuario));
		model.addAttribute("qtdTempo", relatarRepository.findQtdtempoByUsuario(idUsuario));
		
		
			return "estatisticaProfessor";
			
	}
	
}
