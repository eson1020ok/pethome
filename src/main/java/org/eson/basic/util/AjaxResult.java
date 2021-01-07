package org.eson.basic.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjaxResult {

    private Boolean success = true;
    private String msg = "操作成功！";
    // 扩展
    private Object resultObject = null;

    public AjaxResult(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    // 优化成链式语法
    public static AjaxResult me() {
        return new AjaxResult();
    }
    // 设置链式语法
    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public AjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public AjaxResult setResultObject(Object resultObject) {
        this.resultObject = resultObject;
        return this;
    }
}
