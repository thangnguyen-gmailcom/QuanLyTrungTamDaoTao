package com.tht.demo.controller;


import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.Blog;
import com.tht.demo.model.ExamType;
import com.tht.demo.model.User;
import com.tht.demo.repository.BlogRepository;
import com.tht.demo.service.BlogService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogManager")
public class BlogManagerController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("")
    public String blogManagerList() {
        return "manager-page/blog-manager-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("blog", new Blog());
        return "manager-page/blog-manager-add";
    }

    @PostMapping("/add")
    public String doAdd(HttpServletRequest request,@Valid @ModelAttribute("blog") Blog blog, BindingResult result, RedirectAttributes attributes) {

        try {
            if (result.hasFieldErrors()) {
                return "manager-page/blog-manager-add";
            }
            String res = null;
            Optional<User> staff = userService.findByEmail(getPrincipal());
            blog.setUser(staff.get());
            blog.setCreatedDate(LocalDateTime.now());
            res = this.saveUploadedFiles(request,blog.getImageUrl(), blog);
            blogService.save(blog);
            attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
            return "redirect:/blogManager";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "error");
            return "redirect:/blogManager";
        }
    }


    @GetMapping("/edit/{id}")
    public String edit( @PathVariable long id, Model model)  {
        Optional<Blog> blog = blogService.findById(id);
        if (blog != null) {
            model.addAttribute("blog", blog.get());
            return "manager-page/blog-manager-edit";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit")
    public String doEdit(HttpServletRequest request,@Valid @ModelAttribute("blog") Blog blog, BindingResult result, RedirectAttributes attributes) {
        try {
            if (result.hasErrors()) {
                return "manager-page/blog-manager-edit";
            }
            String res = null;
            List<Blog> blogs = blogRepository.findAll();
            for (Blog blog1 : blogs) {
                if ((blog.getTitle()).equals(blog1.getTitle()) && (blog.getId()) != blog1.getId()) {
                    attributes.addFlashAttribute("mess", "Tên tiêu đề đã tồn tại...!!!");
                    return "/manager-page/blog-manager-edit";
                }
            }
            res= this.saveUploadedFiles(request, blog.getImageUrl(), blog);
            blog.setCreatedDate(LocalDateTime.now());
            blogService.save(blog);
            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("mess", "Error");
        }
        return "redirect:/blogManager";
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
    private String saveUploadedFiles(HttpServletRequest request, MultipartFile[] files, Blog blog) throws IOException {
        String UPLOAD_DIR = System.getProperty("user.dir")+"/src/main/resources/static/upload";
        String uploadRootPath = request.getServletContext().getRealPath("upload");

        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        File uploadDir = new File(UPLOAD_DIR);
        //
        // Tạo thư mục gốc upload nếu nó không tồn tại.
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        if(!uploadDir.exists()){
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
                    blog.setImage(name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        return uploadRootDir.toString();
    }


}
