package com.szadowsz.datamuse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WordResult {

    @JsonProperty("word")
    private String word;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("numSyllables")
    private Integer numSyllables;
    @JsonProperty("tags")
    private List<String> tags;

    public String getWord() {
        return word;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getNumSyllables() {
        return numSyllables;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
