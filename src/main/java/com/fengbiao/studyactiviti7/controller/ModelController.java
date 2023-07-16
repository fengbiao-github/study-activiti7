package com.fengbiao.studyactiviti7.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping("/model")
public class ModelController {

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = "请假流程模型";
            String key = "leaveProcess";
            String desc = "请输入描述信息~";
            int version = 1;
            // 初始化一个空模型
            Model model = repositoryService.newModel();
            model.setName(name);
            model.setKey(key);
            model.setVersion(version);
            // 封裝模型json对象
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 0);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
            model.setMetaInfo(modelObjectNode.toString());
            // 存储模型对象（表 ACT_RE_MODEL ）
            repositoryService.saveModel(model);
            // 封装模型对象基础数据json串
            // {"id":"canvas","resourceId":"canvas","stencilset":{"namespace":"http://b3mn.org/stencilset/bpmn2.0#"},"properties":{"process_id":"未定义"}}
            ObjectNode editorNode = objectMapper.createObjectNode();
            // editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.replace("stencilset", stencilSetNode);
            // 标识key
            ObjectNode propertiesNode = objectMapper.createObjectNode();
            propertiesNode.put("process_id", key);
            editorNode.replace("properties", propertiesNode);
            // 存储模型对象基础数据（表 ACT_GE_BYTEARRAY ）
            repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));
            // 编辑流程模型时,只需要直接跳转此url并传递上modelId即可
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + model.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
