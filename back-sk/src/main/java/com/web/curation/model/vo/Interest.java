
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
public class Interest {
    @Id
    private int lid;
    private int reviewNum;
    private String email;
    private String nickName;

    public Interest(int reviewNum, String email, String nickName) {
        this.reviewNum = reviewNum;
        this.email = email;
        this.nickName = nickName;
    }

    public Interest(int reviewNum, String email) {
        this.reviewNum = reviewNum;
        this.email = email;
    }
}