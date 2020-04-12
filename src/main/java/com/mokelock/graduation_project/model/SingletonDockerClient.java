package com.mokelock.graduation_project.model;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class SingletonDockerClient {

    private static SingletonDockerClient singletonDockerClient;
    private DockerClient dockerClient;

    private SingletonDockerClient() {
//        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
//                .withDockerHost("tcp://127.0.0.1:2375")
//                .withDockerTlsVerify(false)
////                .withDockerCertPath("/home/user/.docker/certs")
////                .withDockerConfig("/home/user/.docker")
////                .withApiVersion("1.30") // optional
//                .withRegistryUrl("https://index.docker.io/v1/")
//                .withRegistryUsername("mlock")
//                .withRegistryPassword("sQEZcSLMqVMS4PE")
//                .withRegistryEmail("541257940@qq.com")
//                .build();
//        dockerClient = DockerClientBuilder.getInstance(config).build();
        dockerClient = DockerClientBuilder.getInstance("tcp://127.0.0.1:2375").build();
    }

    public static SingletonDockerClient getInstance() {
        try {
            if (null == singletonDockerClient) {
                synchronized (SingletonDockerClient.class) {
                    if (null == singletonDockerClient) {
                        singletonDockerClient = new SingletonDockerClient();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        return singletonDockerClient;
    }

    public DockerClient getDockerClient() {
        return dockerClient;
    }

}
