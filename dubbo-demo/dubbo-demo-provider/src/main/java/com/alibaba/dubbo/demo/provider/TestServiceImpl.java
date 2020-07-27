package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.TestService;
import com.alibaba.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestServiceImpl implements TestService {

    @Override
    public String test(String test) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + test + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return test + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
