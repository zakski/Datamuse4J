package com.szadowsz.datamuse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DatamuseClientValTest {


    @Test
    void meansLikeNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.meansLike(null);
        });
    }

    @Test
    void meansLikeDuplicateParamValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            Map<DatamuseParam.Code, String> options = new HashMap<>();
            options.put(DatamuseParam.Code.ML, "duck");
            List<WordResult> results = dmuse.meansLike("duck",options);
        });
    }

    @Test
    void meansLikeSpanishExampleTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            Map<DatamuseParam.Code, String> options = new HashMap<>();
            options.put(DatamuseParam.Code.V, "es");
            options.put(DatamuseParam.Code.REL_JJB,"pequeño");
            List<WordResult> results = dmuse.complexQuery(options);
        });
    }

    @Test
    void soundsLikeNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.soundsLike(null);
        });
    }

    @Test
    void soundsLikeDuplicateParamValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            Map<DatamuseParam.Code, String> options = new HashMap<>();
            options.put(DatamuseParam.Code.SL, "duck");
            List<WordResult> results = dmuse.soundsLike("duck",options);
        });
    }

    @Test
    void speltLikeNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.speltLike(null);
        });
    }

    @Test
    void speltLikeDuplicateParamValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            Map<DatamuseParam.Code, String> options = new HashMap<>();
            options.put(DatamuseParam.Code.SP, "duck");
            List<WordResult> results = dmuse.speltLike("duck",options);
        });
    }

    @Test
    void complexLikeNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.complexQuery(null);
        });
    }

    @Test
    void prefixHintNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.prefixHintSuggestions(null);
        });
    }
}
