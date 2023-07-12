package com.fengbiao.studyactiviti7;

import static org.junit.Assert.*;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

public class ProcessEngineTest {

    @Test
    public void test1() throws Exception {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("processEngine=" + processEngine);
    }

    @Test
    public void test2() throws Exception {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine=" + processEngine);
    }

    @Test
    public void test3() throws Exception {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine=" + processEngine);
    }

    @Test
    public void test4() throws Exception {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("processEngine=" + processEngine);
    }
    
    @Test
    public void test5() throws Exception {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("processEngine=" + processEngine);
        ManagementService managementService = processEngine.getManagementService();
        DynamicBpmnService dynamicBpmnService = processEngine.getDynamicBpmnService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        HistoryService historyService = processEngine.getHistoryService();
    }
}
