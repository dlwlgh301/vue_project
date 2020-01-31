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
public class Follow {
    @Id
    private int num;
    private String follower;
    private String followernickName;
    private String folloing;
    private String folloingnickName;

    public Follow(String follower, String followernickName, String folloing, String folloingnickName) {
        this.follower = follower;
        this.followernickName = followernickName;
        this.folloing = folloing;
        this.folloingnickName = folloingnickName;
    }
}