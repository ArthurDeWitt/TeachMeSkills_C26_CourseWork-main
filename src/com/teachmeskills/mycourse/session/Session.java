package com.teachmeskills.mycourse.session;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public final class Session {
    private String accessToken;
    private Date expDate;

    public Session() {
        setAccessToken();
        setExpDate();
    }

    public boolean isSessionAlive() {

        if (this.accessToken.length() == 16 || this.expDate.after(new Date())) {
            return true;
        }
        return false;
    }

    private void setAccessToken() {
        String symbols = "qwertyuiopasdfghjklzxcvbnm1234567890";
        this.accessToken = new Random().ints(16, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 3);
        this.expDate = calendar.getTime();
    }
}
