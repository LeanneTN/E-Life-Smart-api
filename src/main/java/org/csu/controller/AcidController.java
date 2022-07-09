package org.csu.controller;

import org.csu.domain.Acid;
import org.csu.service.AcidService;
import org.csu.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acid")
public class AcidController {
    @Autowired
    private AcidService acidService;

    @GetMapping("/get_acid")
    public ResponseResult getAcid(){
        return acidService.getAcid();
    }

    @PostMapping("/insert_acid")
    public ResponseResult insertAcid(@RequestBody Acid acid){
        return acidService.insertAcid(acid);
    }
}
