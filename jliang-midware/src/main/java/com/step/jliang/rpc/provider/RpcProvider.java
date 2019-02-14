package com.step.jliang.rpc.provider;

import com.step.jliang.rpc.RpcFramework;
import com.step.jliang.rpc.service.HelloService;
import com.step.jliang.rpc.service.impl.HelloServiceImpl;

/**
 * @author jliang
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
