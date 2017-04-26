package com.amazon.controller;

import com.amazon.pojo.JsonMeta;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.http2.Stream;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hadoop on 4/25/17.
 */
@RestController
public class RequestController {

    @GetMapping("/")
    public String index() {

        //File directory = new File("/Users/ajinkya/Documents/adbms_project/electronics/input/hadoop/pig/");
//        @NotNull
//        File[] files = directory.listFiles();
//
//        for ( File currentFile : files) {
//            if (currentFile.isFile()) {
//                String currentLine;
//                try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
//                    while ((currentLine = reader.readLine()) != null) {
//                        System.out.println(currentLine);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

            File directory = new File("/home/hadoop/Juilee/Project/AmazonReview/pig_files/pig-category");
        File[] files = directory.listFiles();
            Collection<String> asinCollection = new HashSet<>();
            Jedis jedis = new Jedis("localhost");
        for ( File currentFile : files) {
            String currentLine;
            if (currentFile.getName().contains("category")) {
                List<String> toStoreInRedis = new ArrayList<>();

                String category = "";
                try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                    while ((currentLine = reader.readLine()) != null) {
                        String[] row = currentLine.split("\t");
                        toStoreInRedis.add(row[0]);
                        category = row[2];
                        asinCollection.add(row[0]);
                    }
                    String key = "top_10_" + category;
                    jedis.set(key, toStoreInRedis.toString());
                    System.out.println("Key in redis: " + key);
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

            //Now Store asins in data base
            Gson gson = new Gson();
            File metadataFile = new File("/home/hadoop/Juilee/Project/metadata");
            String currentLine;
            try (BufferedReader metadataFileReader = new BufferedReader(new FileReader(metadataFile))) {
                while ((currentLine = metadataFileReader.readLine()) != null) {
                    JsonMeta sampleMetadata = gson.fromJson(currentLine, JsonMeta.class);
                    if (StringUtils.isNotBlank(sampleMetadata.getAsin())) {
                        if (asinCollection.contains(sampleMetadata.getAsin()))
                        {
                            jedis.set(sampleMetadata.getAsin(), gson.toJson(sampleMetadata));
                            System.out.println("Added asin to db: " + sampleMetadata.getAsin());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            jedis.close();
        return "Hello from Spring Boot";
        }

    @CrossOrigin
    @RequestMapping(value = "/search_category", method = RequestMethod.GET)
    public List<JsonMeta> findTopTenProducts(@RequestParam(name = "searchParam", required = false) String searchParam ) {
        Gson gson = new Gson();
        if (StringUtils.isNotBlank(searchParam)){
            List<JsonMeta> results = new ArrayList<>();
            Jedis jedis = new Jedis("localhost");
            String key = "top_10_" + searchParam;
            if (StringUtils.isNotBlank(jedis.get(key))) {
                String result = jedis.get(key);
                result = result.replaceAll("\\[","").replaceAll("\\]","");
                String[] strArray = result.split(",");
                for (String s : strArray) {
                    String metadataInString = jedis.get(s.trim());
                    JsonMeta fromDB = gson.fromJson(metadataInString, JsonMeta.class);
                    results.add(fromDB);
                }
                return results;
            }
        }
        return null;
    }
}
