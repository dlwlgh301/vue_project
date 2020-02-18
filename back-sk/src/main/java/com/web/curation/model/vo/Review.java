package com.web.curation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    private int rid;
    private String email;
    private String productName;
    private String content;
    private double score;
    private String title;
    private String keywordMain;
    private String keywordSub;
    private int likeNumber;
    private String nickName;
    private String imgURL;

    public Review(String email, String productName, String content, double score, String title, String keywordMain,
            String keywordSub, int likeNumber, String nickName, String imgURL) {
        this.email = email;
        this.productName = productName;
        this.content = content;
        this.score = score;
        this.title = title;
        this.keywordMain = keywordMain;
        this.keywordSub = keywordSub;
        this.likeNumber = likeNumber;
        this.nickName = nickName;
        this.imgURL = imgURL;
    }

}