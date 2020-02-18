package com.web.curation.service.review;

import java.util.List;

import com.web.curation.dao.review.ImgDao;
import com.web.curation.model.vo.Img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgDao imgDao;

    @Override
    public boolean insertImg(Img img) throws Exception {
        return imgDao.insertImg(img);
    }

    // @Override
    // public List<Img> getImgURL(int rNum) throws Exception {
    // return imgDao.getImgURL(rNum);
    // }

    @Override
    public List<String> getImgs(int rNum) throws Exception {
        return imgDao.getImgs(rNum);
    }

}