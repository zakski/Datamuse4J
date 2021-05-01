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

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Quick and Dirty unit test taken from examples on the Datamuse website to ensure future changes compatibility with the
 * original library.
 */
public class DatamuseClientTest {

    @Test
    void meansLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.meansLike("ringing in the ears");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("tinnitus")).count();
        assertEquals(1,has);
    }


    //    @Test
//    void similarStartsWith() {
//        DatamuseClient dmuse = new DatamuseClient();
//        String results = dmuse.findSimilarStartsWith("duck","b");
//        assertTrue(results.contains("bird"));
//    }
//
//    @Test
//    void similarEndsWith() {
//        DatamuseQuery dmuse = new DatamuseQuery();
//        String results = dmuse.findSimilarEndsWith("spoon","a");
//        assertTrue(results.contains("spatula"));
//    }
//
//    @Test
//    void speltLikeWithMissingLetters() {
//        DatamuseQuery dmuse = new DatamuseQuery();
//        String results = dmuse.wordsStartingWithEndingWith("t","k",2);
//        assertTrue(results.contains("tank"));
//    }

    @Test
    void soundsLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.soundsLike("jirraf");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("giraffe")).count();
        assertEquals(1,has);
    }

    @Test
    void speltLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.speltLike("hipopatamus");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("hippopotamus")).count();
        assertEquals(1,has);
    }

    @Test
    void prefixHintExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.prefixHintSuggestions("rawand");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("rwanda")).count();
        assertEquals(1,has);
      }
}
