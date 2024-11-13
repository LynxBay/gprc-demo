package com.riardo.grpc.boot.service.a.service;

import com.google.protobuf.ProtocolStringList;
import com.ricardo.grpc.api.HelloProto;
import com.ricardo.grpc.api.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @ClassName : HelloServiceImpl
 * @Author : changyp
 * @Date : 2024/11/5 12:54
 * @Description :
 */
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    /*
    一元rpc 单入参
     */
    @Override
    public void sayHello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        //1. 从入参message中获取参数值
        String name = request.getName();
        //2.处理业务
        System.out.println(name);
        String result = "Hello " + name;
        //3.构建返回值
        HelloProto.HelloResponse response = HelloProto.HelloResponse
                .newBuilder()
                .setResult("Method [sayHello] invoked, result: " + result)
                .build();
        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

    /*
    * 一元rpc 多入参
     */
    @Override
    public void sayHelloAgain(HelloProto.HelloRequest1 request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        ProtocolStringList nameList = request.getNameList();
        for (String s : nameList) {
            System.out.println("Name : "+s);
        }
        System.out.println("服务端接收数据完毕");
        HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
        builder.setResult("服务端业务处理完毕  本次登陆人数："+nameList.size());
        HelloProto.HelloResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
