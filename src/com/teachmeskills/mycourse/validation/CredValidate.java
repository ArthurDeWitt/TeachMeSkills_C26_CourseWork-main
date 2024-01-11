package com.teachmeskills.mycourse.validation;

import com.teachmeskills.mycourse.coder.Coder;
import com.teachmeskills.mycourse.exception.CheckLoginException;
import com.teachmeskills.mycourse.exception.CheckPasswordException;
import com.teachmeskills.mycourse.storage.StorageMok;
import com.teachmeskills.mycourse.util.Const;

public class CredValidate {
    public static void passwordValidation(String password) throws CheckPasswordException {
        StorageMok storageMok = new StorageMok();
        if (!password.matches(".*\\d{1,}.*")) {
            throw new CheckPasswordException("wrong password or login");
        }
        if (password.contains(" ")) {
            throw new CheckPasswordException("wrong password or login");
        }
        if (!(password.equals(Coder.deCoder(storageMok.getPassword())))){
            throw new CheckPasswordException("wrong password or login");
        }
    }
    public static void loginValidation(String login)throws CheckLoginException {
        StorageMok storageMok = new StorageMok();
        if (login.contains(" ")) {
            throw new CheckLoginException("wrong password or login");
        }
        if (!(login.equals(Coder.deCoder(storageMok.getLogin())))) {
            throw new CheckLoginException("wrong password or login");
        }
    }
}
