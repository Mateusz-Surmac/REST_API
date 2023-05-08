package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyTasksAmountEmail(String message) {

        List<String> functionality2 = new ArrayList<>();
        functionality2.add("You can manage your tasks");
        functionality2.add("Provides connection with Trello Account");
        functionality2.add("Application allows sending tasks to Trello");

        String message2 = taskRepository.count() + " - number of tasks";

        Context context = new Context();
        context.setVariable("message", message2);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("preview_message", "Daily info");
        context.setVariable("goodbye_message", "Goodbye! Have a nice day");
        context.setVariable("show_button", true);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality2);
        return templateEngine.process("mail/daily-task-info", context);
    }
}