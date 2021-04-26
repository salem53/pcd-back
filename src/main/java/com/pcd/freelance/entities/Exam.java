package com.pcd.freelance.entities;

import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Set;

@Entity
@Table(name="Exam",uniqueConstraints={@UniqueConstraint(columnNames={"file"})})
public class Exam {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="idExam")
  private long id;

  @Column(name="file")
  @NotNull
  String file;

  @Column(name="nbQuestion")
  @NotNull
  int nbQuestion;

  @OneToMany(mappedBy="exam")
  Set<PassExamLink> passExams;

  private File fileContent;

  public File getFileContent() {
    return fileContent;
  }

  public void setFileContent(File fileContent) {
    this.fileContent = fileContent;
    this.file=fileContent.getName();
  }

  public Exam(long id, int nbQuestion, File fileContent) {
    this.id = id;
    this.fileContent = fileContent;
    this.file = fileContent.getName();
    this.nbQuestion = nbQuestion;

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFile() {
    return file;
  }


  public int getNbQuestion() {
    return nbQuestion;
  }

  public void setNbQuestion(int nbQuestion) {
    this.nbQuestion = nbQuestion;
  }

  public Exam() {
  }


}
