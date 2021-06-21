package com.fsoft.team.controller;

import com.fsoft.team.dtos.Cart;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"CART"})
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
        model.addAttribute("product", product);

        List<CategoryEntity> listCateEntity = categoryService.getAll();
        List<CategoryDTO> listCateDTO = new ArrayList<CategoryDTO>();
        for (int i = 0; i < listCateEntity.size(); i++) {
            listCateDTO.add(new CategoryDTO(listCateEntity.get(i).getCategoryID(), listCateEntity.get(i).getCategoryName()));
        }

        model.addAttribute("LISTCATEGORY", listCateDTO);
        return "/product/add-product";
    }

    @PostMapping("/addproduct")
    public String addProduct(@ModelAttribute("product") ProductDTO product,
            @RequestParam("image") MultipartFile multipartFile,
            @RequestParam("condition") String condition) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setImgProduct(fileName);
        product.setCondition(condition);
<<<<<<< HEAD
        String uploadDir = "src/main/resources/static/img/" + fileName;
=======
        String uploadDir = "/static/img/" + fileName;
>>>>>>> df958a0514786095c23c8ac90eb0d4e4916573a0
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

    // Shopping

    @RequestMapping("view-cart")
    public String viewCartPage(Model model) {
        Cart cart = (Cart) model.getAttribute("CART");
        if (cart == null) {
            cart = new Cart();
            ProductEntity product = new ProductEntity();
            cart.add(product);
            cart.clear();
        }
        model.addAttribute("CART", cart);
        return "Cart";
    }

    @GetMapping("/order")
    public String order(@RequestParam("txtProductID") Long productID, Model model) {
        int quantityCart = 0;
        Cart cart = (Cart) model.getAttribute("CART");
        if (cart == null) {
            cart = new Cart();
        } else {
            for (ProductEntity product : cart.getCart().values()) {
                if (product.getProductID() == productID) {
                    quantityCart = product.getUnitInStock();
                    break;
                }
            }
        }
        int max = productService.checkUnitsInStock(productID);

        ProductEntity product = productService.findProductByID(productID);
        product.setUnitInStock(1);

        if (quantityCart < max) {
            cart.add(product);
            model.addAttribute("CART", cart);
            model.addAttribute("ORDER_SUCCESS", "Add To Cart Success");
        } else {
            model.addAttribute("ORDER_SUCCESS", product.getProductName() + " is out of stock.");
        }
        return "redirect:/list-products";
    }

    @RequestMapping("back")
    public String continueShopping(Model model) {
        model.addAttribute("ORDER_SUCCESS", "");
        return "redirect:/list-products";
    }

    @PostMapping("clear")
    public String clearCart(Model model) {
        Cart cart = (Cart) model.getAttribute("CART");
        cart.clear();
        model.addAttribute("CART", cart);
        return "Cart";
    }

    @GetMapping("remove")
    public String removeCart(@RequestParam("txtProductID") Long productID, Model model) {
        Cart cart = (Cart) model.getAttribute("CART");
        System.out.println("product: " + productID);
        cart.delete(productID);
        model.addAttribute("CART", cart);
        return "Cart";
    }
}
