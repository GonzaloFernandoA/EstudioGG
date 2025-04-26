package com.whatsup.bot.repository;

import java.io.IOException;
import java.util.List;

public interface IS3Repository<T> {
    void save(String key, T entity) throws IOException;
    T findByKey(String key, Class<T> clazz) throws IOException;
    void delete(String key);
    List<String> listAll(String prefix);
    List<T> listAndReadAllAsObjects(String prefix, Class<T> clazz) ;
}