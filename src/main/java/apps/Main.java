package apps;

public class Main {
    public static void main(String[] args) {
        Document document = new SmartDocument();
        document = new CachedDocument(new TimeDocument(document));
        document.parse("path");
    }
}