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
public class Bookmark {
    @Id
    private int bid;
    private String email;
    private String productName;

    public Bookmark(String email, String productName) {
        this.email = email;
        this.productName = productName;
    }
}
