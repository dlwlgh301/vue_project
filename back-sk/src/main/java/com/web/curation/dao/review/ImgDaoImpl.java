package com.web.curation.dao.review;

import java.util.List;

import com.web.curation.model.vo.Img;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImgDaoImpl implements ImgDao {
    @Autowired
    SqlSession sqlSession;

    @Override
    public boolean insertImg(Img img) throws Exception {
        int row = sqlSession.insert("insertImg", img);
        if (row > 0)
            return true;
        else
            return false;
    }

    // @Override
    // public List<Img> getImgURL(int rNum) throws Exception {
    // return sqlSession.selectList("getImgURL", rNum);
    // }

    @Override
    public List<String> getImgs(int rNum) throws Exception {
        return sqlSession.selectList("getImgs", rNum);
    }

}