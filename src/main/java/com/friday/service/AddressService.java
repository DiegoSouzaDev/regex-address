package com.friday.service;

import com.friday.exception.NoHouseNumberFoundException;
import com.friday.parser.AddressParser;
import com.friday.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private ParserService parserService;

    public AddressVO parse(String address) throws NoHouseNumberFoundException {
        final AddressParser parser = parserService.identifyParser(address);

        return parser.parse(address);
    }



}
