package com.plateno.mysrpingboot.repositories;

import java.net.URL;

import org.springframework.stereotype.Service;

import freemarker.cache.URLTemplateLoader;

/**
 */
@Service
public class FreemarkerClassTemplateLoader extends URLTemplateLoader {
    protected URL getURL(String name)  {
        return ClassLoaderUtils.getResource(name, getClass());
    }
}
