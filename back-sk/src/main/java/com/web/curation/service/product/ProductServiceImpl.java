package com.web.curation.service.product;

import com.web.curation.dao.product.ProductDao;
import com.web.curation.model.vo.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public int insertProduct(Product product) throws Exception {
        return productDao.insertProduct(product);
    }

}