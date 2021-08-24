package com.lister.employeeonboardingsystem.enums;

/**
 *
 */
public class RoleDescription {
    public enum Role{
        HR(1),SENIORSOFTWAREENGINEER(2),SOFTWAREENGINEER(3),ASSOCIATESOFTWAREENGINEER(4);
        private final int roleId;
        Role(int roleId){
            this.roleId=roleId;
        }
        public int getRoleId(){
            return this.roleId;
        }
    }
}
