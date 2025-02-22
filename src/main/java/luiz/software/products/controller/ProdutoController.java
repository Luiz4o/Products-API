package luiz.software.products.controller;


import java.util.List;

import luiz.software.products.entity.Produto;
import luiz.software.products.exception.ProductNullException;
import luiz.software.products.exception.ProductPriceException;
import luiz.software.products.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@PostMapping(value = "/save")
	public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) throws Exception {
		if(produto.getNome() == null || produto.getPreco() == null){
			throw new ProductNullException();
		}
		if(produto.getPreco() < 0){
			throw  new ProductPriceException();
		}

		produto = service.save(produto);

		return ResponseEntity.ok().body(produto);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscaProduto(@PathVariable Long id) {

		Produto produto = service.findById(id);

		return ResponseEntity.ok().body(produto);

	}

	@GetMapping
	public ResponseEntity<List<Produto>> buscaTodosProdutos() {

		List<Produto> produtos = service.findAll();

		return ResponseEntity.ok().body(produtos);

	}

}
