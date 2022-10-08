package com.zqy.blog_admin.system.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult  {

    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回信息")
    private String msg;
    @ApiModelProperty("返回数据")
    private Object data;


    public static AjaxResult success(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("ok");
        return ajaxResult;
    }
    public static AjaxResult success(Object data){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(200);
        ajaxResult.setSuccess(true);
        ajaxResult.setMsg("ok");
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public static AjaxResult error(){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(503);
        ajaxResult.setSuccess(false);
        ajaxResult.setMsg("failed");
        return ajaxResult;
    }

    public static AjaxResult error(Object data){
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setCode(503);
        ajaxResult.setSuccess(false);
        ajaxResult.setMsg("failed");
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public AjaxResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public AjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }
}
