package com.web.curation.dao.product;

import com.web.curation.model.vo.Product;

import org.springframework.stereotype.Repository;

public interface ProductDao {
    int insertProduct(Product product) throws Exception;

}