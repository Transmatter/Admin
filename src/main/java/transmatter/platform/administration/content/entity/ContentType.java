package transmatter.platform.administration.content.entity;

public enum ContentType {
    NA("ไม่ทราบชนิดข่าว"),LOCAL_CONTENT("ข่าวในประเทศ"),INTER_CONTENT("ข่าวต่างประเทศ");
    String thaiName;
    ContentType(String thaiName){
        this.thaiName = thaiName;
    }

    public String getThaiName() {
        return thaiName;
    }
}
