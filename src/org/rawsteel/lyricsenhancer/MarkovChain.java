package org.rawsteel.lyricsenhancer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MarkovChain {
    static final int MAX_CHAIN_LENGTH = 2;

    static final Random rand = new Random();

    private static class Chain implements Comparable<Chain> {
        public List<String> words;

        public Chain() {
            words = new ArrayList<String>();
        }

        public Chain shift(String word) {
            Chain c = new Chain();
            c.words.addAll(words);
            c.words.add(word);
            
            if (c.words.size() > MAX_CHAIN_LENGTH) {
                c.words.remove(0);
            }
            return c;
        }

        @Override
        public int compareTo(Chain that) {
            if (that.words.size() != this.words.size()) {
                return this.words.size() - that.words.size();
            }

            for (int i = 0; i < this.words.size(); i++) {
                int comparison = (this.words.get(i)).compareTo(that.words.get(i));
                if (comparison != 0) {
                    return comparison;
                }
            }

            return 0;
        }

        @Override
        public int hashCode() {
            int code = 0;
            for (int i = 0; i < this.words.size(); i++) {
                code += this.words.get(i).hashCode();
            }
            return code;
        }

        @Override
        public String toString() {
            StringBuilder retVal = new StringBuilder();
            for (int i = 0; i < words.size(); i++) {
                retVal.append(words.get(i)).append(" ");
            }
            return retVal.toString();
        }
    }

    private static Map<Chain, Map<String, Integer>> readFile(File f) throws IOException{
        Map<Chain, Map<String, Integer>> m = new TreeMap<Chain, Map<String, Integer>>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), Charset.forName("ISO-8859-1")));
        String line;

        while ((line = in.readLine()) != null) {
            String[] words = line.split("\\s+");
            Chain c = new Chain();
            for (String word : words) {
                word = word.replaceAll(",", "");
                word = word.replaceAll("\\.", "");
                word = word.replaceAll("!", "");
                word = word.replaceAll("-", "");
                word = word.trim();
                Map<String, Integer> freq = m.get(c);
                if (freq == null) {
                    freq = new HashMap<String, Integer>();
                    freq.put(word, 1);
                } else {
                    Integer count = freq.get(word);
                    if (count == null) {
                        count = new Integer(0);
                    }
                    freq.put(word, count.intValue() + 1);
                }
                m.put(c, freq);
                c = c.shift(word);
            }
        }

        return m;
    }

    private static String generate(Map<Chain, Map<String, Integer>> frequencies) {
        StringBuilder result = new StringBuilder();
        Chain c = new Chain();

        while (frequencies.containsKey(c)) {
            String word = randomWord(frequencies.get(c));
            c = c.shift(word);
            result.append(word).append(" ");
        }
        
        return result.toString();
    }

    private static String randomWord(Map<String, Integer> frequencies) {
        int total = 0;
        Iterator<Integer> it = frequencies.values().iterator();
        while (it.hasNext()) {
            total += it.next();
        }

        int value = rand.nextInt(total) + 1;
        Iterator<Map.Entry<String, Integer>> it2 = frequencies.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<String, Integer> entry = it2.next();
            Integer freq = (Integer) entry.getValue();
            if (freq.intValue() >= value) {
                return (String)entry.getKey();
            } else {
                value -= freq.intValue();
            }
        }

        // this should not happen!! 
        throw new RuntimeException("Bug in the random word picker!");
    }

    public static void main(String[] args) throws IOException {
        File f = new File("songs/christmas swedish/o helga natt.txt");
        Map<Chain, Map<String, Integer>> m = readFile(f);
        for (int i = 0; i < 10; i++) {
            System.out.println(generate(m));
        }
    }
}