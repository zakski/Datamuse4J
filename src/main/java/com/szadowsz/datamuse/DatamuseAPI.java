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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

/**
 * Sketch of the interface to
 */
interface DatamuseAPI {


    /**
     * Returns a list of similar words to the word/phrase supplied.
     *
     * @param wordPhrase A word or phrase.
     * @return A list of similar words.
     */
    @GET("words")
    Call<List<WordResult>> meansLike(@Query("ml") String wordPhrase, @QueryMap Map<String, String> options);

    /**
     * Find words which sound the same as the specified word/phrase when spoken.
     *
     * @param wordPhrase A word or phrase.
     * @return A list of words/phrases which sound similar when spoken.
     */
    @GET("words")
    Call<List<WordResult>> soundsLike(@Query("sl") String wordPhrase, @QueryMap Map<String, String> options);

    /**
     * Find words which are spelt the same as the specified word/phrase.
     *
     * @param wordPhrase A word or phrase.
     * @return A list of words/phrases which are spelt similar.
     */
    @GET("words")
    Call<List<WordResult>> speltLike(@Query("sp") String wordPhrase, @QueryMap Map<String, String> options);

    /**
     * Find words which are "like", for complex queries
     *
     * @param options the query options.
     * @return A list of words/phrases which are related to the query.
     */
    @GET("words")
    Call<List<WordResult>> complexLike(@QueryMap Map<String, String> options);


    /**
     * Returns suggestions for what the user may be typing based on what they have typed so far. Useful for
     * autocomplete on forms.
     *
     * @param wordPhrase The current word or phrase.
     * @return Suggestions of what the user may be typing.
     */
    @GET("sug")
    Call<List<WordResult>> prefixHintSuggestions(@Query("s") String wordPhrase);
}
