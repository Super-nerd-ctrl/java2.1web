package com.yt.javaweb.common;

public class MyResult {

    private String code;

    private String meg;

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMeg() {
        return meg;
    }

    public void setMeg(String meg) {
        this.meg = meg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "code='" + code + '\'' +
                ", meg='" + meg + '\'' +
                ", data=" + data +
                '}';
    }
}
