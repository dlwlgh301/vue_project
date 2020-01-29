// 디비 셋팅 주석 
package com.web.curation.model.board;

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
    private int boardNumber;
    private String email;
    private String contents;
    private String keyword;
    private int likeNumber;
    private int productNumber;

}