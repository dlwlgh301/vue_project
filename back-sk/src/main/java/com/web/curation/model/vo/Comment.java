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
public class Comment {
    @Id
    private int num;
    private int boardNumber;
    private String comment;
    private String email;
    private String nickName;
    private String imgURL;

    public Comment(int boardNumber, String comment, String email, String nickName, String imgURL) {
        this.boardNumber = boardNumber;
        this.comment = comment;
        this.email = email;
        this.nickName = nickName;
        this.imgURL = imgURL;
    }
}