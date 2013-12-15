/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Gussoh
 */
public class MainFrame extends javax.swing.JFrame {

    private static final String christmasSv = "data/Christmas.sv.xml";
    private static final String christmasEn = "data/Christmas.en.xml";
    private static final String disneySv = "data/Disney.sv.xml";
    private static final String disneyEn = "data/Disney.en.xml";
    private static final String chalmersSv = "data/Chalmers.sv.xml";
    
    MatchDictionary dictionaryChristmasSv, dictionaryChristmasEn, dictionaryDisneySv, dictionaryDisneyEn, dictionaryChalmersSv;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        
        dictionaryChristmasSv = MatchDictionary.loadFromFile(christmasSv);
        dictionaryChristmasEn = MatchDictionary.loadFromFile(christmasEn);
        dictionaryDisneySv = MatchDictionary.loadFromFile(disneySv);
        dictionaryDisneyEn = MatchDictionary.loadFromFile(disneyEn);
        dictionaryChalmersSv = MatchDictionary.loadFromFile(chalmersSv);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        substitutionMode = new javax.swing.ButtonGroup();
        enhanceButton = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        originalLyrics = new javax.swing.JTextArea();
        amount = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        christmasSwedish = new javax.swing.JRadioButton();
        disneySwedish = new javax.swing.JRadioButton();
        christmasEnglish = new javax.swing.JRadioButton();
        disneyEnglish = new javax.swing.JRadioButton();
        chalmersSwedish = new javax.swing.JRadioButton();
        replaceSame = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lyrics Enhancer Platinum!");

        enhanceButton.setText("Enhance!");
        enhanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enhanceButtonActionPerformed(evt);
            }
        });

        originalLyrics.setColumns(20);
        originalLyrics.setRows(5);
        jScrollPane2.setViewportView(originalLyrics);

        tabPane.addTab("Original Lyrics", jScrollPane2);

        amount.setPaintLabels(true);
        amount.setPaintTicks(true);
        amount.setValue(20);
        amount.setBorder(javax.swing.BorderFactory.createTitledBorder("Amount of words to replace"));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mode"));

        substitutionMode.add(christmasSwedish);
        christmasSwedish.setSelected(true);
        christmasSwedish.setText("Christmas (Swedish)");

        substitutionMode.add(disneySwedish);
        disneySwedish.setText("Disney (Swedish)");

        substitutionMode.add(christmasEnglish);
        christmasEnglish.setText("Christmas (English)");

        substitutionMode.add(disneyEnglish);
        disneyEnglish.setText("Disney (English)");

        substitutionMode.add(chalmersSwedish);
        chalmersSwedish.setText("Chalmers (Svenska)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(christmasEnglish)
                    .addComponent(christmasSwedish)
                    .addComponent(disneySwedish)
                    .addComponent(disneyEnglish)
                    .addComponent(chalmersSwedish))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(christmasSwedish)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(christmasEnglish)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disneySwedish)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disneyEnglish)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chalmersSwedish)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        replaceSame.setSelected(true);
        replaceSame.setText("Findus does want this unchecked. Gussoh does want it checked. Yes Hello");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(replaceSame)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(enhanceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(replaceSame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(enhanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enhanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enhanceButtonActionPerformed
        MatchDictionary dictionary = dictionaryChristmasEn;
        if(christmasSwedish.isSelected()) {
            dictionary = dictionaryChristmasSv;
        } else if (disneyEnglish.isSelected()) {
            dictionary = dictionaryChristmasEn;
        } else if (disneySwedish.isSelected()) {
            dictionary = dictionaryDisneySv;
        } else if (chalmersSwedish.isSelected()) {
            dictionary = dictionaryChalmersSv;
        }
        
        String text = originalLyrics.getText();
        
        text = text.replaceAll(",", "");
        text = text.replaceAll("\\.", "");
        text = text.replaceAll("!", "");
        text = text.replaceAll("-", "");
        text = text.replaceAll("'", "");
        
        text = text.trim();
        
        if (text.length() < 1) {
            JOptionPane.showMessageDialog(this, "No lyrics :(");
            return;
        }
        
        HashMap<String, String> replacedWords = new HashMap<String, String>();
        String firstLine = null;
        StringBuilder newText = new StringBuilder();
        String[] lines = text.split("\n");
        int lineNo = 1;
        for (String line : lines) {
            System.out.println("Line = " + lineNo + " / " + lines.length);
            lineNo++;
            if (firstLine == null) {
                firstLine = line;
            }
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (replaceSame.isSelected() && replacedWords.containsKey(word.replaceAll("[^A-Öa-ö]", "").toLowerCase())) {
                    newText.append(replacedWords.get(word.replaceAll("[^A-Öa-ö]", "").toLowerCase()));
                } else if (Math.random() * 100 < amount.getValue()) {
                    String newWord = "";
                    try {
                        newWord = replace(dictionary, word);
                    } catch (Exception e) {
                        System.out.println("error on word " + word + "; " + e);
                        e.printStackTrace();
                    }
                    replacedWords.put(word.replaceAll("[^A-Öa-ö]", "").toLowerCase(), newWord);
                    newText.append(newWord);
                } else {
                    replacedWords.put(word.replaceAll("[^A-Öa-ö]", "").toLowerCase(), word.replaceAll("[^A-Öa-ö]", "").toLowerCase());
                    newText.append(word);
                }
                newText.append(' ');
            }
            newText.append('\n');
        }
        
        JTextArea newLyrics = new JTextArea(newText.toString());
        ScrollPane pane = new ScrollPane();
        pane.add(newLyrics);
        
        tabPane.addTab(firstLine, pane);
        tabPane.setTabComponentAt(tabPane.getTabCount() - 1, new ButtonTabComponent(tabPane));
        
    }//GEN-LAST:event_enhanceButtonActionPerformed

    private String replace(MatchDictionary dictionary, String subject) {
        
        if (subject.length() < 1) {
            return subject;
        }
        
        subject = subject.replaceAll("[^A-Öa-ö]", "");
        subject = subject.toLowerCase();
        
        int syllables = dictionary.getSyllables(subject);
        if (syllables == 0) {
            return subject;
        }
        
        TreeSet<String> classIds = dictionary.getClasses(subject);
        List<String> classIdsList = new ArrayList<>(classIds);
        Collections.shuffle(classIdsList);
        for (String classId : classIdsList) {
            TreeSet<String> syllableClassWords = dictionary.getSyllableClassWords(syllables, classId);
            if (!syllableClassWords.isEmpty()) {
                ArrayList<String> syllableClassWordsList = new ArrayList<String>(syllableClassWords);
                return syllableClassWordsList.get((int) (Math.random() * syllableClassWordsList.size()));
            }
        }
        
        return subject;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            // Set System L&F
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
           // handle exception
        }
        catch (ClassNotFoundException e) {
           // handle exception
        }
        catch (InstantiationException e) {
           // handle exception
        }
        catch (IllegalAccessException e) {
           // handle exception
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
        
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider amount;
    private javax.swing.JRadioButton chalmersSwedish;
    private javax.swing.JRadioButton christmasEnglish;
    private javax.swing.JRadioButton christmasSwedish;
    private javax.swing.JRadioButton disneyEnglish;
    private javax.swing.JRadioButton disneySwedish;
    private javax.swing.JButton enhanceButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea originalLyrics;
    private javax.swing.JCheckBox replaceSame;
    private javax.swing.ButtonGroup substitutionMode;
    private javax.swing.JTabbedPane tabPane;
    // End of variables declaration//GEN-END:variables
}
