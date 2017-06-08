package com.potaychuk.parser;

import com.potaychuk.XMLWriter.XMLWriter;
import com.potaychuk.domain.EventLog;
import com.potaychuk.meta.MetaLog;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
public class LogParser extends Thread{

    private EventLog eventLog;

    public static void main(String[] args) {
        LogParser logParser = new LogParser();
        logParser.setEventLog(new EventLog());
        logParser.parse(new File("C:\\Users\\Potaychuk Sviatoslav\\Desktop\\123.txt"));
        new XMLWriter().writeToXml(logParser.eventLog, new File("C:\\Users\\Potaychuk Sviatoslav\\IdeaProjects\\bms_test\\bms-test\\src\\main\\resources\\static\\demo.xml"));
        System.out.println(logParser.eventLog);
    }
    @Override
    public void run() {
        super.run();
    }

    public void parse(File file) {
        int count=0;
        try {
            String str = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
//            StringBuilder str = new StringBuilder();
//            List<String> list = Files.readAllLines(file.toPath());
//            for (String s: list){
//                str.append(s);
//            }
//            Pattern pattern = Pattern.compile("(.{28,29}((SdlSig\\s)|(Stopping)).*)([0-9]{8}\\.[0-9]{3})?:");
            Pattern pattern = Pattern.compile("(.{28}((SdlSig\\s)|(Stopping)).*)(?=(\\r\\n))");
//            Pattern pattern = Pattern.compile("(SdlSig\\s)|(Stopping)");
//            Pattern pattern = Pattern.compile("(.{28}SdlSig)");
//            Pattern pattern = Pattern.compile("(key=.*)([0-9]{8,8}\\\\.[0-9]{3,3})?:");
//            Pattern pattern = Pattern.compile("key=.{8}");
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                count++;
                eventLog.getEventsMessages().add(matcher.group());
//                System.out.println(test);
//                int start = matcher.start();
//                int end = matcher.end();
//                System.out.println(start);
//                System.out.println(end);

//                System.out.println(str.substring(start, end));
            }
//            while (matcher.find()){
//                System.out.println(str.substring(matcher.start(), matcher.end())+"====================");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Total Count=: "+count);
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public void setEventLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }
}
