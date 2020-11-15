package com.cristiano.marketplace.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristiano.marketplace.domain.Produto;
import com.cristiano.marketplace.dto.PesquisaDTO;
import com.cristiano.marketplace.dto.ProdutoDTO;
import com.cristiano.marketplace.dto.ProdutoPesquisaDTO;
import com.cristiano.marketplace.exception.ResourceNotFoundException;
import com.cristiano.marketplace.exception.ValidationException;
import com.cristiano.marketplace.service.ProdutoService;

@RestController 
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

	 @Autowired
	 private ProdutoService produtoService;
	 	 
	 /**
	  * Get all products
	  * @return
	  */
	 @GetMapping
	 public List <Produto> getAllProducts(){
        return (List<Produto>) produtoService.findAll();
	 }
	 
	 /**
	  * Get product by id
	  * @param produtoId
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @GetMapping("/{id}")
	 public ResponseEntity <Produto> getProductById(@PathVariable(value = "id") Long produtoId)
			 throws ResourceNotFoundException {
		 Produto produto = produtoService.findProductById(produtoId);
		 return ResponseEntity.ok().body(produto);
	 }

	 /**
	  * Create product
	  * @param produtoDTO
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @PostMapping
	 public  ResponseEntity <Object> createProduct(@Valid @RequestBody ProdutoDTO produtoDTO, 
			                                       BindingResult result) throws ResourceNotFoundException, ValidationException {
		 
		 if(result.hasErrors()) {
			 List<String> messages = new ArrayList<String>();
			 result.getAllErrors().forEach(error->{
				 messages.add(error.getDefaultMessage());
			 });
			 throw new ValidationException(messages);
		 }
		 
		 Produto produto = produtoDTO.transformToEntity();
		 
		 return ResponseEntity.ok(produtoService.saveProduct(produto));
	 }

	 /**
	  * Update product
	  * @param produtoId
	  * @param produtoDetails
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @PutMapping("/{id}")
	 public ResponseEntity <Object> updateProduct(@PathVariable(value = "id") Long produtoId,
			 									   @Valid @RequestBody ProdutoDTO produtoDetails, 
			 									   BindingResult result) throws ResourceNotFoundException, ValidationException {
		 
		 if(result.hasErrors()) {
			 List<String> messages = new ArrayList<String>();
			 result.getAllErrors().forEach(error->{
				 messages.add(error.getDefaultMessage());
			 });
			 throw new ValidationException(messages);
		 }
		 
		 Produto produtoD = produtoDetails.transformToEntity();
		 produtoD.setId(produtoId);
		 
		 final Produto updatedProduct = produtoService.saveProduct(produtoD);
		 
		 return ResponseEntity.ok(updatedProduct);
	 }

	 /**
	  * Delete product
	  * @param produtoId
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @DeleteMapping("/{id}")
	 public Map < String, Boolean > deleteProduct(@PathVariable(value = "id") Long produtoId)
			 throws ResourceNotFoundException {
		 
		 produtoService.deleteProduct(produtoId);
		 
		 Map < String, Boolean > response = new HashMap < > ();
		 response.put("deleted", Boolean.TRUE);
		 return response;
	 }
	 
	 /**
	  * Search products by name
	  * @param nomeProduto
	  * @param page
	  * @param size
	  * @return
	  */
	 @GetMapping("/search")
	 public ResponseEntity<PesquisaDTO> search(@RequestParam("nome") String nomeProduto, 
			 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
			 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		 Page<Produto> produtos = produtoService.searchProduct(nomeProduto, page, size);
		 
		 PesquisaDTO pesquisa = new PesquisaDTO();
		 pesquisa.setDataPesquisa(new Date());
		 pesquisa.setTermoPesquisado(nomeProduto);
		 
		 Page<ProdutoPesquisaDTO> listaProdutosDto = produtos.map(produto -> new ProdutoPesquisaDTO(produto) );
		 pesquisa.setProdutos(listaProdutosDto.toList());
		 
		 return ResponseEntity.ok().body(pesquisa);
	 }
	 
}
