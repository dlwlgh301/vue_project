package com.web.curation.service.Bookmark;

import java.util.List;

import com.web.curation.dao.bookmark.BookmarkDao;
import com.web.curation.dao.product.ProductDao;
import com.web.curation.model.vo.Bookmark;
import com.web.curation.model.vo.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    @Autowired
    BookmarkDao bookmarkDao;

    @Override
    public int addBookmark(Bookmark bookmark) throws Exception {
        System.out.println("service : addBookmark");
        return bookmarkDao.addBookmark(bookmark);
    }

    @Override
    public int deleteBookmark(Bookmark bookmark) throws Exception {
        return bookmarkDao.deleteBookmark(bookmark);
    }

    @Override
    public List<Bookmark> getBookmarkListByEmail(String email) throws Exception {
        System.out.println("service : getBookmarkListByEmail");
        return bookmarkDao.getBookmarkListByEmail(email);
    }

    @Override
    public int likeCheck(Bookmark bookmark) throws Exception {
        return bookmarkDao.likeCheck(bookmark);
    }

}