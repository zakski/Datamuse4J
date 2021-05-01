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
