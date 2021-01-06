package org.fgbokg.basic.service;

import org.fgbokg.basic.exception.CustomException;

public interface IVerificationService {

    /**
     *
     * @param phone
     */
    void sendMobileCode(String phone) throws CustomException;
}
