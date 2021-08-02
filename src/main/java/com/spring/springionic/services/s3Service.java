package com.spring.springionic.services;

import java.io.File;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class s3Service {
    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    private Logger LOG = LoggerFactory.getLogger(s3Service.class);

    public void uploadFile(String localFilePath){

        try {

            File file = new File(localFilePath);
            LOG.info("Upload starting");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));            
            LOG.info("Upload finished");

        } catch (AmazonServiceException e) {

            LOG.info("AmazonServiceException: "+ e.getErrorMessage());
            LOG.info("Status code: "+ e.getStatusCode());

        } catch (AmazonClientException e) {

            LOG.info("AmazonClientException: "+ e.getMessage());

        }
    }
}
