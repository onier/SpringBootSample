package com.sb.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Admin on 2018/6/15.
 */
public class MyProxy {

    public static void main(String[] args) {
        MyInterface object = (MyInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{MyInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 1;
            }
        });
        System.out.println(object.exit());
    }

}
