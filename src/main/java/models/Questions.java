package models;

import javafx.event.ActionEvent;

public class Questions{
    public int getIdquiz() {
        return idquiz;
    }

    public void setQu(quiz qu) {
        this.qu = qu;
    }

    quiz qu;

    public quiz getQu() {
        return qu;
    }

    public void setIdquiz(int idquiz) {
        this.idquiz = idquiz;
    }

    private int  idquiz;
    private String question;
    private String op1;
    private String op2;
    private String op3;

    private String answer;
    private int idquest;


public Questions (String o1 ,String o2 ,String o3,String a,String q)
{
    this.op1=o1;
    this.op2=o2;
    this.op3=o3;
    this.answer=a;
    this.question=q;

}

    public Questions() {

    }



    public String getAnswer() {
        return answer;
    }

    public String getOp1() {
        return op1;
    }

    public String getOp2() {
        return op2;
    }

    public String getOp3() {
        return op3;
    }


    public String getQuestion() {
        return question;
    }

    public int getIdquest() {
        return idquest;
    }

    public void setIdquest(int idquest) {
        this.idquest = idquest;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }


public Questions (String o1 ,String o2 ,String o3,String a,String q,int idquiz)
    {
        this.op1=o1;
        this.op2=o2;
        this.op3=o3;
        this.answer=a;
        this.question=q;
        this.idquiz=idquiz;
        }


    public void ajout(ActionEvent actionEvent) {
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
