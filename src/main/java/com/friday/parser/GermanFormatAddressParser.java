package com.friday.parser;

import com.friday.vo.AddressVO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Diego de Souza on 14/11/18.
 */

@Component
public class GermanFormatAddressParser implements AddressParser {
    /*
    Regular expression that groups the text before finds a number and then groups the number and the rest of the string.
    By doing that identifies the german addresses, and others that starts with the street name and then have a house number that can
    have a letter in it.
     */
    public static final Pattern pattern = Pattern.compile("(.+?)(\\d+.+|\\d+)");

    public static final int HOUSE_NUMBER = 2;
    public static final int STREET_NAME = 1;

    @Override
    public AddressVO parse(String address) {
        final Matcher matcher = getMatcher(address);
        matcher.find();

        final String streetName = matcher.group(STREET_NAME).replace(",", "").trim();
        final String houseNumber = matcher.group(HOUSE_NUMBER);
        return new AddressVO(streetName, houseNumber);
    }


    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
