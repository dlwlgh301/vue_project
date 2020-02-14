// 디비 셋팅 주석 
package com.web.curation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private int boardNumber;
    private String email;
    private int productNumber;
    private String content;
    private double score;
    private String imgURL;
    private String keywordMain;
    private String keywordSub;
    private int likeNumber;
    private String nickName;

    public Review(String email, int productNumber, String content, double score, String imgURL, String keywordMain,
            String keywordSub, int likeNumber, String nickName) {
        this.email = email;
        this.productNumber = productNumber;
        this.content = content;
        this.score = score;
        this.imgURL = imgURL;
        this.keywordMain = keywordMain;
        this.keywordSub = keywordSub;
        this.likeNumber = likeNumber;
        this.nickName = nickName;
    }

}