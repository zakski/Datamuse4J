/**
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.szadowsz.datamuse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Quick and Dirty unit test taken from examples on the Datamuse website to ensure future changes compatibility with the
 * original library.
 */
public class DatamuseQueryTest {

    @Test
    void similarMeaning() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.findSimilar("ringing in the ears");
        assertTrue(results.contains("tinnitus"));
    }

    @Test
    void similarStartsWith() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.findSimilarStartsWith("duck","b");
        assertTrue(results.contains("bird"));
    }

    @Test
    void similarEndsWith() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.findSimilarEndsWith("spoon","a");
        assertTrue(results.contains("spatula"));
    }

    @Test
    void soundsLike() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.soundsSimilar("jirraf");
        assertTrue(results.contains("giraffe"));
    }

    @Test
    void speltLikeWithMissingLetters() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.wordsStartingWithEndingWith("t","k",2);
        assertTrue(results.contains("tank"));
    }
    @Test
    void speltLike() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.speltSimilar("hipopatamus");
        assertTrue(results.contains("hippopotamus"));
    }

    @Test
    void rhymesWith() {
        DatamuseQuery dmuse = new DatamuseQuery();
        String results = dmuse.prefixHintSuggestions("rawand");
        assertTrue(results.contains("rwanda"));
    }
}
