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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean Class to store the results from Datamuse
 */
public class WordResult {

    @JsonProperty("word")
    private String word;
    @JsonProperty("defHeadword")
    private String defHeadword;
    @JsonProperty("numSyllables")
    private Integer numSyllables;
    @JsonProperty("score")
    private Integer score;

    @JsonProperty("defs")
    private List<String> defs;

    @JsonProperty("tags")
    private List<String> tags;

    // extracted from tags
    private String pronunciation;
    private Double frequency;
    private String wordType;
    private final List<String> corrections = new ArrayList<>();

    public String getWord() {
        return word;
    }

    public String getDefHeadword() {
        return defHeadword;
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

    public List<String> getDefs() {
        return defs;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getWordType() {
        return wordType;
    }

    public List<String> getCorrections() {
        return corrections;
    }

    public Double getFrequency(){
        return frequency;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefHeadword(String defHeadword) {
        this.defHeadword = defHeadword;
    }

    public void setNumSyllables(Integer syllables) {
        this.numSyllables = syllables;
    }

    public void setScore(Integer score) { this.score = score; }

    public void setDefs(List<String> defs) {
        this.defs = defs;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
        if (!tags.isEmpty()){
            tags.stream().filter(s -> s.startsWith("spellcor:")).map(s -> s.substring(9)).forEach(corrections::add);
            this.pronunciation = tags.stream().filter(s -> s.startsWith("pron:"))
                    .findFirst().map(s -> s.substring(5)).orElse(null);
            this.frequency = tags.stream().filter(s -> s.startsWith("f:"))
                    .findFirst().map(s -> Double.valueOf(s.substring(2))).orElse(0.0);
            this.wordType = tags.stream().filter(s -> !s.startsWith("spellcor:") && !s.startsWith("pron:") && !s.startsWith("f:") && !s.equals("query"))
                    .findFirst().orElse(null);
        }
    }
  }
