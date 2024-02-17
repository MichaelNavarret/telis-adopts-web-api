package com.api.telisadoptproyect.library.util;

import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.api.telisadoptproyect.library.validation.EnumValidation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailStructureUtils {
    public enum Type{
        TOKEN,
        RESET,
    }

    public static EmailStructure buildEmailStructure(Type emailType, Map<String, String> params) {
        if (params.isEmpty()) throw new BadRequestException("Email Params can not be empty");
        if (!EnumValidation.validateEnum(Type.class, emailType.name())) throw new BadRequestException("Email Type is not valid");

        if(emailType == Type.TOKEN){
            return buildTokenEmail(params);
        }
        if(emailType == Type.RESET){
            return buildResetPassEmail(params);
        }
        return null;
    }

    private static EmailStructure buildTokenEmail(Map<String, String> params) {
        final String email2FASubjectTitle = "2FA Code";
        final String title2FAMessage = "Authentication Code Request";

        final int otpCode = new GeneratorTool().OTP();

        EmailStructure tokenEmail = new EmailStructure();

        tokenEmail.setEmailSubjectTitle(email2FASubjectTitle);
        tokenEmail.setTittleMessage(title2FAMessage);
        tokenEmail.setOwnerName(Strings.EMPTY);
        tokenEmail.setSender(params.get("sender"));
        tokenEmail.setAddressee(params.get("receiver"));
        tokenEmail.setMessageType(Type.TOKEN.name());
        tokenEmail.setBodyParam(String.valueOf(otpCode));

        return tokenEmail;
    }

    private static EmailStructure buildResetPassEmail(final Map<String, String> params) {
        final String resetPasswordSubjectTitle = "Reset Password Email";
        final String titlePasswordResetMessage = "Reset Password Request";

        EmailStructure resetPassEmail = new EmailStructure();

        resetPassEmail.setEmailSubjectTitle(resetPasswordSubjectTitle);
        resetPassEmail.setTittleMessage(titlePasswordResetMessage);
        resetPassEmail.setSender(params.get("sender"));
        resetPassEmail.setAddressee(params.get("addressee"));
        resetPassEmail.setMessageType(Type.RESET.name());
        resetPassEmail.setBodyParam(generateResetPasswordUrl(params.get("baseUrl"), params.get("token")));

        return resetPassEmail;
    }

    private static String generateResetPasswordUrl(final String appBaseUrl, final String token){
        return appBaseUrl + "/reset-password?token=" + token;
    }
}
