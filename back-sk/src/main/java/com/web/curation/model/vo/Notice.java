
// 디비 셋팅 주석 
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
public class Notice {
    @Id
    private int nid;
    private String person1;
    private String person1nickName;
    private String person2;
    private String person2nickName;
    private String msg;

    public Notice(String person1, String person1nickName, String person2, String person2nickName, String msg) {
        this.person1 = person1;
        this.person1nickName = person1nickName;
        this.person2 = person2;
        this.person2nickName = person2nickName;
        this.msg = msg;
    }
}