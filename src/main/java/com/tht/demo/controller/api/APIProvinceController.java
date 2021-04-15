package com.tht.demo.controller.api;

import com.tht.demo.model.District;
import com.tht.demo.model.Province;
import com.tht.demo.model.Ward;
import com.tht.demo.service.DistrictService;
import com.tht.demo.service.ProvinceService;
import com.tht.demo.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="api/provinces")
public class APIProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @GetMapping("/")
    public ResponseEntity<?> showProvinces(){
        try{
            List<Province> provinces = provinceService.showAll();
            return new ResponseEntity<>(provinces,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/districts/{provinceCode}")
    public ResponseEntity<?> showDistrictsByProvincesId(@PathVariable("provinceCode") String provinceCode){
        try{
            List<District> districts = districtService.showAll(provinceCode);
            return new ResponseEntity<>(districts,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/districts/wards/{districtsCode}")
    public ResponseEntity<?> showWardsByDistrictsId(@PathVariable("districtsCode") String distortsCode){
        try{
            List<Ward> wards = wardService.showAll(distortsCode);
            return new ResponseEntity<>(wards,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/districts/ward/{wardCode}")
    public ResponseEntity<?> findWardsByWardCode(@PathVariable String wardCode){
        try{
            Optional<Ward> ward = wardService.findByWardCode(wardCode);
            return new ResponseEntity<>(ward.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
