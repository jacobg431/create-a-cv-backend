package com.cvbackend.springboot.maven;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BackendController {
    private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

    @GetMapping("/hello")
    @ResponseBody
    public byte[] generatePdfGet() {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            String html = "<html><body><h1>Hello World</h1></body></html>";
            builder.withHtmlContent(html, "");
            builder.toStream(os);
            builder.run();
            
            byte[] pdf = os.toByteArray();
            return Base64.getEncoder().encode(pdf);
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    @PostMapping("/generate-pdf")
    @ResponseBody
    public byte[] generatePdf(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "email", required = true) String email,
            @RequestParam(name = "phone", required = true) String phone,
            @RequestParam(name = "address", required = true) String address,
            @RequestParam(name = "education", required = true) String education,
            @RequestParam(name = "experience", required = true) String experience,
            @RequestParam(name = "skills", required = true) String skills
    ) {
        logger.info("Generate PDF endpoint hit");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            String htmlToConvert = "<html><body><h1>Curriculum Vitae</h1>" +
                                    "<p><strong>Name:</strong> " + name + "</p>" +
                                    "<p><strong>Email:</strong> " + email + "</p>" +
                                    "<p><strong>Phone:</strong> " + phone + "</p>" +
                                    "<p><strong>Address:</strong> " + address + "</p>" +    
                                    "<h2>Education</h2>" +
                                    "<p>" + education + "</p>" +
                                    "<h2>Experience</h2>" +
                                    "<p>" + experience + "</p>" +
                                    "<h2>Skills</h2>" +
                                    "<p>" + skills + "</p>" + 
                                    "</body></html>";

            pdfBuilder.withHtmlContent(htmlToConvert, "");
            pdfBuilder.toStream(outputStream);
            pdfBuilder.run();
            
            byte[] pdf = outputStream.toByteArray();
            return Base64.getEncoder().encode(pdf);
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}