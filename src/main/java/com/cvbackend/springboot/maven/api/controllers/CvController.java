package com.cvbackend.springboot.maven.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
public class CvController {
    private static final Logger logger = LoggerFactory.getLogger(CvController.class);
    
    @Autowired
    private TemplateEngine templateEngine;

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
            // Inject data into HTML template
            Context context = new Context();
            context.setVariable("personaliaSegment", payload.getPersonaliaSegment());
            context.setVariable("educationSegment", payload.getEducationSegment());
            context.setVariable("experienceSegment", payload.getExperienceSegment());
            context.setVariable("skillsSegment", payload.getSkillsSegment());
            context.setVariable("certificationsSegment", payload.getCertificationsSegment());
            context.setVariable("coursesSegment", payload.getCoursesSegment());
    
            String processedHtml = templateEngine.process("cv", context);
    
            // Convert generated HTML to PDF
            PdfRendererBuilder pdfBuilder = new PdfRendererBuilder();
            pdfBuilder.withHtmlContent(processedHtml, "/");
            pdfBuilder.toStream(outputStream);
            pdfBuilder.run();
            
            // Return PDF as Base64 encoded string
            byte[] pdf = outputStream.toByteArray();
            return Base64.getEncoder().encode(pdf);
        } catch (Exception e) {
            logger.error("Error generating PDF", e);
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}