package com.cristiano.marketplace.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristiano.marketplace.domain.Produto;
import com.cristiano.marketplace.dto.ProdutoDTO;
import com.cristiano.marketplace.exception.ResourceNotFoundException;
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
	 public Produto createProduct(@RequestBody ProdutoDTO produtoDTO) throws ResourceNotFoundException {
		 Produto produto = produtoDTO.transformToEntity();
		 return produtoService.saveProduct(produto);
	 }

	 /**
	  * Update product
	  * @param produtoId
	  * @param produtoDetails
	  * @return
	  * @throws ResourceNotFoundException
	  */
	 @PutMapping("/{id}")
	 public ResponseEntity <Produto> updateProduct(@PathVariable(value = "id") Long produtoId,
			 									   @RequestBody ProdutoDTO produtoDetails) throws ResourceNotFoundException {
		 
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
	 
	 
	 
}
