package server.models;

import server.models.enums.PasswordResetsStatusesEnum;
import java.time.Instant;

/**
 * password resets model
 *
 * @author Ntwari Clarance Liberiste
 */
public class PasswordResets {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Otp.
     */
    private int otp;
    /**
     * The Expiration date.
     */
    private String expiration_date;
    /**
     * The Status.
     */
    private PasswordResetsStatusesEnum status;
    /**
     * The Created at.
     */
    private String created_at;


    /**
     * The Update at.
     */
    private String updated_at;


    /**
     * Instantiates a new Password reset model.
     *
     * @param id              the id
     * @param email           the email
     * @param otp             the otp (One Time Passcode)
     * @param expiration_date the expiration date
     * @param status          the status [ACTIVE,PENDING,EXPIRED]
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets(int id, String email, int otp, String expiration_date, PasswordResetsStatusesEnum status) {
        this.id = id;
        this.email = email;
        this.otp = otp;
        this.expiration_date = expiration_date;
        this.status = status;
        this.created_at = Instant.now().toString();
        this.updated_at = Instant.now().toString();
    }

    /**
     * Instantiates a new Password reset model.
     *
     * @param email           the email
     * @param otp             the otp (One Time Passcode)
     * @param expiration_date the expiration date
     * @param status          the status [ACTIVE,PENDING,EXPIRED]
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets(String email, int otp, String expiration_date, PasswordResetsStatusesEnum status) {
        this.email = email;
        this.otp = otp;
        this.expiration_date = expiration_date;
        this.status = status;
        this.created_at = Instant.now().toString();
        this.updated_at = Instant.now().toString();
    }

    /**
     * Instantiates a new Password reset model.
     *
     * @param email           the email
     * @param otp             the otp (One Time Passcode)
     * @param expiration_date the expiration date
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets(String email, int otp, String expiration_date) {
        this.email = email;
        this.otp = otp;
        this.expiration_date = expiration_date;
        this.status = PasswordResetsStatusesEnum.PENDING;
        this.created_at = Instant.now().toString();
        this.updated_at = Instant.now().toString();
    }

    /**
     * Instantiates a new Password reset model.
     *
     * @param email           the email
     * @param expiration_date the expiration date
     * @author Ntwari Clarance Liberiste
     */
    public PasswordResets(String email, String expiration_date) {
        int min = 100000;
        int max = 999999;

        this.email = email;
        this.otp = (int)(Math.random() * (max - min + 1) + min) ;
        this.expiration_date = expiration_date;
        this.status = PasswordResetsStatusesEnum.PENDING;
        this.created_at = Instant.now().toString();
        this.updated_at = Instant.now().toString();
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


    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public String getUpdated_at() {
        return updated_at;
    }

    /**
     * Sets updated at.
     *
     * @param updated_at the updated at
     */
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }



}

