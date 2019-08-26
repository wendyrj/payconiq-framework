package utils;

import org.apache.commons.lang3.RandomStringUtils;
import testObjects.GistFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static String randomFromChoices(String... choices) {
        return randomFromArray(choices);
    }

    public static String randomFromChoices() {
        return randomFromArray(RandomRepo.listOfWords);
    }

    public static String randomFromArray(String[] array) {
        return array[randomIndex(array.length)];
    }

    public static Integer randomIndex(Integer max) {
        return RANDOM.nextInt(max);
    }

    public static boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public static String generateRandomString(Integer length){
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static Map<String, GistFile> generateRandomFileMap(int quantity) {
        Map files = new HashMap<String, GistFile>();

        for (int i = 0; i < quantity; i++) {
            String content = RandomUtils.randomFromChoices();
            String fileName = RandomUtils.randomFromChoices() + RandomUtils.randomIndex(99999) + "."
                    + RandomUtils.randomFromChoices(RandomRepo.listOffFileExtension);
            GistFile gistFile = new GistFile(content, fileName);
            files.put(fileName, gistFile);
        }
        return files;
    }
}
