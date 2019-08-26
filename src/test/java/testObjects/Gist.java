package testObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(value = { "forks_url",  "commits_url", "node_id", "git_pull_url", "git_push_url", "html_url",
                                "comments", "user", "comments_url", "owner", "forks", "history", "truncated"})
public class Gist {
    private String description;
    private boolean publicField;
    private Map <String, GistFile> files;
    private String url;
    private String id;
    private String created_at;
    private String updated_at;

    public Gist() {}

    public Gist(String description, boolean publicField, Map<String, GistFile> files) {
        this.description = description;
        this.publicField = publicField;
        this.files = files;
    }

    public Gist(String description, boolean publicField, Map<String, GistFile> files, String url, String id, String created_at, String updated_at) {
        this.description = description;
        this.publicField = publicField;
        this.files = files;
        this.url = url;
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Map<String, GistFile> getFiles() {
        return files;
    }

    public void setFiles(Map<String, GistFile> files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("public")
    public boolean isPublicField() {
        return publicField;
    }

    public void setPublicField(boolean publicField) {
        this.publicField = publicField;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
