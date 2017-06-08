package com.potaychuk.controller;

import com.potaychuk.meta.MetaLog;
import com.potaychuk.parser.LogParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Potaychuk Sviatoslav on 08.06.2017.
 */
@RestController
@RequestMapping(value = "/api")
public class ParseController {

    @PostMapping(value = "/parse")
    public String parseFile(@RequestParam("file")String path) {
        new LogParser().parse(new File(path));
        return "well done";
    }

}
