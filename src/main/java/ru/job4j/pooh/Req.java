package ru.job4j.pooh;

public class Req {
    private final String httpRequestType;
    private final String method;
    private final String mode;
    private final String text;
    private final String id;

    private Req(String httpRequestType, String method, String mode, String text, String id) {
        this.httpRequestType = httpRequestType;
        this.method = method;
        this.mode = mode;
        this.text = text;
        this.id = id;
    }

    public static Req of(String content) {
        String[] contentLines = content.split(System.lineSeparator());
        String[] firstLine = contentLines[0].split(" ");
        String httpRequestType = firstLine[0];
        String[] path = firstLine[1].split("/");
        String method = path[1];
        String mode = path[2];
        String id = "";
        if (path.length > 3) {
            id = path[3];
        }
        String text = "";
        if (httpRequestType.equals("POST")) {
            text = contentLines[contentLines.length - 1];
        }
        return new Req(httpRequestType, method, mode, text, id);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String text() {
        return text;
    }

    public String id() {
        return id;
    }
}
