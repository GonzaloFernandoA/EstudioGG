/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.config.S3Config;
import com.whatsup.bot.service.EventService;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.ResponseTransformer;

@Repository
public class S3RepositoryImpl<T> implements IS3Repository<T> {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    S3Config config;

    private final S3Client s3Client;
    private final ObjectMapper objectMapper;
    private final String bucketName;

    public S3RepositoryImpl(S3Client s3Client, ObjectMapper objectMapper) {
        this.s3Client = s3Client;
        this.objectMapper = objectMapper;
        this.bucketName = "";
    }

    @Override
    public void save(String key, T entity) {

        try {
            String json = objectMapper.writeValueAsString(entity);
            InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
            PutObjectRequest putRequest = PutObjectRequest.builder()
                    .bucket(getBucketName())
                    .key(key+ ".json")
                    .build();
            s3Client.putObject(putRequest, RequestBody.fromInputStream(inputStream, json.length()));
            logger.info("Saved [" + key + ".json ]");
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }
    }

    private InputStream getS3Stream(String key) {
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(getBucketName())
                .key(key)
                .build();

        return s3Client.getObject(getRequest);
    }

    @Override
    public T findByKey(String key, Class<T> clazz) {
        try {
            logger.info("Buscando... [" + key + "]");
            return objectMapper.readValue(getS3Stream(key), clazz);
        } catch (NoSuchKeyException ex) {
            logger.info("Entidad no encontrada.[" + key + "]");
            return null;
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage());
            return null;
        } finally {
            logger.info("FindByKey fin [" + key + "]");
        }

    }

    @Override
    public void delete(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(getBucketName())
                .key(key)
                .build();
        s3Client.deleteObject(deleteRequest);
    }

    @Override
    public List<String> listAll(String prefix) {
        ListObjectsRequest listRequest = ListObjectsRequest.builder()
                .bucket(getBucketName())
                .prefix(prefix)
                .build();
        return s3Client.listObjects(listRequest)
                .contents()
                .stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    private String getBucketName() {
        return config.getBucket();
    }

    @Override
    public List<T> listAndReadAllAsObjects(String prefix, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson para convertir JSON a objetos

        ListObjectsRequest listRequest = ListObjectsRequest.builder()
                .bucket(getBucketName())
                .prefix(prefix)
                .build();

        List<S3Object> objects = s3Client.listObjects(listRequest).contents();
        List<T> resultList = new ArrayList<>();

        for (S3Object object : objects) {
            GetObjectRequest getRequest = GetObjectRequest.builder()
                    .bucket(getBucketName())
                    .key(object.key())
                    .build();

            // Obtiene el contenido del archivo como JSON
            String jsonContent = s3Client.getObject(getRequest, ResponseTransformer.toBytes()).asUtf8String();

            try {
                  resultList.add( objectMapper.readValue(jsonContent, clazz));
                } 
                catch (JsonProcessingException e) 
                {
                  logger.info("JSON inválido, intentando reparar...");
                  if (!jsonContent.trim().endsWith("}")) {
                      jsonContent = jsonContent.trim() + "}";
                      try {
                          resultList.add( objectMapper.readValue(jsonContent, clazz));
                      } catch (JsonProcessingException ex) {
                          logger.error("No se pudo reparar el JSON");
                      }
                  }
                }
        }
        return resultList;
    }
}
