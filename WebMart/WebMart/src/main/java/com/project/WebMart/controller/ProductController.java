package com.project.WebMart.controller;

import com.project.WebMart.model.Products;
import com.project.WebMart.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/webmart")
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Products>> getAllProducts(){
        try{
            return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){
        try{
            Products products = productService.getProductById(id);
            if(products == null){
                return new ResponseEntity<>("failed to get product by given id",HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(products,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error occurred while fetching product",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody Products products){

        try{
            return new ResponseEntity<>(productService.addProduct(products),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add",HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Products products){
        try{
            return new ResponseEntity<>(productService.updateProduct(id,products),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update product",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>("Successfully deleted the product",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete product",HttpStatus.BAD_REQUEST);
        }
    }

}
