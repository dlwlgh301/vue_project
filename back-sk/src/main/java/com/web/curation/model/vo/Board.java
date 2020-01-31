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
public class Board {
    @Id
    private int boardNumber;
    private String email;
    private String contents;
    private String keyword;
    private int likeNumber;
    private int productNumber;
    private String imgURL;

    public Board(String email, String contents, String keyword, int likeNumber, int productNumber, String imgURL) {
        this.email = email;
        this.contents = contents;
        this.keyword = keyword;
        this.likeNumber = likeNumber;
        this.productNumber = productNumber;
        this.imgURL = imgURL;
    }

}