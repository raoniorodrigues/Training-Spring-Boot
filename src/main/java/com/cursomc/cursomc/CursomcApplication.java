package com.cursomc.cursomc;

import com.cursomc.cursomc.domain.*;
import com.cursomc.cursomc.domain.enums.TipoCliente;
import com.cursomc.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository ;

	@Autowired
	private EnderecoRepository enderecoRepository ;



	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 7800.56);
		Produto p2 = new Produto(null, "NoteBook", 7800.56);
		Produto p3 = new Produto(null, "Mouse", 7800.56);
		Produto p4 = new Produto(null, "Cadeira Gamer", 7800.56);
		Produto p5 = new Produto(null, "Mesa Industrial", 7800.56);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p4, p5));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));

		Cliente cli1 = new Cliente(null, "Raoni", "raoni@gmail.cm", "22982977893", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("123245352","12323445"));

		Endereco e1 = new Endereco(null, "Rua Guanaraba", "14", "Apto 21", "Cohab", "06810060", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua da Piranboia", "666", "Sala 999", "Itaquaquecetuba", "23454567", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));


	}
}
