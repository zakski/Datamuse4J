package com.szadowsz.datamuse;

class DatamuseUtils {

    public static final String API_URL = "https://api.datamuse.com/";

    private DatamuseUtils(){}

    public static void validateWordPhrase(String wordPhrase) throws DatamuseException.DatamuseValException {
        if (wordPhrase == null){
            throw new DatamuseException.DatamuseValException("WordPhrase cannot be null");
        }
    }

    public static String replaceSpaces(String wordPhrase) {
        return wordPhrase.replaceAll(" ", "+");
    }
}
