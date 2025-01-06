package com.example.PandaCoffee.service;
import com.example.PandaCoffee.dto.request.ProductRequest;
import com.example.PandaCoffee.dto.response.ProductResponse;
import com.example.PandaCoffee.mapper.ProductMapper;
import com.example.PandaCoffee.model.Categories;
import com.example.PandaCoffee.model.Product;
import com.example.PandaCoffee.repositories.CategoriesRepository;
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
    CategoriesRepository categoriesRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public ProductResponse addProduct(ProductRequest productRequest, MultipartFile file) throws Exception {
        System.out.println(productRequest);
        // Kiem tra ton tai
        Optional<Product> product = productRepository.findByProductName(productRequest.getProductName());
        if (product.isPresent()) {  // Kiểm tra nếu Optional có giá trị
            throw new IllegalArgumentException("Product exists");  // Ném ngoại lệ nếu sản phẩm đã tồn tại
        }
        // Gan gia tri
        Product pro = productMapper.toProduct(productRequest);
        if (file != null && !file.isEmpty()) {
            pro.setAvatar(cloudinaryService.uploadImage(file));
        } else {
            pro.setAvatar(null);
        }

        //Set category
        // Lấy Categories từ CSDL bằng categoryId
        Categories category = categoriesRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found for ID: " + productRequest.getCategoryId()));

        // Gán categories cho Product
        pro.setCategories(category);
        // Moi them san pham mac dinh con hang
        pro.setStatus(1);

        // Luu thong tin
        productRepository.save(pro);

        return productMapper.toProductResponse(pro);
    }

    public ProductResponse updateProduct(int productId, ProductRequest productRequest, MultipartFile file) throws Exception {
        // Tìm sản phẩm theo ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Kiểm tra tên sản phẩm mới đã tồn tại chưa (trừ trường hợp tên không thay đổi)
        Optional<Product> existingProduct = productRepository.findByProductName(productRequest.getProductName());
        if (existingProduct.isPresent() && existingProduct.get().getProductId() != productId) {
            throw new IllegalArgumentException("Product name already exists");
        }

        // Cập nhật các thông tin từ productRequest
         productMapper.updateProductFromRequest(productRequest, product);

        // Lấy Categories từ CSDL bằng categoryId
        Categories category = categoriesRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found for ID: " + productRequest.getCategoryId()));

        // Gán categories cho Product
        product.setCategories(category);



        // Nếu có file ảnh mới, cập nhật avatar
        if (file != null && !file.isEmpty()) {
            product.setAvatar(cloudinaryService.uploadImage(file));
        }

        // Lưu lại sản phẩm sau khi cập nhật
        productRepository.save(product);

        // Trả về thông tin sản phẩm đã cập nhật dưới dạng ProductResponse
        return productMapper.toProductResponse(product);
    }

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

    public ProductResponse findById(int productId) {
        Product pro = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return productMapper.toProductResponse(pro);
    }

    public List<ProductResponse> findAllProductByCategoryId(int categoryId) {
        List<Product> list = productRepository.findByCategories_CategoryId(categoryId);
        return list.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    //Ham truy van danh sach tat ca san pham
    public List<ProductResponse> findAll ()
    {
        var list = productRepository.findAll();
        return list.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    //Tim kiem danh sach san pham theo ten san pham
    public List<ProductResponse> findByProductName(String productName) {
        List<Product> products = productRepository.findByProductNameContainingIgnoreCase(productName);
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    //Truy van thong tin san pham theo id

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
