package apps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedDocument extends DocumentDecorator {
    private static final String DB_URL = "jdbc:sqlite:db.sqlite";

    public CachedDocument(Document document) {
        super(document);
    }

    public String parse(String path) {
        String query = "SELECT parsed_string FROM files WHERE path = ?";
        String result = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, path);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("found");
                result = rs.getString("parsed_string");
            } else {
                result = super.parse(path);
                String insertQuery = "INSERT INTO files (path, parsed_string) VALUES (?, ?)";

                try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, path);
                    insertStmt.setString(2, result);
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result = super.parse(path);
        }

        return result;
    }
}
