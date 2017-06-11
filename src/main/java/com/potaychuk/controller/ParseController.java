package com.potaychuk.controller;

import com.potaychuk.parser.LogParser;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
@RestController
@RequestMapping(value = "/api")
public class ParseController {

    private LogParser logParser;



    @PostMapping(value = "/parse")
    public String parseFile(@RequestParam("file")String path) {
        File log = new File(path);
        if (!log.canRead()){
            return "cant read the file";
        }
        if (logParser!=null && logParser.isAlive()){
            logParser.interrupt();
        }
        logParser= new LogParser();
        logParser.setLog(log);
        logParser.start();
        return "watch bms-demo/target/classes/static/xml-log/demo.xml";

    }

}
