package entity;

/**
 * @author 余俊锋
 * @date 2020/11/21 17:29
 * @Description
 */
public enum StatusCode2 {
    success("200"),
    failed("404"),
    error("400");

    private  String value;

    StatusCode2(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
