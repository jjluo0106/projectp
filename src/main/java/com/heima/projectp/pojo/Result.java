package com.heima.projectp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    public static Result success(String s , Object data){
        return new Result("success",200,s,data);
    }

    public static Result error(String s){
        return new Result("error",404,s,"");
    }

}
