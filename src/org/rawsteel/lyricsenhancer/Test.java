/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eklann
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MatchDictionary dictionary = new MatchDictionary();
        
        String[] testStrings = new String[] {"test", "apa", "melon", "vatten", "leksakståg", "bajs", "avloppsbrunn"};

        for (String s : testStrings) {
            dictionary.getSyllables(s);
            dictionary.getClasses(s);
        }
        
        System.out.println("Words with 2 syllables: " + dictionary.getSyllableWords(2));
        System.out.println("Words with classId 2: " + dictionary.getClassWords(2));
        
        dictionary.saveToFile("asd");
    }
}
