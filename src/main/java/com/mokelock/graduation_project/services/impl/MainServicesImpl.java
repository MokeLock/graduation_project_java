package com.mokelock.graduation_project.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Ports;
import com.mokelock.graduation_project.model.SingletonDockerClient;
import com.mokelock.graduation_project.services.MainServices;
import org.springframework.stereotype.Service;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

@Service
public class MainServicesImpl implements MainServices {

    private SingletonDockerClient singletonDockerClient = SingletonDockerClient.getInstance();
    private DockerClient dockerClient = singletonDockerClient.getDockerClient();

    @Override
    public String getMsgFromDocker() {

        System.out.println(dockerClient.authConfig());
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSON.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(infoStr);
        return infoStr;
    }

    @Override
    public String createContainers(String containerName, String imageName) {
        ExposedPort tcp80 = ExposedPort.tcp(80);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(8088));

        CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80).exec();
        return null;
    }

    @Override
    public String startContainer(String containerID) {
        dockerClient.startContainerCmd(containerID).exec();
        return null;
    }

    @Override
    public String stopContainer(String containerID) {
        dockerClient.stopContainerCmd(containerID).exec();
        return null;
    }

    @Override
    public String removeContainer(String containerID) {
        dockerClient.removeContainerCmd(containerID).exec();
        return null;
    }
}
