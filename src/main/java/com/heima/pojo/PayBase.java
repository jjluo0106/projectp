package com.heima.pojo;


import lombok.Data;

import org.springframework.stereotype.Repository;


@Data
@Repository
public class PayBase {

    private String marchentCode;
    private String platformCode;
    private String callBackUrl;
    private String amountQ;


    public PayBase() {
    }

    public PayBase(String marchentCode, String platformCode, String callBackUrl, String amountQ) {
        this.marchentCode = marchentCode;
        this.platformCode = platformCode;
        this.callBackUrl = callBackUrl;
        this.amountQ = amountQ;
    }
}
