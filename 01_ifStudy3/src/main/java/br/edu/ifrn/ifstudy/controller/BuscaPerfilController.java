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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifrn.ifstudy.dominio.Relatar;
import br.edu.ifrn.ifstudy.dominio.Usuario;
import br.edu.ifrn.ifstudy.repository.RelatarRepository;
import br.edu.ifrn.ifstudy.repository.UsuarioRepository;

@Controller
//@RequestMapping("/usuarios")
public class BuscaPerfilController {
	
	@Autowired
	private RelatarRepository relatarRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

		@GetMapping("/buscaPerfil")
		public String buscarMaterias () {
			return "buscaPerfil";
		}
		
		/**
		 * @param materia responsavel por mapear os parâmetros de requisição da requisição GET/POST para o seu argumento de método
		 * @param model é usado para passar valores para renderizar uma visualização.
		 * @return retorna a pagina html “estatistica”
		 * Este método serve para fazer uma pesquisa/busca de um cadastro de relato salvo no banco de dados
		 */
		
		@GetMapping("/buscarRel")
		public String buscar(@RequestParam(name="materia",required= false) String materia, 
			 HttpSession sessao, ModelMap model) {
			
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			String email;    

			if (principal instanceof UserDetails) {
			    email = ((UserDetails)principal).getUsername();
			} else {
			    email = principal.toString();
			}
			
			Usuario u= usuarioRepository.findByEmail(email).get();
			
			List<Relatar> dadosEncontrados = relatarRepository.findByMateria(materia, u.getId());
			
			
			model.addAttribute("dadosEncontrados", dadosEncontrados);
			model.addAttribute("qtdQuestao", relatarRepository.findQtdQuestoesByUsuario(u.getId()));
			model.addAttribute("qtdConteudo", relatarRepository.findQtdConteudoByUsuario(u.getId()));
			model.addAttribute("qtdData", relatarRepository.findQtdDataByUsuario(u.getId()));
			model.addAttribute("qtdAcerto", relatarRepository.findQtdAcertoByUsuario(u.getId()));
			model.addAttribute("qtdErro", relatarRepository.findQtdErroByUsuario(u.getId()));
			model.addAttribute("qtdTempo", relatarRepository.findQtdtempoByUsuario(u.getId()));
			
			return "estatistica";
	}
		
		/**
		 * Editando os dados cadastrados
		 */
		
		/**
		 * @param idMateria serve como um trecho dinâmico da url. 
		 * @param model é usado para passar valores para renderizar uma visualização.
		 * @return retorna a pagina html “relatar”
		 * método utilizado para fazer uma edição nos dados da materia anteriormente salva no banco de dados
		 */
		
		/**
		 * método utilizado para fazer uma edição nos dados da materia
		 */
		@GetMapping("/editarRel/{idRelatar}")
		public String iniciarEdicao(@PathVariable ("idRelatar") Integer idMateria,
				ModelMap model, HttpSession sessao ) {
			
			Relatar m = relatarRepository.findById(idMateria).get();
			
			model.addAttribute("relatar",m);
			
			return "relatar";
		}
		
		/**
		 * @param idMateria serve como um trecho dinâmico da url. O  id permitirá que a informação de remoção ocorra de acordo com que a chave primária informar a qual linha da tabela Relatar precisa ser removido.
		 * @param attr servem para armazenar atributos de flash e eles serão propagados automaticamente
		 * @return redireciona a um mapeamento chamado “/buscaRel”
		 * Este método serve para a remoção de um cadastro feito de Relatar.
		 */
		
		/**
		 * Este método serve para a remoção de um cadastro feito de Relatar.
		 */
		
		@GetMapping("/remover/{idRelatar}")
		public String remover(@PathVariable("idRelatar") Integer idMateria, HttpSession sessao, RedirectAttributes attr) {
			
		relatarRepository.deleteById(idMateria);
		attr.addFlashAttribute("msgSucesso", "Dados removidos com sucesso" );
			
		return "redirect:/buscarRel";
		}
}
		

