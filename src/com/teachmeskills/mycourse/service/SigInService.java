package com.teachmeskills.mycourse.service;

import com.teachmeskills.mycourse.coder.Coder;
import com.teachmeskills.mycourse.exception.CheckLoginException;
import com.teachmeskills.mycourse.exception.CheckPasswordException;
import com.teachmeskills.mycourse.logger.Logger;
import com.teachmeskills.mycourse.session.Session;
import com.teachmeskills.mycourse.validation.CredValidate;

import java.util.Date;

public class SigInService {
    public static Session sigIn (String login, String password) {
        Logger.logExecutionInfo(new Date(),"start sig in\n");

        try {
            CredValidate.loginValidation(Coder.deCoder(login));
            CredValidate.passwordValidation(Coder.deCoder(password));

            Logger.logExecutionInfo(new Date(),"success sig in\n" );

        } catch (CheckLoginException | CheckPasswordException e) {
            Logger.LogErrorInfo(new Date(), e.getMessage(), e);
        }

        Logger.logExecutionInfo(new Date(),"end sig in\n");

        return new Session();
    }

}
