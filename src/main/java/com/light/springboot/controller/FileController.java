package com.light.springboot.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Edison.Yin on 2019/6/25.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/upload-download")
    public void uploadAndDownload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        ServletContext context = request.getServletContext();

        // Get MIME type of the file
        String mimeType = context.getMimeType(file.getOriginalFilename());

        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
            logger.warn("context getMimeType is null");
        }
        logger.info("MIME type:" + mimeType+";charset=UTF-8");

        // set content attributes for the response
        response.setContentType(mimeType);

        // set header for response
        String header = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getOriginalFilename());
        response.setHeader(header, headerValue);
        OutputStream  outputStream = null;
        try {
            outputStream = response.getOutputStream();
//            InputStream inputStream = file.getInputStream();
//            IOUtils.copy(inputStream, outputStream);
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        }
    }

    @PostMapping("/csv-convert-html")
    public void csvConvertHtml(@RequestParam("file") MultipartFile file) {

    }
}
