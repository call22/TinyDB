package cn.edu.thssdb.exception;

public class SyntaxErrorException extends RuntimeException {
    private final String message;

    public SyntaxErrorException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
