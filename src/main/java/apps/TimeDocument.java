package apps;


public class TimeDocument extends DocumentDecorator {
    public TimeDocument(Document document) {
        super(document);
    }

    public String parse(String path) {
        long startTime = System.nanoTime();

        String result =  super.parse(path);

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.println("Elapsed time in seconds: " + elapsedTime/ 1_000_000_000.0);

        return result;
    }
}
