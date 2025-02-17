package com.example.excam_module4.controller;

import com.example.excam_module4.model.Product;

import com.example.excam_module4.service.impl.ProducTypeService;
import com.example.excam_module4.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private ProducTypeService productTypeService;

    // Hiển thị danh sách tất cả sản phẩm

    @GetMapping
    public String showProducts(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Integer type,
                               @RequestParam(required = false) BigDecimal price,
                               @PageableDefault(size = 5) Pageable pageable,
                               Model model) {
        Page<Product> productPage = productService.searchProducts(name, type, price, pageable);
        model.addAttribute("products", productPage);
        // Để hiển thị dropdown loại sản phẩm
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Product product = new Product();
        product.setStatus("Chờ duyêt");
        model.addAttribute("product", product);
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/create";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("productTypes", productTypeService.findAll());
            return "product/create";
        }
        Product createdProduct = productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Tạo sản phẩm thành công!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get());
            model.addAttribute("productTypes", productTypeService.findAll());
            return "product/edit";
        }
        redirectAttributes.addFlashAttribute("error", "Không tìm thấy sản phẩm");
        return "redirect:/products";
    }
    @PostMapping("/batch-delete")
    public String batchDelete(@RequestParam(name = "selectedIds", required = false) List<Integer> selectedIds,
                              RedirectAttributes redirectAttributes) {
        if (selectedIds != null && !selectedIds.isEmpty()) {

            for (Integer id : selectedIds) {
                productService.deleteById(id);
            }
            redirectAttributes.addFlashAttribute("message", "Đã xóa các sản phẩm được chọn!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Chưa chọn sản phẩm nào để xóa.");
        }
        return "redirect:/products";
    }

}
