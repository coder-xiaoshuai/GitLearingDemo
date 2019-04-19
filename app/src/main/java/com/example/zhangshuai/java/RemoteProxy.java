package com.example.zhangshuai.java;

public abstract class RemoteProxy<ResultType, RequestType> {
    /**
     * 开始加载
     */
    public abstract void startLoad(RequestType requestType);

    /**
     * 是否远程拿数据
     */
    public abstract boolean shouldFetch(RequestType requestType);

    /**
     * 创建远程请求
     */
    public abstract void createRemoteRequest(RequestType requestType, ResultCallback<ResultType> callback);

    /**
     * 保存数据到本地
     */
    public abstract void saveDataToLocal(ResultType resultType);

    public interface ResultCallback<T> {

        void onSuccess(T resultType);

        void onFail(int errorCode, String errorMsg);
    }
}
