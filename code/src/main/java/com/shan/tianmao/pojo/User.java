package com.shan.tianmao.pojo;

public class User {
    private int id;
    private String password;
    private String name;
    private String salt; //加密用的
   // private String anonymousName; //匿名名称 x****x

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 将name转为匿名格式
     * @return
     */
//    public String getAnonymousName() {
//        if(null!=anonymousName) {
//            return anonymousName;
//        }
//        if(null == name) {
//            anonymousName = null;
//        }else if(name.length()<=1) {
//            anonymousName = "*";
//        }else if(name.length() == 2) {
//            anonymousName = name.substring(0, 1) + "*";
//        }else {
//            char[] cs = anonymousName.toCharArray();
//            for(int i=1; i<anonymousName.length()-1; i++) {
//                cs[i] = '*';
//            }
//            anonymousName = new String(cs);
//        }
//        return anonymousName;
//    }

//    public void setAnonymousName(String anonymousName) {
//        this.anonymousName = anonymousName;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
