package ru.urvanov.javaexamples.jackson.polymorphicdeserialization;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void jsonSkeletonSerializationTest() throws IOException {
        Skeleton skeleton = new Skeleton();
        skeleton.setHealth(100);
        skeleton.setBones(99);
        ObjectMapper objectMapper = new ObjectMapper();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(byteArrayOutputStream, skeleton);

            String expectedJson = new String(
                    Files.readAllBytes(Paths
                            .get("src/test/resources/ru/urvanov/javaexamples/jackson/polymorphicdeserialization/skeleton.json")),
                    "utf-8");

            assertJsonEquals(expectedJson, byteArrayOutputStream.toString("utf-8"));
        }
    }

    @Test
    public void jsonSkeletonDeserializationTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        String jsonString = new String(Files.readAllBytes(Paths.get("src/test/resources/ru/urvanov/javaexamples/jackson/polymorphicdeserialization/skeleton.json")), "utf-8");
        
        try (InputStream inputStream = new ByteArrayInputStream(jsonString.getBytes("utf-8"))) {
            Object object = objectMapper.readValue(inputStream, Monster.class);
            assertTrue(object instanceof Skeleton);
            Skeleton skeleton = (Skeleton) object;
            assertEquals(100, skeleton.getHealth());
            assertEquals(99, skeleton.getBones());
        }
        
    }

    @Test
    public void jsonVampireSerializationTest() throws IOException {
        Vampire vampire = new Vampire();
        vampire.setHealth(50);
        vampire.setBloodCollected(999);
        ObjectMapper objectMapper = new ObjectMapper();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(byteArrayOutputStream, vampire);

            String expectedJson = new String(
                    Files.readAllBytes(Paths
                            .get("src/test/resources/ru/urvanov/javaexamples/jackson/polymorphicdeserialization/vampire.json")),
                    "utf-8");

            assertJsonEquals(expectedJson, byteArrayOutputStream.toString("utf-8"));
        }
    }

    @Test
    public void jsonVampireDeserializationTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        
        String jsonString = new String(Files.readAllBytes(Paths.get("src/test/resources/ru/urvanov/javaexamples/jackson/polymorphicdeserialization/vampire.json")), "utf-8");
        
        try (InputStream inputStream = new ByteArrayInputStream(jsonString.getBytes("utf-8"))) {
            Object object = objectMapper.readValue(inputStream, Monster.class);
            assertTrue(object instanceof Vampire);
            Vampire vampire = (Vampire) object;
            assertEquals(50, vampire.getHealth());
            assertEquals(999, vampire.getBloodCollected());
        }
        
    }

}
