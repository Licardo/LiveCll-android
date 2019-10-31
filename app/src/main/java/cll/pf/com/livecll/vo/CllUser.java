package cll.pf.com.livecll.vo;

import cn.bmob.v3.BmobUser;

public class CllUser extends BmobUser {

    public CllUser() {
        this.setTableName("_User");
    }

    private String nickName;
    private String gender;
    private String avatar;
    private String address;
    private Integer age;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
