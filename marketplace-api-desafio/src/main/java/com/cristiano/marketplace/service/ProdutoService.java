package com.cristiano.marketplace.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cristiano.marketplace.domain.Categoria;
import com.cristiano.marketplace.domain.Produto;
import com.cristiano.marketplace.exception.ResourceNotFoundException;
import com.cristiano.marketplace.repository.AvaliacaoItemVendaRepository;
import com.cristiano.marketplace.repository.CategoriaRepository;
import com.cristiano.marketplace.repository.ItemVendaRepository;
import com.cristiano.marketplace.repository.NoticiaCategoriaRepository;
import com.cristiano.marketplace.repository.ProdutoRepository;

@Service
public class ProdutoService {

	// Log to show the behavior of the methods
	private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	 private ItemVendaRepository itemVendaRepository;
	
	 @Autowired
	 private AvaliacaoItemVendaRepository avaliacaoItemVendaRepository;
	 
	 @Autowired
	 private NoticiaCategoriaRepository noticiaCategoriaRepository;
	 
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
	@CacheEvict(value="searchProduct", allEntries=true)
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
	@CacheEvict(value="searchProduct", allEntries=true)
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
	@Cacheable(value="searchProduct")
	public Page<Produto> searchProduct(String name, int page, int size) {
		log.info("Search products name {}", name);
		
		// Order by score, nome and categoria.nome
		PageRequest pageRequest 
		= PageRequest.of(page, size, Sort.Direction.ASC, "score", "nome", "categoria.nome");

		Page<Produto> produtos = produtoRepository.search(name.toLowerCase(), pageRequest);

		return produtos;
	}
	
	
	/**
	  * Calculate score
	  * 
	  * X = Average product rating over the past 12 monthss
	  * Y = Quantity of sales / days the product exists
	  * Z = Quantity of product category news for the current day
	  * 
	  * Score = X + Y + Z
	  * 
	  */
	 public void calculateScore() {
		 
		 Iterable<Produto> products = produtoRepository.findAll();
		 
		 products.forEach(product -> {
			 
			 // X = Average product rating over the past 12 monthss
			 Long x = avaliacaoItemVendaRepository.findAverageEvaluationProductYearly(product.getId());
			 if(x==null) 
				 x = Long.valueOf(0);	 
				 
			 // Y = Quantity of sales / days the product exists
			 Integer daysProductExistence = this.getDifferenceDays(product.getDataCriacao(),new Date());			 
			 Long y = itemVendaRepository.findSalesPerDaysExistenceProduct(product.getId(), daysProductExistence);
			 if(y==null)
				 y = Long.valueOf(0);
			 
			 // Z = Quantity of product category news for the current day
			 Long z = noticiaCategoriaRepository.findNewsByCategory(product.getCategoria().getId());
			 if(z==0)
				 z = Long.valueOf(0);
			 
			 Long score = x + y + z;
			 product.setScore(score);
			 
			 produtoRepository.save(product);
			 
		 });
		 
		 
	 }
	 
	 /**
	  * Difference days between two dates
	  * 
	  * @param d1
	  * @param d2
	  * @return
	  */
	 public int getDifferenceDays(Date d1, Date d2) {
		 int daysdiff = 0;
		 long diff = d2.getTime() - d1.getTime();
		 long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		 daysdiff = (int) diffDays;
		 return daysdiff;
	 }
	
}
