package com.fengbiao.studyactiviti7;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.BeforeClass;
import org.junit.Test;

public class RepositoryServiceTest {

    private static ProcessEngine processEngine;

    @BeforeClass
    public static void beforeClass() {
        processEngine = ProcessEngines.getDefaultProcessEngine();
    }

    @Test
    public void test1() throws Exception {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().name("我的第一个请假流程")
                .addClasspathResource("leave.bpmn").addClasspathResource("leave.png").deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
        System.out.println(deployment.getVersion());
        System.out.println(deployment);
    }

    @Test
    public void test2() throws Exception {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        InputStream inputStream = ReflectUtil.getResourceAsStream("leave.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = repositoryService.createDeployment().name("我的第一个请假流程(ZIP)")
                .addZipInputStream(zipInputStream).deploy();
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
        System.out.println(deployment.getVersion());
        System.out.println(deployment);
    }

    @Test
    public void test3() throws Exception {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.processDefinitionKey("leaveProcess")
                .orderByProcessDefinitionVersion().desc().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println(processDefinition);
        }
    }
}
