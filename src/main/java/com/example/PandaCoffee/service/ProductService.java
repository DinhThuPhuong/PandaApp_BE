package com.example.PandaCoffee.service;
import com.example.PandaCoffee.dto.request.ProductRequest;
import com.example.PandaCoffee.dto.response.ProductResponse;
import com.example.PandaCoffee.mapper.ProductMapper;
import com.example.PandaCoffee.model.Categories;
import com.example.PandaCoffee.model.Product;
import com.example.PandaCoffee.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public ProductResponse addProduct (ProductRequest productRequest, MultipartFile file) throws Exception
    {
        System.out.println(productRequest);
        //Kiem tra ton tai
        Optional<Product> product = productRepository.findByProductName(productRequest.getProductName());
        if (product.isPresent()) {  // Kiểm tra nếu Optional có giá trị
            throw new IllegalArgumentException("Product exists");  // Ném ngoại lệ nếu sản phẩm đã tồn tại
        }
        //Gan gia tri
        Product pro = productMapper.toProduct(productRequest);
        if(file != null && !file.isEmpty()){
            pro.setAvatar(cloudinaryService.uploadImage(file));

        }else {
            pro.setAvatar(null);
        }
        //Moi them san pham mac dinh con hang
        pro.setStatus(1);

        //Luu thong tin
         productRepository.save(pro);

         return productMapper.toProductResponse(pro);
    }
    // Cap nhat trang thai con hang
    public boolean updateProductStatusToInStock(int productId) {
        // Tìm sản phẩm theo ID
        Optional<Product> productOpt = productRepository.findById(productId);

        // Kiểm tra xem sản phẩm có tồn tại không
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            // Nếu trạng thái đã là "còn hàng", không cần cập nhật lại
            if (product.getStatus() == 1) {
                return false; // Trả về false nếu trạng thái đã là "còn hàng"
            }

            // Cập nhật trạng thái thành còn hàng (1)
            product.setStatus(1); // Giả sử 1 là còn hàng

            // Lưu lại sản phẩm đã cập nhật
            productRepository.save(product);
            return true; // Trả về true nếu trạng thái được cập nhật thành công
        } else {
            return false; // Trả về false nếu sản phẩm không tồn tại
        }
    }

    //Truy van thong tin san pham theo id
    public ProductResponse findById(int productId)
    {
        Product pro = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("Product not found"));
        return productMapper.toProductResponse(pro);
    }

    //Truy van danh sach san pham theo ma loai
    public List<ProductResponse> findAllProductByCategoryId(int categoryId)
    {
        List<Product> list = productRepository.findByCategories_CategoryId(categoryId);
        return list.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    //Xoa mem san pham
//     public boolean deledeProduct(int productId)
//     {
//         Product pro = productRepository.findById(productId).orElseThrow(()-> new IllegalArgumentException("Product not found"));
//
//     }

    // Cập nhật trạng thái sản phẩm thành "hết hàng"
    public boolean updateProductStatusToOutOfStock(int productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            // Nếu trạng thái đã là "hết hàng", không cần thay đổi
            if (product.getStatus() == 0) {
                return false;  // Trả về false nếu sản phẩm đã là hết hàng
            }

            // Cập nhật trạng thái thành hết hàng (0)
            product.setStatus(0);
            productRepository.save(product);
            return true;
        } else {
            return false;  // Trả về false nếu sản phẩm không tồn tại
        }
    }

}