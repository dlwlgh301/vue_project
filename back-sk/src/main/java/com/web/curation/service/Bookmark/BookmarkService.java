package com.web.curation.service.Bookmark;

import java.util.List;

import com.web.curation.model.vo.Bookmark;
import com.web.curation.model.vo.Product;

public interface BookmarkService {
    int addBookmark(Bookmark bookmark) throws Exception;

    int deleteBookmark(Bookmark bookmark) throws Exception;

    List<Product> getBookmarkListByEmail(String email) throws Exception;

    int likeCheck(Bookmark bookmark) throws Exception;
}