package com.api.telisadoptproyect.api.configuration;

import com.api.telisadoptproyect.library.entity.EmailType;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.util.EmailStructure;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
public class SendgridEmailSender {
    private static final String COM_LOGO_PNG_URL = "https://i.ibb.co/0jZQYQg/com-logo.png";
    private final List<String> ALLOWED_FILE_EXTENSIONS = List.of("pdf", "png", "jpg");
    @Autowired
    private PropertiesConfig propertiesConfig;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public SendgridEmailSender(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void sendHtmlEmail(EmailStructure emailStructure){
        if (propertiesConfig.isSendgridTestModeActive()) {
            return;
        }
        final String htmlBody = buildSendgridEmailTemplate(emailStructure);

        Mail htmlMail = new Mail();
        htmlMail.setFrom(new Email(propertiesConfig.getSendgridSenderEmail()));
        htmlMail.setSubject(emailStructure.getEmailSubjectTitle());

        Personalization personalization = new Personalization();
        personalization.addTo(new Email(emailStructure.getAddressee()));
        htmlMail.addContent(new Content("text/html", htmlBody));
        personalization.addSubstitution("text", "test");
        personalization.addCustomArg("ar", "ar");

        htmlMail.addPersonalization(personalization);

        SendGrid client = new SendGrid(propertiesConfig.getSendgridKey());
        Request mailRequest = new Request();

        mailRequest.setMethod(Method.POST);
        mailRequest.setEndpoint("mail/send");

        try{
            mailRequest.setBody(htmlMail.build());
            Response response = client.api(mailRequest);
        }catch(Exception e){
            throw new BadRequestException("Unable to send email");
        }
    }

    private String buildSendgridEmailTemplate(final EmailStructure emailStructure){
        Context context = new Context();
        context.setVariable("emailSubjectTitle", emailStructure.getEmailSubjectTitle());
        context.setVariable("logoUrl", COM_LOGO_PNG_URL);
        context.setVariable("titleMessage", emailStructure.getTittleMessage());
        context.setVariable("sender", emailStructure.getSender());
        context.setVariable("addressee", emailStructure.getAddressee());
        context.setVariable("bodyParam", emailStructure.getBodyParam());

        return templateEngine.process(EmailType.HELPER.getValueByKey(emailStructure.getMessageType()), context);
    }

    public void sendEmail(List<String> recipients, String subject, String bodyText, Object document, String documentName) throws IOException {
        if(recipients == null || recipients.isEmpty()) throw new BadRequestException("The email recipients are required");
        if(StringUtils.isEmpty(subject)) throw new BadRequestException("The email subject is required");
        if(StringUtils.isEmpty(documentName)) throw new BadRequestException("The document name is required");
        if(document != null){
            if(document instanceof File){
                File attachmentFile = (File) document;
                String extension = attachmentFile.getName().substring(attachmentFile.getName().lastIndexOf(".") + 1);
                if(!ALLOWED_FILE_EXTENSIONS.contains(extension)){
                    throw new BadRequestException("The file extension "+ extension +" is not allowed");
                }
            }
        }

        SendGrid sendGrid = new SendGrid(propertiesConfig.getSendgridKey());
        Email from = new Email(propertiesConfig.getSendgridSenderEmail());

        for (String recipient : recipients) {
            Email to = new Email(recipient);
            Content content = new Content("text/html", bodyText);
            Mail email = new Mail(from, subject, to, content);

            if (document != null) {
                if (document instanceof File) {
                    File attachmentFile = (File) document;
                    byte[] fileBytes = Files.readAllBytes(attachmentFile.toPath());
                    String base64Content = Base64.encodeBase64String(fileBytes);

                    Attachments attachments = new Attachments();
                    attachments.setContent(base64Content);
                    attachments.setType("application/pdf");
                    attachments.setFilename(documentName);

                    email.addAttachments(attachments);
                } else if (document instanceof String) {
                    String base64Content = (String) document;

                    Attachments attachments = new Attachments();
                    attachments.setContent(base64Content);
                    attachments.setType("application/pdf");
                    attachments.setFilename(documentName);

                    email.addAttachments(attachments);
                }
            }

            Request request = new Request();
            try{
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(email.build());
                if(propertiesConfig.isSendgridTestModeActive() == false){
                    sendGrid.api(request);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
