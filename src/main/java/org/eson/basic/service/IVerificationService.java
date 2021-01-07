package org.eson.basic.service;

import org.eson.basic.exception.CustomException;

public interface IVerificationService {

    /**
     *
     * @param phone
     */
    void sendMobileCode(String phone) throws CustomException;
}
