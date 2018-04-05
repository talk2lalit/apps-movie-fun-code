package org.superbiz.moviefun.blobstore;

import com.amazonaws.services.s3.AmazonS3Client;

import java.io.IOException;
import java.util.Optional;

public class S3Store implements BlobStore {

    private AmazonS3Client s3Client;
    private String s3BucketName;

    public S3Store(AmazonS3Client s3Client, String s3BucketName) {
        this.s3Client = s3Client;
        this.s3BucketName = s3BucketName;
    }

    @Override
    public void put(Blob blob) throws IOException {

    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
