package com.potaychuk.parser;

import com.potaychuk.XMLWriter.XMLWriter;
import com.potaychuk.domain.EventLog;
import com.potaychuk.meta.MetaLog;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
public class LogParser extends Thread {

    private EventLog eventLog;
    private XMLWriter xmlWriter;
    private File log;
    private File dist;

    @Override
    public void run() {
        try {
            eventLog = new EventLog();
            parse(log);
            String filePath = getClass().getClassLoader().getResource("static").getPath() + "/" + "xml-log/demo.xml";
            dist = new File(filePath.replace("%20", " "));
            if (dist.exists()) {
                dist.delete();
            }
            dist.createNewFile();
            xmlWriter = new XMLWriter();
            xmlWriter.writeToXml(eventLog, dist);
            sleep(MetaLog.TIME_TO_WAIT);
            System.out.println("Hi im awake");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(File file) {
        try {
            List<String> list = Files.readAllLines(file.toPath());
            List<String> list2 = list.parallelStream().
                    filter(p -> p.matches(MetaLog.SDLSIG_REGEX) || p.matches(MetaLog.STOPPING_REGEX)).
                    collect(Collectors.toList());
            eventLog.setEventsMessages(list2);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getLog() {
        return log;
    }

    public void setLog(File log) {
        this.log = log;
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public void setEventLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }
}
