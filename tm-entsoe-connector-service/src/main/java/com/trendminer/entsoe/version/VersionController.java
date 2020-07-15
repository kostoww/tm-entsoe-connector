package com.trendminer.entsoe.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/version")
    public Version getVersion() {
        return new Version("5.5.5");
    }
}
