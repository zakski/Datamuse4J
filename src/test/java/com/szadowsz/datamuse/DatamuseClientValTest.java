package com.szadowsz.datamuse;

import org.junit.jupiter.api.Test;

import java.util.List;

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
    void soundsLikeNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.soundsLike(null);
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
    void prefixHintNullValidationTest() {
        DatamuseClient dmuse = new DatamuseClient();
        assertThrows(DatamuseException.DatamuseValException.class, () -> {
            List<WordResult> results = dmuse.prefixHintSuggestions(null);
        });
    }
}
