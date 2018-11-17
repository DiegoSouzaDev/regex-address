package com.friday.parser;

import com.friday.vo.AddressVO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Diego de Souza on 16/11/18.
 */
@Component
public class ForeignNumberInStreetNameAddressParser implements AddressParser {

    /*
    Regular expression that groups the text before the literal "No* where it is preceded with numbers.
    It separate street and house number in cases where the street name has numbers in it
     */

    public static final Pattern pattern = Pattern.compile("(.+?)(No \\d+|no \\d+)");
    public static final int HOUSE_NUMBER = 2;
    public static final int STREET_NAME = 1;

    @Override
    public AddressVO parse(final String address) {
        final Matcher matcher = getMatcher(address);
        matcher.find();

        final String street = matcher.group(STREET_NAME).trim();
        final String houseNumber = matcher.group(HOUSE_NUMBER).trim();
        return new AddressVO(street, houseNumber);
    }

    @Override
    public Pattern getPattern() {

        return pattern;
    }
}
