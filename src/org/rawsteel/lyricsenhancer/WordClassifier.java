/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eklann
 */
public class WordClassifier {
    public static HashSet<Character> vowels;
    
    public WordClassifier() {
        vowels = new HashSet<Character>();
         
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('y');
        vowels.add('å');
        vowels.add('ä');
        vowels.add('ö');
        vowels.add('a');    
    }
    
    public int CountSyllables(String word) {
        int numVowels = 0;
        boolean lastWasVowel = false;

        for (int i = 0; i < word.length(); i++) {
            char wc = word.charAt(i);
            
            if (vowels.contains(wc)) {
                //Count only non-diphtongs
                if (!lastWasVowel) {
                    numVowels++;
                }
                
                lastWasVowel = true;
            } else {
                lastWasVowel = false;
            }
        }
        
        //remove e & es, it's _usually? silent
        if (word.endsWith("es") || word.endsWith("e")) {
            numVowels--;
        }

        return numVowels;
    }
    
    public TreeSet<Integer> getWordClasses(String word) {
        TreeSet<Integer> classes = new TreeSet<Integer>();
        
        String url = String.format("http://api-demo.tyda.se/interface/xcall?rid=350001&v=2&c=xsearch_word,%s,sv,10,fixed,word_expanded", word);
        Scanner scanner = new Scanner(getWebpageAsString(url));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("<class_reference>")) {
                classes.add(Integer.parseInt(line.replaceAll("<class_reference>", "").replaceAll("</class_reference>", "")));
            }
        }
        
        return classes;
    }
    
    private String getWebpageAsString(String webpageUrl) {
        try {
            URL url = new URL(webpageUrl);
            URLConnection con = url.openConnection();
            Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
            Matcher m = p.matcher(con.getContentType());
            /* If Content-Type doesn't match this pre-conception, choose default and 
             * hope for the best. */
            String charset = m.matches() ? m.group(1) : "ISO-8859-1";
            Reader r = new InputStreamReader(con.getInputStream(), charset);
            StringBuilder buf = new StringBuilder();
            while (true) {
                int ch = r.read();
                if (ch < 0)
                    break;
                buf.append((char) ch);
            }
            return buf.toString();
        } catch (IOException ex) {
            Logger.getLogger(WordClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
