//디비 셋팅 주석 
package com.web.curation.dao.bookmark;

import java.util.List;

import com.web.curation.model.vo.Bookmark;
import com.web.curation.model.vo.Product;

public interface BookmarkDao {
    int addBookmark(Bookmark bookmark) throws Exception;

    int deleteBookmark(Bookmark bookmark) throws Exception;

    List<Product> getBookmarkListByEmail(String email) throws Exception;

    int likeCheck(Bookmark bookmark) throws Exception;
}