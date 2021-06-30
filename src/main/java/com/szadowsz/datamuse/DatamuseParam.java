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

/**
 * "/words" - This endpoint returns a list of words (and multiword expressions) from a given vocabulary that match a
 * given set of constraints.
 *
 * The first four parameters (rd, sl, sp, rel_[code], and v) can be thought of as hard constraints on the result set,
 * while the next three (topics, lc, and rc) can be thought of as context hints. The latter only impact the order in
 *
 * All parameters are optional.
 *
 * /sug - This resource is useful as a backend for “autocomplete” widgets on websites and apps when the vocabulary of
 * possible search terms is very large. It provides word suggestions given a partially-entered query using a combination
 * of the operations described in the “/words” resource above. The suggestions perform live spelling correction and
 * intelligently fall back to choices that are phonetically or semantically similar when an exact prefix match can't be
 * found.
 *
 * The endpoint produces JSON output similar to the /words resource .
 */
public class DatamuseParam {

    /**
     * Definitions 	Produced in the defs field of the result object. The definitions are from WordNet. If the word is an
     * inflected form (such as the plural of a noun or a conjugated form of a verb), then an additional defHeadword
     * field will be added indicating the base form from which the definitions are drawn.
     */
    public static final String META_FLAG_D = "d";

    /**
     * Parts of speech 	One or more part-of-speech codes will be added to the tags field of the result object. "n" means
     * noun, "v" means verb, "adj" means adjective, "adv" means adverb, and "u" means that the part of speech is none of
     * these or cannot be determined. Multiple entries will be added when the word's part of speech is ambiguous, with
     * the most popular part of speech listed first. This field is derived from an analysis of Google Books Ngrams data.
     */
    public static final String META_FLAG_P = "p";

    /**
     * Syllable count 	Produced in the numSyllables field of the result object. In certain cases the number of
     * syllables may be ambiguous, in which case the system's best guess is chosen based on the entire query.
     */
    public static final String META_FLAG_S = "s";

    /**
     * Pronunciation 	Produced in the tags field of the result object, prefixed by "pron:". This is the system's best
     * guess for the pronunciation of the word or phrase. The format of the pronunication is a space-delimited list of
     * Arpabet phoneme codes. If you add "&ipa=1" to your API query, the pronunciation string will instead use the
     * International Phonetic Alphabet. Note that for terms that are very rare or outside of the vocabulary, the
     * pronunciation will be guessed based on the spelling. In certain cases the pronunciation may be ambiguous, in
     * which case the system's best guess is chosen based on the entire query.
     */
    public static final String META_FLAG_R = "r";

    /**
     * Word frequency 	Produced in the tags field of the result object, prefixed by "f:". The value is the number of
     * times the word (or multi-word phrase) occurs per million words of English text according to Google Books Ngrams.
     */
    public static final String META_FLAG_F = "f";

    public static final String META_FLAGS = META_FLAG_S + META_FLAG_D + META_FLAG_F + META_FLAG_R + META_FLAG_P;

    public static Code[] REL_CODES = {Code.REL_JJA, Code.REL_JJB, Code.REL_SYN, Code.REL_TRG, Code.REL_ANT,
            Code.REL_SPC, Code.REL_GEN, Code.REL_COM, Code.REL_PAR, Code.REL_BGA, Code.REL_BGB, Code.REL_RHY,
            Code.REL_NRY, Code.REL_HOM, Code.REL_CNS};

    /**
     * Parameter Codes for more complex queries.
     */
    public enum Code {
        /**
         * Means like constraint: require that the results have a meaning related to this string value, which can be any
         * word or sequence of words. (This is effectively the reverse dictionary feature of OneLook.)
         */
        ML,

        /**
         * Means like constraint: require that the results have a meaning related to this string value, which can be any
         * word or sequence of words. (This is effectively the reverse dictionary feature of OneLook.)
         * <p>
         * Deprecated by the restful service we connect to.
         */
        @Deprecated
        OLD_ML,

        /**
         * Sounds like constraint: require that the results are pronounced similarly to this string of characters. (If the
         * string of characters doesn't have a known pronunciation, the system will make its best guess using a
         * text-to-phonemes algorithm.)
         */
        SL,

