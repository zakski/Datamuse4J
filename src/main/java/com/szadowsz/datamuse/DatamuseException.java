package com.szadowsz.datamuse;

public class DatamuseException extends Exception {

    DatamuseException(String msg) {
        super(msg);
    }

    DatamuseException(String msg, Throwable t) {
        super(msg, t);
    }

    public static class DatamuseValException extends DatamuseException {
        DatamuseValException(String msg) {
            super(msg);
        }
    }
    public static class DatamuseHttpException extends DatamuseException {

        private final int code;
        private final String message;

        DatamuseHttpException(String msg, int code, String message) {
            super(msg);
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}