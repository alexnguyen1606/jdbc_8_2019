package com.laptrinhjavaweb.builder;

public class UserBuilder {
    private int status;
    private String userName;
    private String fullName;
    private UserBuilder (Builder builder){
        this.status = builder.status;
        this.fullName = builder.fullName;
        this.userName = builder.userName;
    }
    public int getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public static class Builder{
        private int status;
        private String userName;
        private String fullName;

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }
        public UserBuilder build(){
            return new UserBuilder(this);
        }
    }
}
