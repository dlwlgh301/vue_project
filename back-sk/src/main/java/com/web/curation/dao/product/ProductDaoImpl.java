package com.web.curation.dao.product;

import com.web.curation.model.vo.Product;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int insertProduct(Product product) throws Exception {
        return sqlSession.insert("insertProduct", product);
    }

}