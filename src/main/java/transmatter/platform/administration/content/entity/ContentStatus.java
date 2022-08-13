package transmatter.platform.administration.content.entity;

public enum ContentStatus {
    NA("ไม่"),COMPLETE("รองรับ"),INCOMPLETE("ไม่รองรับ");

    String thaiName;
    ContentStatus(String thaiName){
        this.thaiName = thaiName;
    }

    public String getThaiName() {
        return thaiName;
    }
}
