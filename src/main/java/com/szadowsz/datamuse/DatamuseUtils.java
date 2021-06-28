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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class DatamuseUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatamuseClient.class);

    public static final String API_URL = "https://api.datamuse.com/";

    private DatamuseUtils() {
    }

    /**
     * Check that the end-user has passed in a valid vocabulary object.
     *
     * @param vocabObj the user-passed-in vocabulary query parameter
     * @return true if english, false otherwise
     * @throws DatamuseException.DatamuseValException if there is more than one vocab set or it is not of string type
     */
    private static boolean validateVocab(Object vocabObj) throws DatamuseException.DatamuseValException {
        if (vocabObj == null) {
            return true;

        } else if (vocabObj instanceof Iterable) {
            throw new DatamuseException.DatamuseValException("Multiple \"" + DatamuseParam.Code.V.name() + "\" detected. Only one should exist");
        } else if (vocabObj instanceof String) {
            String vocab = (String) vocabObj;
            switch (vocab) {
                case "enwiki":
                    return true;
                case "es":
                    return false;
                default:
                    logger.warn("Custom Vocabulary \"" + vocab + "\"Detected - defaulting rel_[code] validation to English");
                    return true;
            }
        } else {
            throw new DatamuseException.DatamuseValException("\"" + DatamuseParam.Code.V.name() + "\" should be of type String");
        }
    }

    private static String sanitiseValue(String key, Object value){
        return replaceSpaces(value.toString());
    }

    static String replaceSpaces(String wordPhrase) {
        return wordPhrase.replaceAll(" ", "+");
    }

    static void validateWordPhrase(String wordPhrase) throws DatamuseException.DatamuseValException {
        if (wordPhrase == null) {
            throw new DatamuseException.DatamuseValException("WordPhrase cannot be null");
        }
    }

    /**
     * Check that the end-user has passed in valid parameters.
     *
     * @param params the user-passed-in query parameters
     * @param <T> the object type the end user has chosen to pass in
     * @throws DatamuseException.DatamuseValException if the validation of the user parameters has failed
     */
    static <T> void validateQueryMap(Map<DatamuseParam.Code, T> params) throws DatamuseException.DatamuseValException {
        if (null == params){
            throw new DatamuseException.DatamuseValException("Param map cannot be null for this query");
        }

        // validate vocabulary
        boolean isEnglish = validateVocab(params.get(DatamuseParam.Code.V));

        // make sure relational codes only exist for english vocabs
        if (!isEnglish) {
            Optional<DatamuseParam.Code> code = Arrays.stream(DatamuseParam.REL_CODES).filter(params::containsKey).findFirst();
            if (code.isPresent()) {
                throw new DatamuseException.DatamuseValException("REL_[***] code \"" + code.get() + "\" detected. REL " +
                        "codes cannot be used for non-english language vocabularies");
            }
        }
    }

    static void validateQueryMap(DatamuseParam.Code code, Map<DatamuseParam.Code, String> params) throws DatamuseException.DatamuseValException {
        if (null != params) {
            if (params.containsKey(code)) {
                throw new DatamuseException.DatamuseValException("Duplicate code \"" + code + "\" detected. REL " +
                        "codes cannot be used for non-english language vocabularies");
            }

            validateQueryMap(params);
        }
    }

    /**
     * Sanitise all the Keys and user supplied values
     *
     * @param params the user-passed-in query parameters
     * @param <T> the object type the end user has chosen to pass in
     * @return a Map of String/String Key/Value Pairs
     */
    static <T> Map<String, String> sanitiseQueryMap(Map<DatamuseParam.Code,T> params) {
        if (params == null){
            return new HashMap<>();
        }

        return params.entrySet().stream()
                .map(e -> new AbstractMap.SimpleImmutableEntry<>(e.getKey().toString(),sanitiseValue(e.getKey().toString(),e.getValue())))
                .collect(Collectors.toMap(AbstractMap.SimpleImmutableEntry::getKey, AbstractMap.SimpleImmutableEntry::getValue));
    }
}
