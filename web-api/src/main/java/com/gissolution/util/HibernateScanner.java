package com.gissolution.util;


import org.hibernate.boot.archive.scan.internal.StandardScanner;
import org.hibernate.boot.archive.scan.spi.ScanEnvironment;
import org.hibernate.boot.archive.scan.spi.ScanOptions;
import org.hibernate.boot.archive.scan.spi.ScanParameters;
import org.hibernate.boot.archive.scan.spi.ScanResult;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class HibernateScanner extends StandardScanner {

    public static final String PACKAGE_NAME = "com.gissolution";

    @Override
    public ScanResult scan(ScanEnvironment environment, ScanOptions options, ScanParameters parameters) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            assert classLoader != null;
            String path = PACKAGE_NAME.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                environment.getNonRootUrls().add(resource);
            }
            return super.scan(environment, options, parameters);
        } catch (IOException e) {
            throw new RuntimeException("Issue Baby");
        }
    }
}
