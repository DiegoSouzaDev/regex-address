package com.friday.service;

import com.friday.exception.NoHouseNumberFoundException;
import com.friday.parser.GermanFormatAddressParser;
import com.friday.vo.AddressVO;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTest {
    @MockBean
    private ParserService parserService;

    @MockBean
    private GermanFormatAddressParser germanFormatAddressParser;

    @Autowired
    private AddressService addressService;



    @Test
    public void parseAddressStringTest() throws NoHouseNumberFoundException {
        final String sentence = "Winterallee 3";
        final AddressVO addressVO = new AddressVO("Winterallee", "3");

        when(parserService.identifyParser(sentence)).thenReturn(germanFormatAddressParser);
        when(germanFormatAddressParser.parse(sentence)).thenReturn(addressVO);

        AddressVO currentAddressVO = addressService.parse(sentence);

        assertThat("Winterallee", equalTo(currentAddressVO.getStreet()));
        assertThat("3", equalTo(currentAddressVO.getHousenumber()));
        verify(parserService, times(1)).identifyParser(sentence);
    }

}
