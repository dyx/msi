package com.lhd.msi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author lhd
 */
@ServletComponentScan
@SpringBootApplication
public class MsiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsiApplication.class, args);
    }
}
