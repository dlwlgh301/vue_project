//디비 셋팅 주석 
package com.web.curation.dao.bookmark;

import java.util.List;

import com.web.curation.model.vo.Bookmark;
import com.web.curation.model.vo.Product;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public int addBookmark(Bookmark bookmark) throws Exception {
        return sqlSession.insert("addProduct", bookmark);
    }

    @Override
    public int deleteBookmark(Bookmark bookmark) throws Exception {
        return sqlSession.delete("deleteBookmark", bookmark);
    }

    @Override
    public List<Product> getBookmarkListByEmail(String email) throws Exception {
        return sqlSession.selectList("getBookmarkListByEmail", email);
    }

    @Override
    public int likeCheck(Bookmark bookmark) throws Exception {
        return sqlSession.selectOne("likeCheck", bookmark);
    }

}