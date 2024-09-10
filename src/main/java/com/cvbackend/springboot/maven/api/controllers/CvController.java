package com.cvbackend.springboot.maven.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cvbackend.springboot.maven.api.models.GenerateCvPayload;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

@RestController
public class CvController {
    private static final Logger logger = LoggerFactory.getLogger(CvController.class);
    
    @Autowired
    private TemplateEngine templateEngine;
    
    @PostMapping(value = "/generate-pdf", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] generatePdf(@RequestBody GenerateCvPayload payload) {
        logger.info("Received Content Type: {}", MediaType.APPLICATION_JSON_VALUE);

        logger.info("Generate PDF endpoint hit");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Inject data into HTML template
            Context context = new Context();
            context.setVariable("personaliaSegment", payload.getPersonaliaSegment());
            context.setVariable("educationSegment", payload.getEducationSegment());
            context.setVariable("experienceSegment", payload.getExperienceSegment());
            context.setVariable("skillsSegment", payload.getSkillsSegment());
            context.setVariable("certificationsSegment", payload.getCertificationsSegment());
            context.setVariable("coursesSegment", payload.getCoursesSegment());
    
            String generatedHtml = templateEngine.process("cv", context);
            logger.info("Generated HTML: " + generatedHtml);
    
            // Convert generated HTML to PDF
            PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            pdfBuilder.withHtmlContent(generatedHtml, "/");
            pdfBuilder.toStream(outputStream);
            pdfBuilder.run();
            
            // Return PDF as Base64 encoded string
            byte[] pdf = outputStream.toByteArray();
            byte[] base64Content = Base64.getEncoder().encode(pdf);
            logger.info("Base64 encoded PDF: " + new String(base64Content));
            return base64Content;
        } catch (Exception e) {
            logger.error("Error generating PDF", e);
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}