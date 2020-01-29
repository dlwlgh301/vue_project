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
public class Follow {
    private String person1;
    private String person1nickName;
    private String person2;
    private String person2nickName;
}