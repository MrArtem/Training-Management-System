package com.exadel.training.notification.help.impl;

import javax.annotation.PostConstruct;
import javax.security.auth.login.Configuration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

/**
 * Created by ayudovin on 13.10.2015.
 */
import com.exadel.training.notification.help.MessageGenerator;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageGeneratorHTML implements MessageGenerator {

    private final String HEADER = "";

    private final String FOOTER = "";

    private final String SITE_URI = "http://localhost:3000/#/";

    private SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-YYYY");

    private SimpleDateFormat formatTime = new SimpleDateFormat("H-m");

    private freemarker.template.Configuration cfg;

    @PostConstruct
    public void init() throws IOException {
        cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(this.getClass(), "/");

        cfg.setDirectoryForTemplateLoading(Paths.get(".", "messageForm").toFile());
    }

    public String getTextActionTrainer(String title, String place, long time) {
        Date date = new Date(time);
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/actionTrainer.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("date", formatDate.format(date));
            data.put("time", formatTime.format(date));
            data.put("title", title);
            data.put("place", place);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextActionListener(String title, String place, long time, boolean isHour) {
        Date date = new Date(time);
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/actionListener.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("date", formatDate.format(date));
            data.put("time", formatTime.format(date));
            data.put("title", title);
            data.put("place", place);
            data.put("isHour", isHour);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextChangeListener(String title, long id) {
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/changeListener.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextConfirmTrainer(String title, long id, String state, boolean answer) {
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/confirmTrainer.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            data.put("answer", answer);
            data.put("state", state);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextRemoveTrainingListener(String title, long id) {
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/removalTrainingListener.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextRequestFeedback(String title, long id, String nameListener) {
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/requestFeedback.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("name", nameListener);
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextResponseFeedback(String title, long id, String nameListener, String nameTrainer) {
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/responseFeedback.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("nameEmployee", nameListener);
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            data.put("nameTrainer", nameTrainer);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextNotificationAdminAboutShortage(String title, long id, boolean isDay, int listenerNumber) {
        StringWriter writer = new StringWriter();
        try {
            Template template = cfg.getTemplate("/countListener.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("isDay", isDay);
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            data.put("count", listenerNumber);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextRaceMoreThreeHours(String title, long id, long idEmployee, long time) {
        StringWriter writer = new StringWriter();
        Date date = new Date(time);
        try {
            Template template = cfg.getTemplate("/moreThreeHours.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("date", formatDate.format(date));
            data.put("time", formatTime.format(date));
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            data.put("uriYes", SITE_URI + "race/" + id + "/listener/" +
                    idEmployee + "/answer/" + true);
            data.put("uriNo", SITE_URI + "race/" + id + "/listener/" +
                    idEmployee + "/answer/" + false);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }

    public String getTextRaceLessThreeHours(String title, long id, long idEmployee, long time) {
        StringWriter writer = new StringWriter();
        Date date = new Date(time);
        try {
            Template template = cfg.getTemplate("/lessThree.ftl");
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("date", formatDate.format(date));
            data.put("time", formatTime.format(date));
            data.put("uri", SITE_URI + "trainings/" + id);
            data.put("title", title);
            data.put("uriYes", SITE_URI + "race/" + id + "/listener/" +
                    idEmployee + "/answer/" + true);
            template.process(data, writer);
        } catch (Exception e) {
            System.out.println(e);
        }
        return writer.toString();
    }
}