package com.bit.learning.java.basics;

/***
 * 异步调用结束时回调：成功结束回调onSuccess，失败结束调用onFailure
 * 回调时传入异步调用的执行结果(successResponse,failResponse)
 */
public interface AsyncCallback {
    /***
     * 异步调用成功时调用
     * @param successResponse
     */
    public void onSuccess(Object successResponse);
    public void onFailure(Object failResponse);
}
