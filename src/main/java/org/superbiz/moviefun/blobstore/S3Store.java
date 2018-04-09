package org.superbiz.moviefun.blobstore;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.tika.Tika;
import org.apache.tika.io.IOUtils;

import java.io.ByteArrayInputStream;
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
        s3Client.putObject(s3BucketName, blob.name, blob.inputStream, new ObjectMetadata());
    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        if (s3Client.doesObjectExist(s3BucketName, name)){
            S3Object s3Object = s3Client.getObject(s3BucketName, name);
            byte[] bytes = IOUtils.toByteArray(s3Object.getObjectContent());
            return Optional.of(new Blob(
                    name,
                    new ByteArrayInputStream(bytes),
                    new Tika().detect(s3Object.getObjectContent())));
        }else{
            return Optional.empty();
        }


    }

    @Override
    public void deleteAll() {

    }
}
