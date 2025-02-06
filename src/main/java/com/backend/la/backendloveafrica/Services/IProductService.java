package com.backend.la.backendloveafrica.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.backend.la.backendloveafrica.models.dtos.ProductCardDTO;
import com.backend.la.backendloveafrica.models.dtos.ProductCardObjectDto;
import com.backend.la.backendloveafrica.models.dtos.ProductNameModelImgType;
import com.backend.la.backendloveafrica.models.entities.Fabric;
import com.backend.la.backendloveafrica.models.entities.ModelAndStripsForProduct;
import com.backend.la.backendloveafrica.models.entities.Product;

public interface IProductService {

        List<Product> findAllProducts();

        Page<Product> findAll(int page, int size);

        Page<ProductCardObjectDto> findAllProductCardDtoPage(int page, int size);

        List<ProductNameModelImgType> findByName(String string);

        List<Product> findByString(String string);

        Page<Product> findPageByString(String string, int page, int size);

        Page<ProductCardObjectDto> findProductCardDtoPageByString(String string, int page, int size);

        Optional<Product> findProductById(Long id);

        Product saveProduct(Product product);

        ProductCardObjectDto saveProductWithImg(MultipartFile imgFile, String nombre, String colorForro, Fabric fabric,
                        ModelAndStripsForProduct modelAndStripsForProduct);

        void deleteProduct(Long id);

        Optional<Product> updateProduct(Long id, Optional<Fabric> fabric,
                        Optional<ModelAndStripsForProduct> modelAndStripsForProduct);

        Optional<Product> updateCompleteProduct(Long id, Product product);

        ProductCardObjectDto updateProductWithImg(Long id, String nombre, String colorForro, MultipartFile imageFile, Fabric fabric,
                        ModelAndStripsForProduct modelAndStripsForProduct);

        Double productCostCalculator(Long id);

        List<ProductNameModelImgType> findBySeasonDto(String season);
}
