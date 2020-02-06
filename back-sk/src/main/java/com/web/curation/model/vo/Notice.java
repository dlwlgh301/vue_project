
// 디비 셋팅 주석 
package com.web.curation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    private int nid;
    private String sender;
    private String senderNick;
    private String receiver;
    private String receiverNick;
    private String msg;

    @Column(name = "readchk", columnDefinition = "TINYINT", length = 1)
    private int readchk;

    public Notice(String sender, String senderNick, String receiver, String receiverNick, String msg) {
        this.sender = sender;
        this.senderNick = senderNick;
        this.receiver = receiver;
        this.receiverNick = receiverNick;
        this.msg = msg;
    }
}