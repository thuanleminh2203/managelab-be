package com.example.demo.serviceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.EmailDTO;
import com.example.demo.service.EmailService;

@Service
public class EmailServicerImpl implements EmailService {

	@Autowired
	private JavaMailSenderImpl emailSender;

	@Override
	public void sendMail(EmailDTO mail, HttpServletRequest request) throws MessagingException, IOException {
		emailSender.setUsername(mail.getMyEmail());
		emailSender.setPassword(mail.getPassword());
		// no attach
		if (mail.getMultipartFiles().length == 0) {
			sendMailSimple(mail);
		} else {
			sendMailAttach(mail, request);
		}

	}

	private void sendMailSimple(EmailDTO mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		String[] toLst = mail.getToListEmail().stream().toArray(String[]::new);
		message.setTo(toLst);
		message.setFrom(mail.getMyEmail());
		emailSender.send(message);
	}

	private void sendMailAttach(EmailDTO mail, HttpServletRequest request) throws MessagingException, IOException {
		MimeMessage message = this.emailSender.createMimeMessage();
		boolean multipart = true;

			MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
			String[] toLst = mail.getToListEmail().stream().toArray(String[]::new);
			helper.setTo(toLst);
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getContent());
			helper.setFrom(mail.getMyEmail());
//			String uploadRootPath = request.getServletContext().getRealPath("upload");
//			System.out.println("uploadRootPath=" + uploadRootPath);
			String path = System.getProperty("user.dir");
//			System.out.println("======" + path + "/upload" );
			File uploadRootDir = new File(path + "/upload");
			// Tạo thư mục gốc upload nếu nó không tồn tại.
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			for (MultipartFile fileData : mail.getMultipartFiles()) {
				String name = fileData.getOriginalFilename();
				if (name != null && name.length() > 0) {
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					FileSystemResource file = new FileSystemResource(new File(uploadRootDir.getAbsolutePath() + File.separator + name));
					helper.addAttachment(name, file);
				}

			}

//			String path1 = "/home/tran/Downloads/test.txt";
//			String path2 = "/home/tran/Downloads/readme.zip";

			// Attachment 1
//			FileSystemResource file1 = new FileSystemResource(new File(path1));
//			helper.addAttachment("Txt file", file1);

			// Attachment 2
//			FileSystemResource file2 = new FileSystemResource(new File(path2));
//			helper.addAttachment("Readme", file2);

			emailSender.send(message);

		

	}

}
