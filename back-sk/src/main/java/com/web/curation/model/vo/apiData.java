
package com.web.curation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class apiData {
    Review review;
    List<String> img;
    boolean interest;
    int commentNum;

    public apiData(Review review, List<String> img, boolean interest) {
        this.review = review;
        this.img = img;
        this.interest = interest;
    }
}