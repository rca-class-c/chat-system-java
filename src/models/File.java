package models;

import java.util.Date;

public class File {
    private int id;
    private String fileLocalPath;
    private String url;
    private String fileName;
    private String fileType;
    private int fileSize;
    private String fileSizeType;
    private int senderId;
    private Date createdAt;
    private FileStatusEnum status;

    public File() { }

    public File(String fileLocalPath, String fileName, String fileType, int fileSize, String fileSizeType, int senderId) {
        this.fileLocalPath = fileLocalPath;
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

    public String getFileLocalPath() { return fileLocalPath; }

    public void setFileLocalPath(String fileLocalPath) { this.fileLocalPath = fileLocalPath; }

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