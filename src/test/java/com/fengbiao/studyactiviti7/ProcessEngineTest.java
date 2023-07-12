package com.fengbiao.studyactiviti7;

import static org.junit.Assert.*;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
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
}
