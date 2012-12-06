/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eklann
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String xmlFile = "data/Christmas.sv.xml";
        String songDir = "songs/christmas swedish/";
    
        MatchDictionary dictionary = new MatchDictionary("sv");
        
        if (new File(xmlFile).exists()) {
            dictionary = MatchDictionary.loadFromFile(xmlFile);
        }
        
        File tmpFile = new File(songDir);
        String[] tmpList = tmpFile.list(new ExtensionFilter("txt"));
        for ( String filename : tmpList) {
            for (String word : getWordsFromFile(songDir + filename)) {
                dictionary.getSyllables(word);
                dictionary.getClasses(word);
                System.out.println(word);
            }
            dictionary.saveToFile(xmlFile);
        }
        

        
        System.out.println("");
        
        for (int key : dictionary.getSyllableKeys())
            System.out.println("Words with "+key+" syllables: " + dictionary.getSyllableWords(key));
        
        for (int key : dictionary.getClassKeys())
            System.out.println("Words with classId "+key+": " + dictionary.getClassWords(key));
        
        dictionary.saveToFile(xmlFile);
    }
    
    private static HashSet<String> getWordsFromFile(String filename) throws FileNotFoundException {
        HashSet<String> words = new HashSet<String>();
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), Charset.forName("ISO-8859-1")));
            String line;
            while ((line = in.readLine()) != null) {
                for (String word : line.split("\\s+"))
                    words.add(word.replaceAll("[^A-Öa-ö ]", ""));
            }
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        return words;
    } 
   
}
