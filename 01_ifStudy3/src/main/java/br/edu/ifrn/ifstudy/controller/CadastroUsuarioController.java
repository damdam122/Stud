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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Usuario;
import br.edu.ifrn.ifstudy.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/cadastro")
	public String entrarCadastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/cadastro";
	}

	// verificando se o email já existe no bd
	@Transactional(readOnly = true)
	private Boolean validandoEmail(Usuario usuario) {
		// buscando o email no bd
		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());

		// se o email já estiver cadastrado vai retornar false
		if (user.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param usuario serve para fazer a validação do usuário no método salvar ao verificar a partir do email
	 * @param result  serve para verificar se há algum erro na inserção de dados
	 * @param model mapeia o objeto do método
	 * @return retorna a página do html “relatar”
	 * Este método tem a função de salvar o usuario.Pois só cadastrado terá acesso ao sistema.
	 */


	
	@PostMapping("/salvar")
	public String Salvar(@Valid Usuario usuario, BindingResult result, HttpSession sessao, RedirectAttributes attr,
			ModelMap model) {
		
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}
		// verificando email
		if(validandoEmail(usuario)) {
			model.addAttribute("msgErro", "O email inserido já existe. Por favor, insira outro");
			return "usuario/cadastro";
		}
		// criptografando a senha
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);

		usuarioRepository.save(usuario);
		// attr.addFlashAttribute("msgSucesso", "Operação realizada com sucesso!");
		attr.addFlashAttribute("msgSucesso", usuario.getSenha());
		System.out.println("Senha: " + usuario.getSenha());

		return "login";

	}

	@ModelAttribute("escolaridade")
	public List<String> getEscolaridade() {

		return Arrays.asList("Ensino Fundamental", "Ensino Médio", "Superior (graduação)", "outra");
	}
	
	/**
	 * @param idUsuario serve para editar as informações inseridas a partir do id cadastrado
	 * @return retorna o mapeamento /usuario/cadastro“
	 *Este método permite a edição dos cadastros de Usuario. A edição pode acontecer de qualquer informação antes adicionada.
	 */

	@GetMapping("/editar/{id}")
	public String iniciarEdicao(@PathVariable("id") Integer idUsuario, ModelMap model, HttpSession sessao) {

		Usuario u = usuarioRepository.findById(idUsuario).get();

		model.addAttribute("usuario", u);

		return "/usuario/cadastro";
	}

}
