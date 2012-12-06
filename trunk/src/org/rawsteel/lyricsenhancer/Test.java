/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

/**
 *
 * @author eklann
 */
public class Test {
    public static void main(String[] args) {
        WordClassifier wc = new WordClassifier();
        
        String[] testStrings = new String[] {"test", "apa", "melon", "vatten", "leksakståg", "bajs", "avloppsbrunn"};

        for (String s : testStrings) {
            System.out.println(String.format("Number of syllables in \"%s\": %d", s, wc.CountSyllables(s)));
    
        }
    }
}
