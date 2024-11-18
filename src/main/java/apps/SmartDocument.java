package apps;

public class SmartDocument implements Document {
    @Override
    public String parse(String path) {
        for (int i = 0; i < Math.pow(10, 6); i++) {
            if (i * 1000 % 3 == 0) {;}
        }
        return "Hello";
    }
}
