package com.laptrinhjavaweb.builder;

public class AssignmentCustomerBuilder {
    private Long customerId;
    private Long userId;

    public Long getCustomerId() {
        return customerId;
    }

    public Long getUserId() {
        return userId;
    }

    private AssignmentCustomerBuilder(Builder builder) {
        this.customerId = builder.customerId;
        this.userId = builder.userId;
    }
    public static class Builder{
        private Long customerId;
        private Long userId;

        public Builder setCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }
        public AssignmentCustomerBuilder build(){
            return new AssignmentCustomerBuilder(this);
        }
    }
}
