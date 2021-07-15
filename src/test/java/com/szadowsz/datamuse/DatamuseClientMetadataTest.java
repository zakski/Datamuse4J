package com.szadowsz.datamuse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatamuseClientMetadataTest {

    /**
     * words with a meaning similar to ringing in the ears 	/words?ml=ringing+in+the+ears
     */
    @Test
    void metadataCorrectionTest() throws DatamuseException, IOException {
        DatamuseClient dmuse = new DatamuseClient();
        List<WordResult> results = dmuse.metadataOf("Abad");
        WordResult result = results.get(0);
        assertEquals(3, result.getCorrections().size());
    }
}
