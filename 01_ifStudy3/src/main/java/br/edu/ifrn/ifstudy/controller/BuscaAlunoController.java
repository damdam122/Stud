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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Usuario;
import br.edu.ifrn.ifstudy.repository.UsuarioRepository;



@Controller
@RequestMapping("/professor")
public class BuscaAlunoController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/buscaAluno")
	public String buscaAluno () {
		return "buscaAluno";
	}
	
	/**
	 * 
	 * @param nome serve para extrair o  parâmetro nome da lista de usuario.
	 * @param email serve para extrair o  parâmetro email da lista de usuario
	 * @param mostrarTodosDados serve para extrair o parâmetro mostrarTodosDados da lista de usuario
	 * @param model serve para armazenar atributos em um mapa e chamadas de método em cadeia
	 * @return retorna o html buscaAluno
	 * Este método é responsável por fazer uma busca do usuario através dos parâmetros nome ou email.
	 */

	
	@GetMapping("/buscar")
	public String buscar(
			@RequestParam(name="nome", required=false) String nome, 
			@RequestParam(name="email", required=false) String email, 
			@RequestParam (name= "mostrarTodosDados", required = false) //
			Boolean mostrarTodosDados,
			ModelMap model) {
		
		List<Usuario> usuariosEncontrados= 
				usuarioRepository.findByEmailAndNome(email, nome);
		
		model.addAttribute("usuariosEncontrados", usuariosEncontrados);
		
		if(mostrarTodosDados != null) {
			model.addAttribute("mostrarTodosDados", true);
		}
		
		return "buscaAluno";
	}

	/**
	 * 
	 * @param idUsuario responsável por repassar a informação sobre o id do usuario salva no banco de dados 
	 * @param attr servem para armazenar atributos de flash e eles serão propagados automaticamente
	 * @return redireciona a um mapeamento chamado “/professor/buscar”
	 * Este método tem a finalidade de remover os usuarios através do id
	 */

	
	@GetMapping("/remover/{id}")
	public String remover(
				@PathVariable("id") Integer idUsuario,
				HttpSession sessao,
				RedirectAttributes attr
			) {
		
		
		usuarioRepository.deleteById(idUsuario);
		attr.addFlashAttribute("msgSucesso", "Usuario removido com sucesso!");
			
		
		
		return "redirect:/professor/buscar";
	}
	
}
