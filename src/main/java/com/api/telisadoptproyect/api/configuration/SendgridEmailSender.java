package com.api.telisadoptproyect.api.configuration;

import com.api.telisadoptproyect.library.entity.EmailType;
import com.api.telisadoptproyect.library.util.EmailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Component
public class SendgridEmailSender {
    private static final String COM_LOGO_PNG_URL = "https://i.ibb.co/0jZQYQg/com-logo.png";
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
}
