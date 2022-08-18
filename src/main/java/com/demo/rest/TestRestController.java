package com.demo.rest;

import com.demo.jdbc.dao.ChildDao;
import com.demo.jdbc.model.Child;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TestRestController {

    private static final Logger logger = LoggerFactory.getLogger(TestRestController.class);

    @GetMapping("/hello")
    public HashMap hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        HashMap response = new HashMap<String, String>();
        response.put("string", String.format("Hello %s!", name));
        return response;
    }

    @GetMapping("/children")
    public HashMap getAllChildren() throws InterruptedException {
        logger.info("Call to /children");
        HashMap response = new HashMap<String, String>();
        Optional<List<Child>> result = new ChildDao().get();
        if (!result.isEmpty()) {
            logger.info("Successfully retrieved children.");
            response.put("children", result.get());
        } else {
            logger.warn("Unable to retrieve children.");
        }
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        return response;
    }

    @GetMapping("/children/{id}")
    public HashMap getChild(@PathVariable long id) {
        logger.info("Call to /children/{%d}", id);
        HashMap response = new HashMap<String, String>();
        List<Child> resultList = new ArrayList<>();
        Optional<Child> result = new ChildDao().get(Long.toString(id));
        if (!result.isEmpty()) {
            logger.info("Successfully retrieved child with Id %d.", id);
            resultList.add(result.get());
            response.put("children", resultList);
        } else {
            logger.warn("Unable to retrieve child with Id %d.", id);
        }
        return response;
    }

    @GetMapping("/books")
    public HashMap getBooks() throws SQLException {
        logger.info("Call to /books");
        HashMap response = new HashMap<String, String>();
        List<String> resultList = new ArrayList<>();
        ResultSet results = new ChildDao().testUuid();
        while (results.next()) {
            Blob idBlob = results.getBlob("uuid");
            UUID uuid = UUID.nameUUIDFromBytes(idBlob.getBytes(1L, (int) idBlob.length()));
            resultList.add(uuid.toString());
            resultList.add(UUID.nameUUIDFromBytes(results.getBytes("uuid")).toString());
            resultList.add(UUID.fromString("3d4bd242-19db-11ed-a21b-0a0027000013").toString());
        }
        response.put("uuid", resultList);
        return response;
    }
}
