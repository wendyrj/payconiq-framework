package testObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(value = {"type", "language", "raw_url", "size", "truncated"})
public class GistFile {
    private String content;
    private String filename;

    public GistFile() {
    }

    public GistFile(String content, String filename) {
        this.content = content;
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename);
    }

    @Override
    public boolean equals(Object obj) {
        return equalsFiles((GistFile) obj);
    }

    public boolean equalsFiles(GistFile file) {
        String cleanContent = content.trim();
        String cleanFileContent = file.getContent().trim();
        return (cleanContent.equals(cleanFileContent) && this.filename.equals(file.getFilename()));
    }
}
