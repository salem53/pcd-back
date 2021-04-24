package com.pcd.freelance.entities;

public class Question {
  String question;
  String response;
  int note ;

  public Question() {
  }

  public Question(String question, String response, int note) {
    this.question = question;
    this.response = response;
    this.note = note;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public int getNote() {
    return note;
  }

  public void setNote(int note) {
    this.note = note;
  }
}
