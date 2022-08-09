package transmatter.platform.administration.content.entity.constant;

public enum ImageStatus {
    NA("ไม่ทราบสถานะ"),COMPLETE("สมบรูณ์"),INCOMPLETE("ไม่สมบูรณ์"),EMPTY("ว่าง");
    String thaiName;
    ImageStatus(String thaiName){
        this.thaiName = thaiName;
    }
    String getThaiName() {
        return thaiName;
    }
}
