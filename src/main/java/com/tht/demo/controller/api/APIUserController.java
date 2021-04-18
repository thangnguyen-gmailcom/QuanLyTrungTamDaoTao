package com.tht.demo.controller.api;

import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.User;
import com.tht.demo.repository.UserRepository;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/user")
public class APIUserController {
    @Autowired
    private UserService userService;

    private static String UPLOAD_DIR = "/";

    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page) {
        try {
            Page<User> userList = userService.showAllEmployee(PageRequest.of(page,8));
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/editForm")
    public ResponseEntity<?> multiUploadFileModel(HttpServletRequest request, @ModelAttribute User user) {


        String result = null;
        try {

            result = this.saveUploadedFiles(request, user.getImageUrl());

        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);

    }

    // Save Files
    private String saveUploadedFiles(HttpServletRequest request, MultipartFile[] files) throws IOException {
        String UPLOAD_DIR = System.getProperty("user.dir")+"/src/main/resources/static/upload";
        String uploadRootPath = request.getServletContext().getRealPath("upload");

        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        //
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        //
        Map<File, String> uploadedFiles = new HashMap();
        for (MultipartFile fileData : files) {

            // Tên file gốc tại Client.
            String name = fileData.getOriginalFilename();
            System.out.println("Client File Name = " + name);

            if (name != null && name.length() > 0) {
                try {
                    // Tạo file tại Server.
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

                    // Luồng ghi dữ liệu vào file trên Server.
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(fileData.getBytes());
                    stream.close();

                    BufferedOutputStream streamLocal = new BufferedOutputStream(new FileOutputStream(UPLOAD_DIR));
                    streamLocal.write(fileData.getBytes());
                    streamLocal.close();
                    //
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        return uploadRootDir.toString();
    }


}
