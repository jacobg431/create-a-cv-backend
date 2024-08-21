package com.cvbackend.springboot.maven.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cvbackend.springboot.maven.api.models.GenerateCvPayload;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CvController {
    private static final Logger logger = LoggerFactory.getLogger(CvController.class);

    // Sample endpoint to generate a PDF - Just for sanity testing
    @GetMapping("/hello")
    @ResponseBody
    public byte[] helloWorld() {
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
    public byte[] generatePdf(@RequestBody GenerateCvPayload payload) {
        logger.info("Generate PDF endpoint hit");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            String htmlToConvert = "<html><body><h1>Curriculum Vitae</h1>" +
                                    "<h2>Personalia</h2>" +
                                    "<p>First name: " + payload.getPersonaliaSegment().getFirstName() + "</p>" +
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