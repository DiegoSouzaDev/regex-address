package com.friday.parser;

import com.friday.vo.AddressVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Diego de Souza on 16/11/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GermanFormatAddressParserTest {

    @Autowired
    private GermanFormatAddressParser parser;

    @Test
    public void parseTest() {
        final String address = "SonnenAlle, 12";
        final AddressVO addressVO = parser.parse(address);
        assertThat(addressVO.getStreet(), equalTo("SonnenAlle"));
        assertThat(addressVO.getHousenumber(), equalTo("12"));

    }
}