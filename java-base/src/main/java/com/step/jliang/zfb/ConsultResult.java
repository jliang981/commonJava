package com.step.jliang.zfb;

/**
 * @author haoliang
 * @Date 2019-03-28
 **/
public class ConsultResult {

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /** 咨询结果是否可用*/
    private boolean isEnable;
    /** 错误码 */
    private String errorCode;


}
