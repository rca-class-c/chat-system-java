package models;

import java.util.Date;

public class File {
    public int id;
    public String url;
    public String fileName;
    public String fileType;
    public int fileSize;
    public String fileSizeType;
    public int senderId;
    public Date createdAt;
    public FileStatusEnum status;

    public File() {

    }
    public File(String fileName, String fileType, int fileSize, String fileSizeType, int senderId) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileSizeType = fileSizeType;
        this.senderId = senderId;
        this.status = FileStatusEnum.PENDING;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSizeType(String fileSizeType) {
        this.fileSizeType = fileSizeType;
    }

    public String getFileSizeType() {
        return fileSizeType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public FileStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FileStatusEnum status) {
        this.status = status;
    }
}
