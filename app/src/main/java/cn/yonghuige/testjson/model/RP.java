package cn.yonghuige.testjson.model;

/**
 * Created by gyh on 2017/3/19.
 */

public class RP {
    private String id;
    private String room_num;
    private String room_division;
    private String phone;
    private String others;

    public RP(String id, String room_num, String room_division, String phone, String others) {
        setId(id);
        setRoom_num(room_num);
        setRoom_division(room_division);
        setPhone(phone);
        setOthers(others);
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_num() {
        return room_num;
    }

    public void setRoom_num(String room_num) {
        this.room_num = room_num;
    }

    public String getRoom_division() {
        return room_division;
    }

    public void setRoom_division(String room_division) {
        this.room_division = room_division;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
