package com.example.demo.serviceImpl;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.example.demo.service.AmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Service
public class AmsServiceImpl implements AmsService {
    private String awsS3Bucket;
    private AmazonS3 amazonS3;
    private TransferManager xfer_mgr;

    @Autowired
    public AmsServiceImpl(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3Bucket)
    {
        this.amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
        this.awsS3Bucket = awsS3Bucket;
        xfer_mgr = new TransferManager(awsCredentialsProvider);



    }
    @Override
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        String fileName = new Date().getTime() + multipartFile.getOriginalFilename();
        File file = new File(fileName);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3Bucket, fileName, file);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            var o =xfer_mgr.upload(putObjectRequest);
            o.waitForUploadResult();

            var res = this.amazonS3.getUrl(awsS3Bucket,fileName);
            return res.toString();
        }catch (Exception e){
            throw e;
        }finally {
            file.delete();
        }

    }
}
