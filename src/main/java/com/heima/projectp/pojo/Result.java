package com.heima.projectp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String status;
    private Integer code;
    private String message;
    private Object data;

    public static Result success(Object data){
        return new Result("success",200,"Request successful",data);
    }

    public static Result error(){
        return new Result("error",404,"Resource not found","");
    }

}
