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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eklann
 */
public class Test {
    public static void main(String[] args) throws Exception {
        WordClassifier wc = new WordClassifier();
        
        String[] testStrings = new String[] {"test", "apa", "melon", "vatten", "leksakståg", "bajs", "avloppsbrunn"};

        for (String s : testStrings) {
            System.out.println(String.format("Number of syllables in \"%s\": %d", s, wc.CountSyllables(s)));
            System.out.println(String.format("Classes for word \"%s\": ", s));

            List<Integer> wordClasses = wc.getWordClasses(s);
            for (int i : wordClasses)
                System.out.print(String.format("%d ", i));
            System.out.println("");
        }
    }
}
