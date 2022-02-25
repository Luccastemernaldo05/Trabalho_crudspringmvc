package br.senai.sp.crudspringmvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.senai.sp.crudspringmvc.dao.ClienteDao;
import br.senai.sp.crudspringmvc.dao.ConnectionFactory;
import br.senai.sp.crudspringmvc.model.Cliente;
import br.senai.sp.crudspringmvc.model.Genero;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		ConnectionFactory.conectar();
		return "index";
	}

	@RequestMapping("index")
	public String view() {
		return "index";
	}

	@RequestMapping("cad_cliente")
	public String form(Model model) {

		model.addAttribute("genero", Genero.values());
		return "cad_cliente";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(Cliente c) {
		
		ClienteDao dao = new ClienteDao();
		if (c.getId() == null) {
			dao.inserir(c);
		} else {
			dao.atualizar(c);
		}
		return "redirect:cad_cliente";
	}

	@RequestMapping("listacliente")
	public String listar(Model model) {
		ClienteDao dao = new ClienteDao();
		model.addAttribute("cliente", dao.listar());
		return "listacliente";

	}

	@RequestMapping("excluirCliente")
	public String excluir(long idCliente) {
		ClienteDao dao = new ClienteDao();
		dao.excluir(idCliente);
		return "redirect:listacliente";
	}

	@RequestMapping("alterarCliente")
	public String alterar(long idCliente, Model model) {
		ClienteDao dao = new ClienteDao();
		model.addAttribute("cliente", dao.buscar(idCliente));

		return "forward:cad_cliente";
	}

	@RequestMapping("estatisticas")
	public String genero(Model model) {

		List<Cliente> cliente = new ClienteDao().listar();
		
		int countMasculino = 0;
		int countFeminino = 0;
		int countJovem = 0;
		int countAdulto = 0;
		int countIdoso = 0;
	
		for(Cliente c : cliente) {
			// If do gênero
			if(c.getGenero() == Genero.MASCULINO) {
				countMasculino++;
			
			}else {
				countFeminino++;
			}
			
			// If da idade
			if(c.getIdade() < 18)  {
				countJovem++;
			
			}else if(c.getIdade() < 50) {
				countAdulto++;				
					
			}else {
				countIdoso++;
			}
			
		}
		model.addAttribute("Masculino", countMasculino);
		model.addAttribute("Feminino", countFeminino);
		model.addAttribute("Jovem", countJovem);
		model.addAttribute("Adulto", countAdulto);
		model.addAttribute("Idoso", countIdoso);

		
		return "estatisticas";		
	
	}
	
	
}
