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
public class Comment {
    @Id
    private int cid;
    private int reviewNum;
    private String content;
    private String email;
    private String nickName;
    private String imgURL;

    public Comment(int reviewNum, String content, String email, String nickName, String imgURL) {
        this.reviewNum = reviewNum;
        this.content = content;
        this.email = email;
        this.nickName = nickName;
        this.imgURL = imgURL;
    }
}