/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Entite;

/**
 *
 * @author Farah GABSI
 */
public class GroupsMembers {

    private Integer id;
    private int groups_id, Idauthor;

    public GroupsMembers(Integer id, int groups_id, int Idauthor) {
        this.id = id;
        this.groups_id = groups_id;
        this.Idauthor = Idauthor;
    }

    @Override
    public String toString() {
        return "GroupsMembers{" + "id=" + id + ", groups_id=" + groups_id + ", Idauthor=" + Idauthor + '}';
    }

    public GroupsMembers() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGroups_id() {
        return groups_id;
    }

    public void setGroups_id(int groups_id) {
        this.groups_id = groups_id;
    }

    public int getIdauthor() {
        return Idauthor;
    }

    public void setIdauthor(int Idauthor) {
        this.Idauthor = Idauthor;
    }
    

}
