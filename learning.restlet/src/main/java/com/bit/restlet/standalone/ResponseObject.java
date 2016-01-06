package com.bit.restlet.standalone;

/**
 * Created by yuzt on 16-1-6.
 */
public class ResponseObject {
    private int code;
    private Object data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResponseObject SUCC = new ResponseObject();

    static {
        SUCC.setCode(200);
        SUCC.setData("Success");
        SUCC.setMsg("The op succeeds");
    }

    public static ResponseObject FAIL = new ResponseObject();

    static {
        FAIL.setCode(500);
        FAIL.setData("failure");
        FAIL.setMsg("The op fails");
    }

}
