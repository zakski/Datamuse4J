package com.szadowsz.datamuse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatamuseClientComplexExampleTest {

    /**
     * words with a meaning similar to ringing in the ears 	/words?ml=ringing+in+the+ears
     */
    @Test
    void meansLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.ML, "ringing in the ears");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("tinnitus")).count();
        assertEquals(1, has);
    }

    /**
     * words related to duck that start with the letter b 	/words?ml=duck&sp=b*
     */
    @Test
    void relatedStartsWithExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.ML, "duck");
        options.put(DatamuseParam.Code.SP, "b*");
        List<WordResult> results = dmuse.complexQuery(options);
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
        options.put(DatamuseParam.Code.ML, "spoon");
        options.put(DatamuseParam.Code.SP, "*a");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("spatula")).count();
        assertEquals(1, has);
    }

    /**
     * words that sound like jirraf 	/words?sl=jirraf
     */
    @Test
    void soundsLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.SL, "jirraf");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("giraffe")).count();
        assertEquals(1, has);
    }

    /**
     * words that start with t, end in k, and have two letters in between 	/words?sp=t??k
     */
    @Test
    void speltLikeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.SP, "t??k");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("took")).count();
        assertEquals(1, has);
    }

    /**
     * words that are spelled similarly to hipopatamus 	/words?sp=hipopatamus
     */
    @Test
    void speltLikeExample2Test() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.SP, "hipopatamus");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("hippopotamus")).count();
        assertEquals(1, has);
    }

    /**
     * words that rhyme with forgetful 	/words?rel_rhy=forgetful
     */
    @Test
    void rhymeExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_RHY, "forgetful");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("netful")).count();
        assertEquals(1, has);
    }

    /**
     * words that rhyme with grape that are related to breakfast 	/words?ml=breakfast&rel_rhy=grape
     */
    @Test
    void rhymeExample2Test() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_RHY, "grape");
        options.put(DatamuseParam.Code.ML,"breakfast");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("crepe")).count();
        assertEquals(1, has);
    }

    /**
     * adjectives that are often used to describe ocean 	/words?rel_jjb=ocean
     */
    @Test
    void adjectivesExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_JJB, "ocean");
        List<WordResult> results = dmuse.complexQuery(options);
        assertEquals("open", results.get(0).getWord());
    }

    /**
     * adjectives describing ocean sorted by how related they are to temperature 	/words?rel_jjb=ocean&topics=temperature
     */
    @Test
    void adjectivesExample2Test() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_JJB, "ocean");
        options.put(DatamuseParam.Code.TOPICS,"temperature");
        List<WordResult> results = dmuse.complexQuery(options);
        assertEquals("cold", results.get(0).getWord());
    }

    /**
     * nouns that are often described by the adjective yellow 	/words?rel_jja=yellow
     */
    @Test
    void adjectivesExample3Test() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_JJA, "yellow");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("flower")).count();
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

    /**
     * words that are triggered by (strongly associated with) the word "cow" 	/words?rel_trg=cow
     */
    @Test
    void strongAssociationExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        Map<DatamuseParam.Code, String> options = new HashMap<>();
        options.put(DatamuseParam.Code.REL_TRG, "cow");
        List<WordResult> results = dmuse.complexQuery(options);
        long has = results.stream().filter(result -> result.getWord().equalsIgnoreCase("milking")).count();
        assertEquals(1, has);
    }


}
