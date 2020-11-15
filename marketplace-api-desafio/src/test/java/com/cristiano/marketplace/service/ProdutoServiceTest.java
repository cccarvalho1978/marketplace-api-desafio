package com.cristiano.marketplace.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import com.cristiano.marketplace.domain.Categoria;
import com.cristiano.marketplace.domain.Produto;
import com.cristiano.marketplace.exception.ResourceNotFoundException;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceTest {
	
	// Product exist
	private final Long PRODUTO_ID_1 = 1l;
	// Product not exist
	private final Long PRODUTO_ID_101 = 101l;
	// Product exist update
	private final Long PRODUTO_ID_2 = 2l;
	// Product exist delete
	private final Long PRODUTO_ID_3 = 3l;

	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private NewsApiService newsApiService;
	
	/**
	 * Testing product that exists
	 */
	@Test
	public void testFindProductById() {

		try {
			Produto produto = produtoService.findProductById(PRODUTO_ID_1);
			assertNotNull(produto);
			assertEquals(PRODUTO_ID_1, produto.getId());
		} catch (ResourceNotFoundException e) {
			// error
			assertFalse(true);
		}
	}

	/**
	 * Testing product that doesn't exist
	 */
	@Test
	public void testFindProductByIdNotExist() {

		try {
			produtoService.findProductById(PRODUTO_ID_101);
			// error
			assertFalse(true);
		} catch (ResourceNotFoundException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Testing find all products
	 */
	@Test
	public void testFindAllProducts() {
		List<Produto> produtos = produtoService.findAll();
		assertFalse(produtos.isEmpty());
	}
	
	/**
	 * Testing save product
	 */
	@Test
	public void testSaveProduct() {
		
		Produto product = new Produto();
		product.setCategoria(new Categoria(1l));
		product.setDataCriacao(new Date());
		product.setDescricao("Descricao Produto Teste");
		product.setNome("Produto Teste");
		
		try {
			Produto p = produtoService.saveProduct(product);
			assertNotNull(p.getId());
			assertEquals(p.getNome(), product.getNome());
		} catch (ResourceNotFoundException e) {
			// error
			assertFalse(true);
		}
	}

	/**
	 * Testing update product
	 */
	@Test
	public void testUpdateProduct() {
		
		try {
			Produto product = produtoService.findProductById(PRODUTO_ID_2);
			product.setNome("Alterando Nome Produto");
			
			Produto p = produtoService.saveProduct(product);
			assertNotNull(p.getId());
			assertEquals(p.getNome(), "Alterando Nome Produto");
		} catch (ResourceNotFoundException e) {
			// error
			assertFalse(true);
		}
	}
	
	/**
	 * Testing delete product
	 */
	@Test
	public void testDeleteProduct() {
		
		try {
			produtoService.deleteProduct(PRODUTO_ID_3);
		} catch (ResourceNotFoundException e) {
			// error
			assertFalse(true);
		}
		
		try {
			produtoService.findProductById(PRODUTO_ID_3);
			// error
			assertFalse(true);
		} catch (ResourceNotFoundException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test search products
	 */
	@Test
	public void testSearchProduct() {

		String name = "Produto 1";

		Page<Produto> produtos = produtoService.searchProduct(name, 0, 10);

		assertNotNull(produtos);

		produtos.forEach( p -> {
			assertTrue(p.getNome().startsWith(name));
		});

	}
	
	
	/**
	 * Test calculate score
	 * @throws Exception
	 */
	@Test
	public void testCalculateScore() throws Exception {

		// Find product
		Produto product = produtoService.findProductById(PRODUTO_ID_1);
		// Persist score null
		product.setScore(null);
		produtoService.saveProduct(product);
		
		// Persist news by cateogy 
		newsApiService.findNewsAllCategories();

		// Persist calculate score
		produtoService.calculateScore();
		
		product = produtoService.findProductById(PRODUTO_ID_1);
		
		assertTrue(product.getScore()>0);
		
		
	}
	

}
