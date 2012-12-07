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
    
    private String language;
    
    static {
        vowels = new HashSet<>();
         
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
    
    public WordClassifier(String language) {
        this.language = language;
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
        if (language.equals("en") && (word.endsWith("es") || word.endsWith("e"))) {
            numVowels--;
        }
        
        if (language.equals("en") && numVowels == 0 && word.length() > 0) {
            numVowels = 1;
        }
        
        return numVowels;
    }
    
    public TreeSet<Integer> getWordClasses(String word) {
        TreeSet<Integer> classes = new TreeSet<Integer>();
        
        String url = String.format("http://api-demo.tyda.se/interface/xcall?rid=350001&v=2&c=xsearch_word,%s,%s,10,fixed,word_expanded", word, language);

        String webpage = getWebpageAsString(url);
        if (webpage.equals(""))
            throw new NullPointerException();
        
        Scanner scanner = new Scanner(webpage);

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
            
            //con.setRequestProperty("User-Agent", "curl/7.21.0 (i486-pc-linux-gnu) libcurl/7.21.0 OpenSSL/0.9.8o zlib/1.2.3.4 libidn/1.15 libssh2/1.2.6");
            //con.setRequestProperty("Host", "api-demo.tyda.se");
            //con.setRequestProperty("Accept", "*/*");
            
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
