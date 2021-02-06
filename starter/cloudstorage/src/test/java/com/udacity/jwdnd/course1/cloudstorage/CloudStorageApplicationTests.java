package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CloudStorageApplicationTests {

    @LocalServerPort
    protected int port;
}
