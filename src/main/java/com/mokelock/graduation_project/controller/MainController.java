package com.mokelock.graduation_project.controller;

import com.alibaba.fastjson.JSON;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.mokelock.graduation_project.model.SingletonDockerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

//    private DockerClient dockerClient = SingletonDockerClient.getInstance().getDockerClient();

    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/getMsg")
    public String getMsg() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://127.0.0.1:2375")
                .withDockerTlsVerify(false)
//                .withDockerCertPath("/home/user/.docker/certs")
//                .withDockerConfig("/home/user/.docker")
//                .withApiVersion("1.30") // optional
                .withRegistryUrl("https://index.docker.io/v1/")
                .withRegistryUsername("mlock")
                .withRegistryPassword("sQEZcSLMqVMS4PE")
                .withRegistryEmail("541257940@qq.com")
                .build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();
        System.out.println(dockerClient.authConfig());
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSON.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(infoStr);
        return infoStr;
    }

}
