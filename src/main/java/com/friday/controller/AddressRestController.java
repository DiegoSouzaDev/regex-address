package com.friday.controller;

import com.friday.exception.NoHouseNumberFoundException;
import com.friday.service.AddressService;
import com.friday.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/parse")
public class AddressRestController {

    @Autowired
    private AddressService service;

    @PostMapping(path = "/address/", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AddressVO parse(@RequestBody String address) throws NoHouseNumberFoundException {
        return service.parse(address);
    }


}
