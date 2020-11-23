package com.ktc.qa.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 余俊锋
 * @date 2020/11/23 18:18
 * @Description
 */
@IdClass(Pl.class)
@Table(name="tb_pl")
@Entity
public class Pl implements Serializable {

    @Id
    @Column(name="problemid")
    private String problemId;
    @Id
    @Column(name="labelid")
    private String labelId;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return "Pl{" +
                "problemId='" + problemId + '\'' +
                ", labelId='" + labelId + '\'' +
                '}';
    }
}
