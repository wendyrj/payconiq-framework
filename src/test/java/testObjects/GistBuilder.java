package testObjects;

import utils.RandomUtils;

import java.util.Map;

public class GistBuilder {
    private Gist gist;

    public GistBuilder() {
        this.gist = new Gist();
    }

    public static Gist generateRandomGist() {
        return GistBuilder.withFiles(RandomUtils.generateRandomFileMap(3))
                .withDescription("Description_" + RandomUtils.randomFromChoices())
                .withPublicField(RandomUtils.getRandomBoolean()).build();
    }

    public static GistBuilder withFiles(Map<String, GistFile> files) {
        GistBuilder gistBuilder = new GistBuilder();
        gistBuilder.gist.setFiles(files);
        return gistBuilder;
    }

    public GistBuilder withDescription(String description) {
        this.gist.setDescription(description);
        return this;
    }

    public GistBuilder withPublicField(boolean field) {
        this.gist.setPublicField(field);
        return this;
    }

    public Gist build() {
        return this.gist;
    }
}
