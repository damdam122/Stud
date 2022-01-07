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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

	@GetMapping("/login")
	public String login () {
		return "login";
	}
	
	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("msgErro", "Login ou senha incorretos.Tente novamente.");
		return "login";
	}
}
