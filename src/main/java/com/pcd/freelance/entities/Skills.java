package com.pcd.freelance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Table(name ="Skills",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Skills {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="idSkill")
  private long id;

  @Column(name="name")
  @NotBlank(message = "Name is mandatory")
  private String name;

  @JsonIgnore
  @Column(name="file")
  String file;

  @JsonIgnore
  @Column(name="nbQuestion")
  int nbQuestion;

  @JsonIgnore
  @Column(name="score")
  float score;

 // private File fileContent;

  @JsonIgnore
  @OneToMany(mappedBy ="skill")
  Set<Skilled> skilled;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    if(score>0) {
      this.score = score;
    }
  }
  public Skills() {
  }

  /*public File getFileContent() {
    return fileContent;
  }

  public void setFileContent(File fileContent) {
    String name=fileContent.getName();
    int index = name.lastIndexOf(".");
    List<Question> ExamQuestion = new ArrayList<Question>() ;
    ObjectMapper objectMapper = new ObjectMapper();
    TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {
    };
    File newFile = new File("src/main/resources/Exams/" + name);
    if ((name.substring(index + 1, name.length()).equals("json"))) {



        try(var is=new FileInputStream(fileContent))
        {

          InputStream inputStream = TypeReference.class.getResourceAsStream("src/main/resources/Exams/" +name );
          Files.copy(is,newFile.toPath());
          List<Question> questions = objectMapper.readValue(inputStream, typeReference);

          List<Integer> numberOfQuestion = new ArrayList<Integer>();

        } catch (JsonParseException e) {
          e.printStackTrace();
        } catch (JsonMappingException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }

    } else {
      System.out.println("a json file should be uploaded ");
    }
    this.fileContent = newFile;
    this.file=fileContent.getName();
  }

*/




  public void setNbQuestion(int nbQuestion) {
    if(nbQuestion>0) {
      this.nbQuestion = nbQuestion;
    }
  }


  public Skills(@NotBlank(message = "Name is mandatory") String name, int nbQuestion,float score) {

    this.name = name;
   // this.file = fileContent.getName();
    this.nbQuestion = nbQuestion;
  //  this.fileContent = fileContent;
    this.score=score;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  public Skills(@NotBlank(message = "Name is mandatory") String name) {
    this.name = name;
  }

  public Skills(@NotBlank(message = "Name is mandatory") String name, String file, int nbQuestion, float score) {
    this.name = name;
    this.file = file;
    this.nbQuestion = nbQuestion;
    this.score = score;
  }
}
