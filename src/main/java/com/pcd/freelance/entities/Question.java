package com.pcd.freelance.entities;

import java.util.List;

public class Question {
  String question;
  List<String> response;
  List<String> rightResponse;
  int note ;

  public Question() {
  }

  public Question(String question,List<String>  response,List<String> rightresponse, int note) {
    this.question = question;
    this.response = response;
    this.rightResponse=rightresponse;
    this.note = note;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public List<String> getResponse() {
    return response;
  }

  public void setResponse(List<String> response) {
    this.response = response;
  }

  public List<String> getRightResponse() {
    return rightResponse;
  }

  public void setRightResponse(List<String> rightResponse) {
    this.rightResponse = rightResponse;
  }

  public int getNote() {
    return note;
  }

  public void setNote(int note) {
    this.note = note;
  }
}
