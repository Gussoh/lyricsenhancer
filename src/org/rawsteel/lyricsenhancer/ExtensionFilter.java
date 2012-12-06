/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rawsteel.lyricsenhancer;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author eklann
 */
public class ExtensionFilter implements FilenameFilter {
    String ext;

    public ExtensionFilter(String ext) {
        this.ext = ("." + ext).toLowerCase();
    }

    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(ext);
    }
}
