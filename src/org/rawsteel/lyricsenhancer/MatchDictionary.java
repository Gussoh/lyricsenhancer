/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eklann
 */
public class MatchDictionary {
    private WordClassifier classifier;
    
    private HashMap<String, Integer> wordSyllables;
    private HashMap<String, TreeSet<Integer>> wordClasses;
    private HashMap<Integer, TreeSet<String>> syllableWords;
    private HashMap<Integer, TreeSet<String>> classWords;
    
    public MatchDictionary() {
        classifier = new WordClassifier();
        
        wordSyllables = new HashMap<>();
        wordClasses = new HashMap<>();
        syllableWords = new HashMap<>();
        classWords = new HashMap<>();
    }
    
    public static MatchDictionary loadFromFile(String filename) {
        XStream xstream = new XStream();
        return (MatchDictionary)(xstream.fromXML(new File(filename)));
    }
    
    public boolean saveToFile(String filename) {
        PrintWriter writer = null;
        try {
            XStream xstream = new XStream();
            
            writer = new PrintWriter(filename);
            writer.print(xstream.toXML(this));
            writer.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MatchDictionary.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
            
            return false;
        }
    }
    
    public int getSyllables(String word) {
        if (wordSyllables.containsKey(word)) {
            return wordSyllables.get(word);
        } else {
            int nSyllables = classifier.CountSyllables(word);
            
            //Add to forward map
            wordSyllables.put(word, nSyllables);
            
            //Add to reverse map
            if (!syllableWords.containsKey(nSyllables)) {
                syllableWords.put(nSyllables, new TreeSet<String>());
            }
            
            syllableWords.get(nSyllables).add(word);
            
            return nSyllables;
        }
    }
    
    public TreeSet<Integer> getClasses(String word) {
        if (wordClasses.containsKey(word)) {
            return wordClasses.get(word);
        } else {
            TreeSet<Integer> classes = classifier.getWordClasses(word);
            
            //Add to forward map
            wordClasses.put(word, classes);
            
            //Add to reverse map
            for (int c : classes) {
                if (!classWords.containsKey(c)) {
                    classWords.put(c, new TreeSet<String>());
                }
                classWords.get(c).add(word);
            }
            
            return classes;
        }
    }
    
    public TreeSet<String> getSyllableWords(int nSyllables) {
        if (syllableWords.containsKey(nSyllables)) {
            return syllableWords.get(nSyllables);
        }
        
        return new TreeSet<String>();
    }
    
    public TreeSet<String> getClassWords(int classId) {
        if (classWords.containsKey(classId)) {
            return classWords.get(classId);
        }
        
        return new TreeSet<String>();
    }
    
    public TreeSet<String> getSyllableClassWords(int nSyllables, int classId) {
        if (syllableWords.containsKey(nSyllables)) {
            if (classWords.containsKey(classId)) {
                TreeSet<String> tmpSet = (TreeSet<String>)(syllableWords.get(nSyllables).clone());
                tmpSet.retainAll(classWords.get(classId));
                return tmpSet;
            }

            return new TreeSet<String>();
        }
        
        return new TreeSet<String>();
    }
}
