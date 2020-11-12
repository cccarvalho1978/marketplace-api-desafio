package com.cristiano.marketplace.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cristiano.marketplace.dto.ProdutoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutoControllerTest {

	private final Long PRODUTO_VALIDO_ID_1 = 1L;
	private final Long PRODUTO_VALIDO_ID_2 = 2L;
	private final Long PRODUTO_VALIDO_ID_3 = 3L;
	private final String PRODUTO_NOME_PESQUISA = "Produto 1";
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testGetAllProducts() throws Exception  {
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/produtos")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}	
	
	@Test
	public void testGetProductById() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/produtos/"+PRODUTO_VALIDO_ID_1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value("Produto 1"))
				.andExpect(jsonPath("$.descricao").value("Produto 1 teste"))
				.andExpect(jsonPath("$.categoria.id").value("1"));
	}
	
	
	@Test
	public void testCreateProduct() throws Exception  {
		
		ProdutoDTO product = new ProdutoDTO();
		product.setNome("Produto Teste");
		product.setCategoriaId(1l);
		product.setDescricao("Produto Teste Descricao");
		
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/produtos")
					.content(mapper.writeValueAsString(product))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nome").value("Produto Teste"))
					.andExpect(jsonPath("$.descricao").value("Produto Teste Descricao"))
					.andExpect(jsonPath("$.categoria.id").value("1"));
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		
		ProdutoDTO product = new ProdutoDTO();
		product.setNome("Produto Alterado");
		product.setCategoriaId(4l);
		product.setDescricao("Descrição do produto alterado");
		
		mvc.perform(MockMvcRequestBuilders.put("/api/v1/produtos/"+PRODUTO_VALIDO_ID_2)
					.content(mapper.writeValueAsString(product))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nome").value("Produto Alterado"))
					.andExpect(jsonPath("$.descricao").value("Descrição do produto alterado"))
					.andExpect(jsonPath("$.categoria.id").value("4"));
		
		
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/produtos/"+PRODUTO_VALIDO_ID_3)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.deleted").value("true"));
		
	}

	@Test
	public void testSearch() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/produtos/search?nome="+PRODUTO_NOME_PESQUISA)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.termoPesquisado").value(PRODUTO_NOME_PESQUISA))
				.andExpect(jsonPath("$.produtos").isArray())
				.andExpect((jsonPath("$.produtos", Matchers.hasSize(10))));
				//.andExpect(jsonPath("$.produtos.length").value("10"));
		
	}

}
