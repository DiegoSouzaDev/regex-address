package com.friday.parser;

import com.friday.vo.AddressVO;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Diego de Souza on 14/11/18.
 */
@Component
public class ForeignNumberStartingAddress implements AddressParser {
    /*
    Regular expression that groups the number first, then groups the rest of the address.
    Doing that it separates number and street name from addresses that statrts with numbers.
     */
    public static final Pattern pattern = Pattern.compile("(\\d+)(.+)");

    public static final int HOUSE_NUMBER = 1;
    public static final int STREET_NAME = 2;

    @Override
    public AddressVO parse(String address) {
        final Matcher matcher = getMatcher(address);
        matcher.find();

        final String street = matcher.group(STREET_NAME).replace(",", "").trim();
        final String houseNumber = matcher.group(HOUSE_NUMBER);
        return new AddressVO(street, houseNumber);
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }
}
