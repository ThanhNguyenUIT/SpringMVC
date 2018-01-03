package com.thanh.controllers;

import com.thanh.Exercise1.Student;
import com.thanh.service.StudentService;
import com.thanh.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class StudentController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request) {
        FileUtil fileUtil = new FileUtil();
        StudentService studentService = new StudentService();

        String fileUrl = fileUtil.storeFileUploaded(request);

        if (fileUrl.equals("")) {
            request.setAttribute("isStored", "Fail!");
        } else {
            request.setAttribute("isStored", "Upload Success!");

            List<Student> students = fileUtil.getListStudent(fileUrl);

            studentService.process((ArrayList<Student>) students);
        }
        return "upload";
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public String showRead(HttpServletRequest request){
        StudentService studentService = new StudentService();
        List<Student> students = studentService.get();

        if(students != null){
            request.setAttribute("students", students);
            request.setAttribute("isReadable", "Read Data Success!");
        } else {
            request.setAttribute("isReadable", "Read Fail!");
        }
        return "read";
    }
}
