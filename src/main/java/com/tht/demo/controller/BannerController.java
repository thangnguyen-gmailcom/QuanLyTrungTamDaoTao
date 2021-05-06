package com.tht.demo.controller;


import com.tht.demo.dto.MyUserDetails;
import com.tht.demo.model.Banner;
import com.tht.demo.model.Blog;
import com.tht.demo.model.User;
import com.tht.demo.service.BannerService;
import com.tht.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private UserService userService;

    @Autowired
    private BannerService bannerService;


    @GetMapping("")
    public String showAll(){return "manager-page/banner-list";}


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("banner", new Banner());
        return "manager-page/banner-add";
    }

    @PostMapping("/add")
    public String doAdd(HttpServletRequest request, @Valid @ModelAttribute("banner") Banner banner, BindingResult result, RedirectAttributes attributes, Pageable pageable) {

        try {
            if (result.hasFieldErrors()) {
                return "manager-page/banner-add";
            }
            String res = null;
            Page<Banner> bannerList = bannerService.showAll(pageable);
            for (Banner tempBanner : bannerList) {
                if ((tempBanner.getDescription()).equals(banner.getDescription()) && (tempBanner.getId()) != banner.getId()) {
                    attributes.addFlashAttribute("error", "Nội dung đã tồn tại...!!!");
                    return "redirect:/banner";
                }
            }


            Optional<User> staff = userService.findByEmail(getPrincipal());
            if(banner.getImage().isEmpty()){
                banner.setImage("default-image.jpg");
            }

            res = this.saveUploadedFiles(request,banner.getImageUrl(), banner);
            bannerService.save(banner);
            attributes.addFlashAttribute("mess", "Thêm mới thành công...!!!");
            return "redirect:/banner";
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "error");
            return "redirect:/banner";
        }
    }


    @GetMapping("/edit/{id}")
    public String edit( @PathVariable long id, Model model)  {
        Optional<Banner> banner = bannerService.findById(id);
        if (banner != null) {
            model.addAttribute("banner", banner.get());
            return "manager-page/banner-edit";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit")
    public String doEdit(HttpServletRequest request, @Valid @ModelAttribute("banner") Banner banner, BindingResult result, RedirectAttributes attributes, Pageable pageable) {
        try {
            if (result.hasErrors()) {
                return "manager-page/banner-edit";
            }
            String res = null;
            Page<Banner> bannerList = bannerService.showAll(pageable);
            for (Banner tempBanner : bannerList) {
                if ((tempBanner.getDescription()).equals(banner.getDescription()) && (tempBanner.getId()) != banner.getId()) {
                    attributes.addFlashAttribute("error", "Nội dung đã tồn tại...!!!");
                    return "redirect:/banner";
                }
            }
            res= this.saveUploadedFiles(request, banner.getImageUrl(), banner);
            bannerService.save(banner);
            attributes.addFlashAttribute("mess", "Thay đổi thành công...!!!");
        } catch (Exception e) {
            e.getMessage();
            attributes.addFlashAttribute("error", "Có Lỗi Xảy Ra");
        }
        return "redirect:/banner";
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
    private String saveUploadedFiles(HttpServletRequest request, MultipartFile[] files, Banner banner) throws IOException {
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
                    banner.setImage(name);
                    System.out.println("Write file: " + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file: " + name);
                }
            }
        }
        return uploadRootDir.toString();
    }
}
