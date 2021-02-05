package server.services;

import server.models.PasswordResets;
import server.models.enums.PasswordResetsStatusesEnum;

import java.time.Instant;

/**
 * Password resets service
 *
 * @author Ntwari Clarance Liberiste
 */
public class PasswordResetService {
    /**
     * The Id.
     */
    public int id;
    /**
     * The Email.
     */
    public String email;
    /**
     * The Otp.
     */
    public int otp;
    /**
     * The Expiration date.
     */
    public String expiration_date;
    /**
     * The Status.
     */
    public PasswordResetsStatusesEnum status;
    /**
     * The Created at.
     */
    public String created_at;
    /**
     * The Update at.
     */
    public String update_at;


    /**
     * Instantiates a new Password reset service.
     *
     * @param id              the id
     * @param email           the email
     * @param otp             the otp (One Time Passcode)
     * @param expiration_date the expiration date
     * @param status          the status [ACTIVE,PENDING,EXPIRED]
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResetService(int id, String email, int otp, String expiration_date, PasswordResetsStatusesEnum status) {
        this.id = id;
        this.email = email;
        this.otp = otp;
        this.expiration_date = expiration_date;
        this.status = status;
        this.created_at = Instant.now().toString();
        this.update_at = Instant.now().toString();
    }

    /**
     * Instantiates a new Password reset service.
     *
     * @param email           the email
     * @param otp             the otp (One Time Passcode)
     * @param expiration_date the expiration date
     * @param status          the status [ACTIVE,PENDING,EXPIRED]
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResetService(String email, int otp, String expiration_date, PasswordResetsStatusesEnum status) {
        this.email = email;
        this.otp = otp;
        this.expiration_date = expiration_date;
        this.status = status;
        this.created_at = Instant.now().toString();
        this.update_at = Instant.now().toString();
    }

    /**
     * Instantiates a new Password reset service.
     *
     * @param email           the email
     * @param otp             the otp (One Time Passcode)
     * @param expiration_date the expiration date
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResetService(String email, int otp, String expiration_date) {
        this.email = email;
        this.otp = otp;
        this.expiration_date = expiration_date;
        this.status = PasswordResetsStatusesEnum.PENDING;
        this.created_at = Instant.now().toString();
        this.update_at = Instant.now().toString();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    public String getExpiration_date() {
        return expiration_date;
    }

    /**
     * Sets expiration date.
     *
     * @param expiration_date the expiration date
     */
    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public PasswordResetsStatusesEnum getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(PasswordResetsStatusesEnum status) {
        this.status = status;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public String getCreated_at() {
        return created_at;
    }

    /**
     * Sets created at.
     *
     * @param created_at the created at
     */
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    /**
     * Gets update at.
     *
     * @return the update at
     */
    public String getUpdate_at() {
        return update_at;
    }

    /**
     * Sets update at.
     *
     * @param update_at the update at
     */
    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    /**
     * Gets otp.
     *
     * @return the otp
     */
    public int getOtp() {
        return otp;
    }

    /**
     * Sets otp.
     *
     * @param otp the otp
     */
    public void setOtp(int otp) {
        this.otp = otp;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }




}
