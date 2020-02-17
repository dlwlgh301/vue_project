package com.web.curation.service.review;

import java.util.List;

import com.web.curation.model.vo.Img;

public interface ImgService {
    // 상품명에 해당하는 상품번호 찾아오기
    boolean insertImg(Img img) throws Exception;

    // List<Img> getImgURL(int rNum) throws Exception;

    List<String> getImgs(int rNum) throws Exception;
}