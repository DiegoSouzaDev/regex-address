package com.friday.service;

import com.friday.exception.NoHouseNumberFoundException;
import com.friday.parser.AddressParser;
import com.friday.parser.ForeignNumberInStreetNameAddressParser;
import com.friday.parser.ForeignNumberStartingAddress;
import com.friday.parser.GermanFormatAddressParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Diego de Souza on 16/11/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTest {

    @Autowired
    ParserService service;

    @Test
    public void identifyParseReturnGermanFormatAddressTest() throws NoHouseNumberFoundException {
        String address = "Musterstrasse 45";
        AddressParser parser = service.identifyParser(address);
        assertThat(parser, instanceOf(GermanFormatAddressParser.class));

    }

    @Test
    public void identifyParseReturnForeignNumberStartingParserTest() throws NoHouseNumberFoundException {
        String address = "45, Musterstrasse";
        AddressParser parser = service.identifyParser(address);
        assertThat(parser, instanceOf(ForeignNumberStartingAddress.class));

    }

    @Test
    public void identifyParseReturnForeignNumberInStreetNameParserTest() throws NoHouseNumberFoundException {
        String address = "Calle 13 No 235";
        AddressParser parser = service.identifyParser(address);
        assertThat(parser, instanceOf(ForeignNumberInStreetNameAddressParser.class));

    }

    @Test(expected = NoHouseNumberFoundException.class)
    public void identifyParseThrowNoHouseNumberExceptionTest() throws NoHouseNumberFoundException {
        String address = "Rua XV de Novembro esquina com João Colin";
        AddressParser parser = service.identifyParser(address);

    }

}