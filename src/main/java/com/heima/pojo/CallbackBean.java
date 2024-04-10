package com.heima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallbackBean {
    private String merchantCodeB;
    private String result_code;
    private String platformOrderNOB;
    private String amountB;
    private String signB;
}
