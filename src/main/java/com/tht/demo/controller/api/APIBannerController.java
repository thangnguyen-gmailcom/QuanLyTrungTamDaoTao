package com.tht.demo.controller.api;


import com.tht.demo.model.Banner;
import com.tht.demo.model.Blog;
import com.tht.demo.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/banner")
public class APIBannerController {

    @Autowired
    private BannerService bannerService;


    @GetMapping("")
    public ResponseEntity<?> showAll(@RequestParam(value = "page",required = false,defaultValue = "0") int page
    ) {
        try {
            Page<Banner> banners = bannerService.showAll(PageRequest.of(page,6, Sort.by("id").descending()));

            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            bannerService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String description,@PageableDefault(size = 6) Pageable pageable
    ){
        try {
            return new ResponseEntity<>(bannerService.search(description,pageable), HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
