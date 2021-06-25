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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.QueryMap;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatamuseClient {

    private final Logger logger = LoggerFactory.getLogger(DatamuseClient.class);

    private final Retrofit retrofit;

    private final DatamuseAPI service;

    public DatamuseClient(String url){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        service = retrofit.create(DatamuseAPI.class);
    }

    public DatamuseClient(){
        this(DatamuseUtils.API_URL);
    }

    private List<WordResult> executeCall(Call<List<WordResult>> call) throws DatamuseException, IOException {
        Response<List<WordResult>> response = call.execute();
        if (response.isSuccessful()){
            logger.debug("API call succeeded with " + response.code() + " - " + response.message());
            return response.body();
        } else {
            logger.debug("API call failed with " + response.code() + " - " + response.message());
            throw new DatamuseException.DatamuseHttpException("API call failed",response.code(),response.message());
        }
    }

    /**
     * Returns a list of similar words to the word/phrase supplied.
     *
     * @param wordPhrase A word or phrase.
     * @return A list of similar words.
     */
    public List<WordResult> meansLike(String wordPhrase) throws DatamuseException, IOException {
        // validation and sanitation
        DatamuseUtils.validateWordPhrase(wordPhrase);
        String sanWordPhrase = DatamuseUtils.replaceSpaces(wordPhrase);

        // execute api call
        logger.debug("Calling meanslike for wordphrase \"" + sanWordPhrase + "\"");
        return executeCall(service.meansLike(sanWordPhrase));
    }

    /**
     * Find words which sound the same as the specified word/phrase when spoken.
     *
     * @param wordPhrase A word or phrase.
     * @return A list of words/phrases which sound similar when spoken.
     */
   public List<WordResult> soundsLike(String wordPhrase) throws DatamuseException, IOException {
       // validation and sanitation
       DatamuseUtils.validateWordPhrase(wordPhrase);
       String sanWordPhrase = DatamuseUtils.replaceSpaces(wordPhrase);

       // execute api call
       logger.debug("Calling soundsLike for wordphrase \"" + sanWordPhrase + "\"");
       return executeCall(service.soundsLike(sanWordPhrase));
   }

    /**
     * Find words which are spelt the same as the specified word/phrase.
     *
     * @param wordPhrase A word or phrase.
     * @return A list of words/phrases which are spelt similar.
     */
    public List<WordResult> speltLike(String wordPhrase) throws DatamuseException, IOException {
        // validation and sanitation
        DatamuseUtils.validateWordPhrase(wordPhrase);
        String sanWordPhrase = DatamuseUtils.replaceSpaces(wordPhrase);

        // execute api call
        logger.debug("Calling speltLike for wordphrase \"" + sanWordPhrase + "\"");
        return executeCall(service.speltLike(sanWordPhrase));
    }

    /**
     * Returns suggestions for what the user may be typing based on what they have typed so far. Useful for
     * autocomplete on forms.
     *
     * @param wordPhrase The current word or phrase.
     * @return Suggestions of what the user may be typing.
     */
    public List<WordResult> prefixHintSuggestions(String wordPhrase) throws DatamuseException, IOException {
        // validation and sanitation
        DatamuseUtils.validateWordPhrase(wordPhrase);
        String sanWordPhrase = DatamuseUtils.replaceSpaces(wordPhrase);

        // execute api call
        logger.debug("Calling speltLike for wordphrase \"" + sanWordPhrase + "\"");
        return executeCall(service.prefixHintSuggestions(sanWordPhrase));
    }

    /**
     * Find words which are "like", for complex queries
     *
     * @param options a map of the user supplied query options.
     * @return A list of words/phrases which are related to the query.
     * @throws DatamuseException if there is a validation issue or a non HTTP-200 response is returned
     * @throws IOException if there is a general issue with the rest call
     */
    public <T> List<WordResult> complexQuery(Map<DatamuseParam.Code, String> options) throws DatamuseException, IOException {
        // validation of raw query params
        DatamuseUtils.validateQueryMap(options);

        // sanitise and convert to string/string map
        Map<String, String> map = DatamuseUtils.sanitiseQueryMap(options);

        // execute api call
        logger.debug("Calling complex query");
        return executeCall(service.complexLike(map));
    }
}
