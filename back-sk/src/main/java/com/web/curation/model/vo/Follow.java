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
    private String following;
    private String followingnickName;

    public Follow(String follower, String followernickName, String following, String followingnickName) {
        this.follower = follower;
        this.followernickName = followernickName;
        this.following = following;
        this.followingnickName = followingnickName;
    }
}