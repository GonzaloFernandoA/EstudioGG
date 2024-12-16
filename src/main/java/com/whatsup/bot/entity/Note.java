/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

/**
 *
 * @author Gonzalo_Avalos
 */

public class Note {

    private String Id;
    private String User;
    private String Note;

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the User
     */
    public String getUser() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUser(String User) {
        this.User = User;
    }

    /**
     * @return the Note
     */
    public String getNote() {
        return Note;
    }

    /**
     * @param Note the Note to set
     */
    public void setNote(String Note) {
        this.Note = Note;
    }
}
