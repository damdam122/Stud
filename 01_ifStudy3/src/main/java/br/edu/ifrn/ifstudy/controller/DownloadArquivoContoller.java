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

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import br.edu.ifrn.ifstudy.dominio.Arquivo;
import br.edu.ifrn.ifstudy.repository.ArquivoRepository;



@Controller
public class DownloadArquivoContoller {
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	/**
	 * 
	 * @param idArquivo idArquivo é responsável por repassar a informação sobre o id do arquivo salvo no banco de dados. O id  se refere a chave primária da tabela de dados no banco de dados
	 * @param salvar serve para mapear fragmentos de caminho de URI variáveis ​​em sua chamada de método.
	 * @return quando retornar fará o download do arquivo
	 */
	
	@GetMapping("/download/{idArquivo}")
	public ResponseEntity <?> downloadFile(	
			@PathVariable Long idArquivo,
			@PathParam("salvar") String salvar
		){
		//carregando arquivo do banco de dados
		Arquivo arquivoBD = arquivoRepository.findById(idArquivo).get();
		
		String texto = (salvar == null || salvar.equals("true")) ? 
				"attachment; filename=\"" + arquivoBD.getNomeArquivo() + "\""
				:"inline; filename=\"" + arquivoBD.getNomeArquivo() + "\"";
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(arquivoBD.getTipoArquivo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, texto)
				.body(new ByteArrayResource(arquivoBD.getDados()));
	}
	

	
}
