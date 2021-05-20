package com.tht.demo.controller;


import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.User;
import com.tht.demo.service.UserService;
import com.tht.demo.validate.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/profileEdit")
public class ProfileEditController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String profileEdit(Model model) {
        try {
            Optional<User> user = userService.findByEmail(getPrincipal());
            model.addAttribute("user", user.get());
            return "manager-page/profile-edit";
        } catch (Exception e) {
            return "/error";
        }
    }

    @PostMapping("")
    public String editProfile(HttpServletRequest request, @Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("imageUrl") MultipartFile[] imageUrl) {
        new UserValidator().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "manager-page/profile-edit";
        } else {
            String result = null;
            Optional<User> staff1 = userService.findByPhoneNumber(user.getPhoneNumber());
            user.setEditedDate(LocalDateTime.now());
            if (staff1.isPresent()) {
                if (!staff1.get().getPhoneNumber().equals(user.getPhoneNumber())) {
                    redirectAttributes.addFlashAttribute("messError", "Số điện thoại đã tồn tại");
                    return "redirect:/profileEdit";
                } else {
                    redirectAttributes.addFlashAttribute("mess", "Chỉnh sửa thành công");
                    try {
                        result = this.saveUploadedFiles(request, imageUrl, user);
                        userService.save(user);
                        return "redirect:/profileEdit";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            redirectAttributes.addFlashAttribute("mess", "Chỉnh sửa thành công");
            try {
                result = this.saveUploadedFiles(request, imageUrl, user);
                userService.save(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:/profileEdit";
        }
    }

    // Save Files
    private String saveUploadedFiles(HttpServletRequest request, MultipartFile[] files, User user) throws IOException {
        String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/upload";
        String uploadRootPath = request.getServletContext().getRealPath("upload");

        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        File uploadDir = new File(UPLOAD_DIR);
        //
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
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

                    File localFile = new File(uploadDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream streamLocal = new BufferedOutputStream(new FileOutputStream(localFile));
                    streamLocal.write(fileData.getBytes());
                    streamLocal.close();
                    user.setImage(name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        return uploadRootDir.toString();
    }


    private String getPrincipal() {
        String userName = null;
        Object printObject = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (printObject instanceof MyUserDetails) {
            userName = ((MyUserDetails) printObject).getUsername();
        } else {
            userName = printObject.toString();
        }
        return userName;
    }
}
