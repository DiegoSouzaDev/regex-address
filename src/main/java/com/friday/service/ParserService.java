package com.friday.service;

import com.friday.AddressFormatEnum;
import com.friday.exception.NoHouseNumberFoundException;
import com.friday.parser.AddressParser;
import com.friday.parser.ForeignNumberInStreetNameAddressParser;
import com.friday.parser.ForeignNumberStartingAddress;
import com.friday.parser.GermanFormatAddressParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.friday.AddressFormatEnum.*;

/**
 * Created by Diego de Souza on 16/11/18.
 */

@Service
public class ParserService {

    public static final Pattern numberPattern = Pattern.compile("\\d+");

    private Map<String, AddressParser> parserMap;

    @Autowired
    private ForeignNumberInStreetNameAddressParser foreignNumberInStreetNameAddressParser;

    @Autowired
    private ForeignNumberStartingAddress foreignNumberStartingAddress;

    @Autowired
    private GermanFormatAddressParser germanFormatAddressParser;

    @PostConstruct
    private void initParserMap() {
        parserMap = new HashMap<>();
        parserMap.put(GERMAN_FORMAT.name(), germanFormatAddressParser);
        parserMap.put(FOREIGN_NUMBER_STARTING_FORMAT.name(), foreignNumberStartingAddress);
        parserMap.put(FOREIGN_NUMBER_IN_STREET_NAME_FORMAT.name(), foreignNumberInStreetNameAddressParser);
    }

    private AddressParser getParser(AddressFormatEnum addressFormat){
        return parserMap.get(addressFormat.name());
    }


    public AddressParser identifyParser(String address) throws NoHouseNumberFoundException {
        final Matcher matcher = numberPattern.matcher(address);

        boolean startsWithNumber = matcher.lookingAt();
        boolean hasMoreThenOneNumber = verifyMultiplesOccurrences(matcher);
        boolean startsWithStreetName = !hasMoreThenOneNumber && !startsWithNumber;

        AddressParser parser = null;

        if (startsWithStreetName) {
            parser = getParser(GERMAN_FORMAT);

        } else if (startsWithNumber) {
            parser = getParser(FOREIGN_NUMBER_STARTING_FORMAT);

        } else if (hasMoreThenOneNumber) {
            parser = getParser(FOREIGN_NUMBER_IN_STREET_NAME_FORMAT);

        }

        return parser;
    }

    private boolean verifyMultiplesOccurrences(final Matcher matcher) throws NoHouseNumberFoundException {
        matcher.reset();
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }

        if(counter == 0){
            throw new NoHouseNumberFoundException();
        }

        return counter > 1;
    }

}