        /**
         * Spelled like constraint: require that the results are spelled similarly to this string of characters, or that
         * they match this wildcard pattern. A pattern can include any combination of alphanumeric characters, spaces, and
         * two reserved characters that represent placeholders — * (which matches any number of characters) and ? (which
         * matches exactly one character).
         */
        SP,

        /**
         * Popular nouns modified by the given adjective, per Google Books Ngrams 	gradual -> increase.
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_JJA,

        /**
         * Popular adjectives used to modify the given noun, per Google Books Ngrams 	beach -> sandy.
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_JJB,

        /**
         * Synonyms (words contained within the same WordNet synset) 	ocean -> sea.
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_SYN,

        /**
         * "Triggers" (words that are statistically associated with the query word in the same piece of text.) 	cow -> milking.
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_TRG,

        /**
         * Antonyms (per WordNet) 	late -> early
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_ANT,

        /**
         * "Kind of" (direct hypernyms, per WordNet) 	gondola -> boat
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_SPC,

        /**
         * "More general than" (direct hyponyms, per WordNet) 	boat -> gondola
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_GEN,

        /**
         * "Comprises" (direct holonyms, per WordNet) 	car -> accelerator
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_COM,

        /**
         * "Part of" (direct meronyms, per WordNet) 	trunk -> tree
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_PAR,

        /**
         * Frequent followers (w′ such that P(w′|w) ≥ 0.001, per Google Books Ngrams) 	wreak -> havoc
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_BGA,

        /**
         * Frequent predecessors (w′ such that P(w|w′) ≥ 0.001, per Google Books Ngrams) 	havoc -> wreak
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_BGB,

        /**
         * Rhymes ("perfect" rhymes, per RhymeZone) 	spade -> aid
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_RHY,

        /**
         * Approximate rhymes (per RhymeZone) 	forest -> chorus
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_NRY,

        /**
         * Homophones (sound-alike words) 	course -> coarse
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_HOM,

        /**
         * Consonant match 	sample -> simple
         * <p>
         * Related word constraint: requires that the results, when paired with the word in this parameter, are in a
         * predefined lexical relation indicated by [code] of rel_[code]. Any number of these parameters may be specified
         * any number of times. An assortment of semantic, phonetic, and corpus-statistics-based relations are available.
         * <p>
         * At this time, these relations are available for English-language vocabularies only.
         */
        REL_CNS,

        /**
         * Identifier for the vocabulary to use. If none is provided, a 550,000-term vocabulary of English words and
         * multiword expressions is used. (The value es specifies a 500,000-term vocabulary of words from Spanish-language
         * books. The value enwiki specifies an approximately 6 million-term vocabulary of article titles from the
         * English-language Wikipedia, updated monthly.) Please contact us to set up a custom vocabulary for your application.
         */
        V,

        /**
         * Topic words: An optional hint to the system about the theme of the document being written. Results will be skewed
         * toward these topics. At most 5 words can be specified. Space or comma delimited. Nouns work best.
         */
        TOPICS,

        /**
         * Left context: An optional hint to the system about the word that appears immediately to the left of the target
         * word in a sentence. (At this time, only a single word may be specified.)
         */
        LC,

        /**
         * Right context: An optional hint to the system about the word that appears immediately to the right of the target
         * word in a sentence. (At this time, only a single word may be specified.)
         */
        RC,

        /**
         * Maximum number of results to return, not to exceed 1000. (default: 100)
         */
        MAX,

        /**
         * A list of single-letter codes (no delimiter) requesting that extra lexical knowledge be included with the
         * results.
         */
        MD,

        /**
         * Query echo: The presence of this parameter asks the system to prepend a result to the output that describes
         * the query string from some other parameter, specified as the argument value. This is useful for looking up
         * metadata about specific words. For example, /words?sp=flower&qe=sp&md=fr can be used to get the pronunciation
         * and word frequency for flower.
         */
        QE;

        @Override
        public java.lang.String toString() {
            return super.toString().toLowerCase();
        }
    }
}
