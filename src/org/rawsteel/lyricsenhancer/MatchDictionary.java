/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author eklann
 */
public class MatchDictionary {
    private HashMap<String, Integer> wordSyllables;
    private HashMap<String, HashSet<Integer>> wordClasses;
    private HashMap<Integer, HashSet<String>> syllableWords;
    private HashMap<Integer, HashSet<String>> classWords;
    
    public MatchDictionary() {
        wordSyllables = new HashMap<>();
        wordClasses = new HashMap<>();
        syllableWords = new HashMap<>();
        classWords = new HashMap<>();
    }
    
    public int getSyllables(String word) {
        return 0;//if ()
    }
}
