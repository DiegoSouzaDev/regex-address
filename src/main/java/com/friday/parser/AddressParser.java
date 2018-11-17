package com.friday.parser;

import com.friday.vo.AddressVO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Diego de Souza on 14/11/18.
 */

public interface AddressParser {

    AddressVO parse(final String address);
    Pattern getPattern();

    default Matcher getMatcher(String address){
        return getPattern().matcher(address);

    }
}
