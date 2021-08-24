package com.lister.employeeonboardingsystem.enums;

/**
 *
 */
public class StatusDescription {
    public enum Status{
        ACCEPT("Completed"),REJECT("Rejected"),SUBMIT("Pending"),SAVE("Incomplete"),UPDATED("updated"),NEW("new user");
        private final String statusName;
        Status(String statusName){
            this.statusName = statusName;
        }
        public String getStatusName(){
            return this.statusName;
        }
    }
}
