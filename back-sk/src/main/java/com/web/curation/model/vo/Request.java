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
public class Request {
    @Id
    private int rid;
    private String requester;
    private String requesterNick;
    private String requestee;
    private String requesteeNick;

    public Request(String requester, String requesterNick, String requestee, String requesteeNick) {
        this.requester = requester;
        this.requesterNick = requesterNick;
        this.requestee = requestee;
        this.requesteeNick = requesteeNick;
    }

    public Request(String requester, String requestee) {
        this.requester = requester;
        this.requestee = requestee;
    }
}