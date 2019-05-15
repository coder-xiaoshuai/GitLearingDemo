package com.example.zhangshuai.kotlin;

public class CallbackTest {
    public static void main(String[] args) {
        System.out.println();
        CallbackProxy callbackProxy = new CallbackProxy();
        callbackProxy.setMyCallback(aBoolean -> {
            if (aBoolean){

            }
        });
    }


    interface MyCallback<T>{
        void call(T t);
    }

    static class CallbackProxy{
        private MyCallback<Boolean> myCallback;
        public void setMyCallback(MyCallback<Boolean> myCallback){
            this.myCallback = myCallback;
        }
    }
}
