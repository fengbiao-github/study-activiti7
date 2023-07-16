package com.fengbiao.studyactiviti7;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Activiti7Test {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    
    @Test
    public void test() throws Exception {
        System.out.println(processEngine);
    }
}
