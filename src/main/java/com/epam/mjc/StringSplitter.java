package com.epam.mjc;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder strDelimiters = new StringBuilder();
        for (String delimiter : delimiters) {
            strDelimiters.append(Pattern.quote(delimiter));
        }
        String[] strings = source.split("[" + strDelimiters + "]");
        return Arrays.stream(strings).filter(x -> !x.isBlank()).map(String::trim).collect(Collectors.toList());
    }
}
