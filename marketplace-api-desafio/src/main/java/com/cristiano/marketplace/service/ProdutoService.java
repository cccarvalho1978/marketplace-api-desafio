package com.cristiano.marketplace.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cristiano.marketplace.domain.Categoria;
import com.cristiano.marketplace.domain.Produto;
import com.cristiano.marketplace.exception.ResourceNotFoundException;
import com.cristiano.marketplace.repository.CategoriaRepository;
import com.cristiano.marketplace.repository.ProdutoRepository;

@Service
public class ProdutoService {

	// Log to show the behavior of the methods
	private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Find product by id
	 * @param idProduct
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Produto findProductById(Long idProduct) throws ResourceNotFoundException {
		log.info("Finding product {} ", idProduct);
		Produto retorno = validateProduct(idProduct);
		return retorno;
	}

	/**
	 * Find all products 
	 * @return
	 */
	public List<Produto> findAll(){
		log.info("Finding all products");
		return (List<Produto>) produtoRepository.findAll();
	}

	/**
	 * Save product
	 * @param product
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Produto saveProduct(Produto product) throws ResourceNotFoundException{
		log.info("Save product");
		
		// Validate category
		Optional<Categoria> retorno = categoriaRepository.findById(product.getCategoria().getId());
		if(retorno.isEmpty()) {
			throw new ResourceNotFoundException("Categoria não foi encontrada ( "+product.getCategoria().getId()+" )");
		}
		
		// Setting the category
		product.setCategoria(retorno.get());
		
		// update
		if(product.getId()!=null) {
			// Validate product
			Produto p = validateProduct(product.getId());
			product.setScore(p.getScore());
			product.setDataCriacao(p.getDataCriacao());
		// insert	
		}else {
			product.setDataCriacao(new Date());
		}
		
		return produtoRepository.save(product);
	}

	/**
	 * Delete product
	 * @param idProduct
	 * @throws ResourceNotFoundException
	 */
	public void deleteProduct(Long idProduct) throws ResourceNotFoundException{
		log.info("Delete product {}", idProduct);
		
		// Validate product
		Produto product = validateProduct(idProduct);
		
		produtoRepository.delete(product);
		
	}
	
	/**
	 * Validates and returns the product
	 * @param idProduct
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Produto validateProduct(Long idProduct) throws ResourceNotFoundException {
		Optional<Produto> retorno = produtoRepository.findById(idProduct); 
		if(retorno.isEmpty()) {
			throw new ResourceNotFoundException("Produto não foi encontrado ( "+idProduct+")");
		}
		return retorno.get();
	}
	
	/**
	 * Search product by name
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Produto> searchProduct(String name, int page, int size) {
		log.info("Search products name {}", name);
		
		// Order by score, nome and categoria.nome
		PageRequest pageRequest 
		= PageRequest.of(page, size, Sort.Direction.ASC, "score", "nome", "categoria.nome");

		Page<Produto> produtos = produtoRepository.search(name.toLowerCase(), pageRequest);

		return produtos;
	}
}
