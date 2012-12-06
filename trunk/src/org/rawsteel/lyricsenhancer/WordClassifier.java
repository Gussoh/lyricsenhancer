/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.util.HashSet;
import java.util.List;

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
    
    public List<String> getWordClasses() {
        return null;
    }
    Jaxb
}
