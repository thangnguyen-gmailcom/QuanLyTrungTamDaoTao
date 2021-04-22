package com.tht.demo.controller.api;


import com.tht.demo.model.Blog;
import com.tht.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/blogPages")
public class APIBlogPageController {

    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page){
        try {
            Page<Blog> blogs = blogService.showAll(PageRequest.of(page,8, Sort.by("id").descending()));

            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
