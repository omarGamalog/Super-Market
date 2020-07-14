package com.example.supermrcket.Fruits;


public class ImageUploadInfo {
    public String imageName;
    public String imageURL;
    public String imageDes;
    public ImageUploadInfo(String name, String url , String Des ) {

        imageName = name;
        imageURL= url;
        imageDes=Des;
    }

    public ImageUploadInfo() {

    }

    public String getImageDes() {
        return imageDes;
    }

    public void setImageDes(String imageDes) {
        this.imageDes = imageDes;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}



