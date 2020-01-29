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
public class comment {
    private int boardNumber;
    private String comment;
    private String email;
    private String nickName;
}