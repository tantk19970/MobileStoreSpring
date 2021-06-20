package com.fsoft.team.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.team.dtos.CategoryDTO;
import com.fsoft.team.dtos.ProductDTO;
import com.fsoft.team.entity.CategoryEntity;
import com.fsoft.team.entity.ProductEntity;
import com.fsoft.team.fileupload.FileUploadUtil;
import com.fsoft.team.service.CategoryService;
import com.fsoft.team.service.ProductService;


@Controller
public class ProductController {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/addproduct")
	public String showForm(Model model) {
		ProductDTO product = new ProductDTO();
		model.addAttribute("product",product);
		
		List<CategoryEntity> listCateEntity = categoryService.getAll();
		List<CategoryDTO> listCateDTO = new ArrayList<CategoryDTO>();
		for(int i = 0; i < listCateEntity.size(); i++) {
			listCateDTO.add(new CategoryDTO(listCateEntity.get(i).getCategoryID(), listCateEntity.get(i).getCategoryName()));
		}
		
		model.addAttribute("LISTCATEGORY", listCateDTO);
		return "/product/add-product";
	}
	
	@PostMapping("/addproduct")
	public String addProduct(@ModelAttribute("product") ProductDTO product , 
			@RequestParam("image") MultipartFile multipartFile,
			@RequestParam("condition") String condition)  throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		product.setImgProduct(fileName);
		product.setCondition(condition);
		String uploadDir = "/static/img/" + fileName;
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		ProductEntity entity = new ProductEntity();
		entity.setProductName(product.getProductName());
		entity.setUnitPrice(product.getUnitPrice());
		entity.setUnitInStock(product.getUnitInStock());
		entity.setDescription(product.getDescription());
		entity.setManufacturer(product.getManufacturer());
		entity.setCondition(product.getCondition());
		entity.setImgProduct(product.getImgProduct());
		entity.setCategoryID(product.getCategoryID());
		productService.addProduct(entity);
		return "/product/addsuccess";
	}
}


















