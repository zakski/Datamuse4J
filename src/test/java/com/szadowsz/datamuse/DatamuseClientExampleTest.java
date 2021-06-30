/**
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 * <p>
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.szadowsz.datamuse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Quick and Dirty unit test taken from examples on the Datamuse website to ensure future changes compatibility with the
 * original library.
 */
public class DatamuseClientExampleTest {

    /**
     * words with a meaning similar to ringing in the ears 	/words?ml=ringing+in+the+ears
     */
    @Test
    void meansLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.meansLike("ringing in the ears");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("tinnitus")).count();
        assertEquals(1, has);
    }

    /**
     * words with a meaning similar to ringing in the ears 	/words?ml=ringing+in+the+ears
     */
    @Test
    void meansLikeEnWikiExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.V, "enwiki");
        List<WordResult> results = dmuse.meansLike("ringing in the ears",options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("tinnitus")).count();
        assertEquals(1, has);
    }

    @Test
    void meansLikeSpanishExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.V, "es");
        List<WordResult> results = dmuse.meansLike("pequeño",options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("diminuto")).count();
        assertEquals(1, has);
    }
    /**
     * words related to duck that start with the letter b 	/words?ml=duck&sp=b*
     */
    @Test
    void relatedStartsWithExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.SP, "b*");
        List<WordResult> results = dmuse.meansLike("duck",options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("bird")).count();
        assertEquals(1, has);
    }

    /**
     * words related to spoon that end with the letter a 	/words?ml=spoon&sp=*a
     */
    @Test
    void relatedEndsWithExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.SP, "*a");
        List<WordResult> results = dmuse.meansLike("spoon",options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("spatula")).count();
        assertEquals(1, has);
    }

    /**
     * words that sound like jirraf 	/words?sl=jirraf
     */
    @Test
    void soundsLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.soundsLike("jirraf");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("giraffe")).count();
        assertEquals(1, has);
    }

    /**
     * words that start with t, end in k, and have two letters in between 	/words?sp=t??k
     */
    @Test
    void speltLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.speltLike("t??k");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("took")).count();
        assertEquals(1, has);
    }

    /**
     * words that are spelled similarly to hipopatamus 	/words?sp=hipopatamus
     */
    @Test
    void speltLikeExample2Test() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.speltLike("hipopatamus");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("hippopotamus")).count();
        assertEquals(1, has);
    }

    /**
     * words that often follow "drink" in a sentence, that start with the letter w 	/words?lc=drink&sp=w*
     */
    @Test
    void followWordExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.LC, "drink");
        options.put(DatamuseParam.Code.SP, "w*");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("water")).count();
        assertEquals(1, has);
    }

    @Test
    void prefixHintExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.prefixHintSuggestions("rawand");
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("rwanda")).count();
        assertEquals(1, has);
    }

    /**
     * For example, /words?sp=flower&qe=sp&md=fr can be used to get the pronunciation and word frequency for flower.
     */
    @Test
    void metadataExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.metadataOf("flower");
        WordResult result = results.get(0);
        assertEquals("flower", result.getWord());
        assertEquals(2,result.getNumSyllables());
        assertEquals(3,result.getDefs().size());
        assertNull(result.getDefHeadword());
        assertEquals(4,result.getTags().size());
        assertEquals("n",result.getWordType());
        assertEquals("F L AW1 ER0 ",result.getPronunciation());
    }

    /**
     * For example, /words?sp=flower&qe=sp&md=fr can be used to get the pronunciation and word frequency for flower.
     */
    @Test
    void metadataExample2Test() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.metadataOf("flowers");
        WordResult result = results.get(0);
        assertEquals("flowers", result.getWord());
        assertEquals(2,result.getNumSyllables());
        assertEquals(3,result.getDefs().size());
        assertEquals("flower",result.getDefHeadword());
        assertEquals(4,result.getTags().size());
        assertEquals("n",result.getWordType());
        assertEquals("F L AW1 ER0 Z ",result.getPronunciation());
    }
}
