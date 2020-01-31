// 디비 셋팅 주석 
package com.web.curation.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {
    private String password;
    @Id
    private String email;

    private String nickName;
    private String name;
    private String comment;
    private String keyword;
    private String imgURL;

    public User(String password, String email, String nickName, String name, String comment, String keyword,
            String imgURL) {
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.name = name;
        this.comment = comment;
        this.keyword = keyword;
        this.imgURL = imgURL;
    }
    // @Column(insertable = false, updatable = false)
    // private LocalDateTime createDate;
}