package transmatter.platform.administration.content.entity.constant;

public enum ContentStatus {
    NA("ไม่"),APPROVE("รองรับ"),NOT_APPROVE("ไม่รองรับ");

    String thaiName;
    ContentStatus(String thaiName){
        this.thaiName = thaiName;
    }

    public String getThaiName() {
        return thaiName;
    }
}
