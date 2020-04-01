package cn.lyc.activititest;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivititestApplicationTests {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    @Test
    public void deployment() {

        repositoryService.createDeployment().addClasspathResource("process/leave.bpmn")
                .addClasspathResource("process/leave.png")
                .name("请假流程4").deploy();
    }

    @Test
    public void startTask() {

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        System.out.println("流程id为: " + processInstance.getId());
    }

    @Test
    public void findTask() {

        List<Task> list = taskService.createTaskQuery().taskAssignee("bb").includeProcessVariables().list();
//        List<Task> list = taskService.createTaskQuery().taskId("47505").includeProcessVariables().list();
        Task task = list.get(0);
        String taskId = task.getId();
        Map<String, Object> variables = task.getProcessVariables();
        System.out.println("流程参数为: " + JSON.toJSONString(variables));
        System.out.println("代办任务id为: " + taskId);
    }

    @Test
    public void complateTask() {

        String taskId = "52504";
        List<String> param = new ArrayList<>();
        param.add("zhangsan3");
        param.add("lisi3");
        param.add("wangwu3");

        Map<String, Object> variables = new HashMap<>();
        variables.put("users5", param);

        taskService.complete(taskId,variables);
        System.out.println("完成任务!");
    }

    @Test
    public void getBpmn() throws IOException {

        FileOutputStream fis = new FileOutputStream("d:/test/approve.png");

        InputStream in = repositoryService.getResourceAsStream("1", "approve.approve.png");
        byte[] tmp = new byte[in.available()];
        in.read(tmp);
        fis.write(tmp);
        fis.close();
        in.close();
        System.out.println("输出完成!");
    }

}
