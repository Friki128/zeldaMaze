package net.esliceu.maze.Utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class hashCreator {
    public static String hash(String text){
        return Arrays.toString(DigestUtils.md5Digest(text.getBytes(StandardCharsets.UTF_8)));
    }
}
