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
public class ForeignNumberInStreetNameAddressParserTest {

    @Autowired
    private  ForeignNumberInStreetNameAddressParser parser;

    @Test
    public void parseTest() {
        final String address = "Calle 39 No 1540";
        final AddressVO addressVO = parser.parse(address);
        assertThat(addressVO.getStreet(), equalTo("Calle 39"));
        assertThat(addressVO.getHousenumber(), equalTo("No 1540"));

    }
}