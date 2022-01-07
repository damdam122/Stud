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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Arquivo;
import br.edu.ifrn.ifstudy.dominio.Relatar;
import br.edu.ifrn.ifstudy.dominio.Usuario;
import br.edu.ifrn.ifstudy.repository.ArquivoRepository;
import br.edu.ifrn.ifstudy.repository.RelatarRepository;
import br.edu.ifrn.ifstudy.repository.UsuarioRepository;

@Controller
//@RequestMapping("/relato")
public class RelatarController {
	
	/**
	 * Criei um novo atributo que toda vez que o form for salvo armazena novos dadosna classe java O ModelMap serve para enviar novas informações
	 */
	

	@Autowired
	private RelatarRepository relatarRepository;

	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/relatar")
	public String relatar(ModelMap model) {
		model.addAttribute("relatar", new Relatar());

		return "relatar";
	}
	
	/**
	 * 
	 * @param relatar parâmetro criado a partir da classe Relatar para servir de variável de armazenamento de dados
	 * @param result1 serve para verificar se há algum erro na inserção de dados
	 * @param model mapeia o objeto do método
	 * @return retorna a página do html “relatar”
	 * Este método tem a função de salvar o cadastro do Relatar. Ou seja a cada estudo do aluno quando clicar para salvar essas informações serão enviadas ao banco de dados
	 */


	@PostMapping("/salvarRel")
	@Transactional(readOnly = false)
	public String salvar(@Valid Relatar relatar, BindingResult result1, RedirectAttributes attr, HttpSession sessao,
			ModelMap model, @RequestParam("file") MultipartFile[] file) throws IOException {

		if (result1.hasErrors()) {
			return "relatar";
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String email;

		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		Usuario u = usuarioRepository.findByEmail(email).get();
		relatar.setUsuario(u);

		List<Arquivo> arquivos = new ArrayList<Arquivo>();
		for (int i = 0; i < file.length; i++) {
			MultipartFile arquivo = file[i];
			String nomeDocumento = StringUtils.cleanPath(arquivo.getOriginalFilename());
			String nomeFoto = StringUtils.cleanPath(arquivo.getOriginalFilename());

			Arquivo arquivoBD = new Arquivo(null, nomeDocumento, arquivo.getContentType(), arquivo.getBytes());

			arquivos.add(arquivoBD);

		}

		arquivoRepository.saveAll(arquivos);
		
		/**
		* serve para cadastrar e editar os dados
		*
		*/
		relatar.setArquivos(arquivos);
		
		relatarRepository.save(relatar);

		attr.addFlashAttribute("msgSucesso", "Dados salvos com sucesso");

		attr.addFlashAttribute("relatar", new Relatar());
		
		

		return "redirect:/relatar";
	}

}
