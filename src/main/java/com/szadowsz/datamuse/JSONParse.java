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

import com.codesnippets4all.json.parsers.JsonParserFactory;
import com.codesnippets4all.json.parsers.JSONParser;

import java.util.List;
import java.util.Map;

/**
 * A JSON parser for the data returned from Datamuse.
 *
 * @author sjblair
 * @since 21/02/15
 */
public class JSONParse {

    public JSONParse() {

    }

    /**
     * A JSON parser for the words returned in the data.
     * @param in JSON data returned from the DatamuseQuery class.
     * @return An array of the words.
     */
    public String[] parseWords(String in) {
        JsonParserFactory factory=JsonParserFactory.getInstance();
        JSONParser parser=factory.newJsonParser();
        Map jsonData=parser.parseJson(in);
        List al= (List) jsonData.get("root");
        String[] results = new String[al.size()];
        for (int i = 0; i < al.size(); i++) {
            results[i] = (String) ((Map)al.get(i)).get("word");
        }
        return results;
    }

    /**
     * A JSON parser for the word scores returned in the data.
     * @param in JSON data returned from the DatamuseQuery class.
     * @return An array of the scores.
     */
    public int[] parseScores(String in) {
        JsonParserFactory factory=JsonParserFactory.getInstance();
        JSONParser parser=factory.newJsonParser();
        Map jsonData=parser.parseJson(in);
        List al= (List) jsonData.get("root");
        int[] results = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            results[i] = Integer.parseInt((String) ((Map) al.get(i)).get("score"));
        }
        return results;
    }
}
